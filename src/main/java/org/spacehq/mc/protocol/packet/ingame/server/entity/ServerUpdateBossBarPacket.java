package org.spacehq.mc.protocol.packet.ingame.server.entity;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.entity.bossbar.BossBarAction;
import org.spacehq.mc.protocol.data.game.values.entity.bossbar.BossBarColor;
import org.spacehq.mc.protocol.data.game.values.entity.bossbar.BossBarType;
import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;
import java.util.UUID;

public class ServerUpdateBossBarPacket implements Packet {

    private UUID uuid;
    private BossBarAction action;
    private Message text;
    private float percent;
    private BossBarColor color;
    private BossBarType type;
    private boolean doesDarkenSky;
    private boolean doesPlayMusic;

    @SuppressWarnings("unused")
    private ServerUpdateBossBarPacket() {
    }

    private ServerUpdateBossBarPacket(UUID uuid, BossBarAction action, Message text, float percent, BossBarColor color,
                                     BossBarType type, boolean doesDarkenSky, boolean doesPlayMusic) {
        this.uuid = uuid;
        this.action = action;
        this.text = text;
        this.percent = percent;
        this.color = color;
        this.type = type;
        this.doesDarkenSky = doesDarkenSky;
        this.doesPlayMusic = doesPlayMusic;
    }

    public ServerUpdateBossBarPacket(UUID uuid, Message text, float percent, BossBarColor color,
                                      BossBarType type, boolean doesDarkenSky, boolean doesPlayMusic) {
        this(uuid, BossBarAction.ADD, text, percent, color, type, doesDarkenSky, doesPlayMusic);
    }

    public ServerUpdateBossBarPacket(UUID uuid) {
        this(uuid, BossBarAction.REMOVE, null, 0, null, null, false, false);
    }

    public ServerUpdateBossBarPacket(UUID uuid, Message text) {
        this(uuid, BossBarAction.UPDATE_TEXT, text, 0, null, null, false, false);
    }

    public ServerUpdateBossBarPacket(UUID uuid, float percent) {
        this(uuid, BossBarAction.UPDATE_TEXT, null, percent, null, null, false, false);
    }

    public ServerUpdateBossBarPacket(UUID uuid, boolean doesDarkenSky, boolean doesPlayMusic) {
        this(uuid, BossBarAction.UPDATE_PROPERTIES, null, 0, null, null, doesDarkenSky, doesPlayMusic);
    }

    public ServerUpdateBossBarPacket(UUID uuid, BossBarColor color, BossBarType type) {
        this(uuid, BossBarAction.UPDATE_STYLE, null, 0, color, type, false, false);
    }

    public UUID getUuid() {
        return uuid;
    }

    public BossBarAction getAction() {
        return action;
    }

    public Message getText() {
        return text;
    }

    public float getPercent() {
        return percent;
    }

    public BossBarColor getColor() {
        return color;
    }

    public BossBarType getType() {
        return type;
    }

    public boolean doesDarkenSky() {
        return doesDarkenSky;
    }

    public boolean doesPlayMusic() {
        return doesPlayMusic;
    }

    @Override
    public void read(NetInput in) throws IOException {
        uuid = in.readUUID();
        action = MagicValues.key(BossBarAction.class, in.readUnsignedByte());
        switch (action) {
            case ADD:
                text = Message.fromString(in.readString());
                percent = in.readFloat();
                color = MagicValues.key(BossBarColor.class, in.readUnsignedByte());
                type = MagicValues.key(BossBarType.class, in.readUnsignedByte());
                byte flags = in.readByte();
                doesDarkenSky = (flags & 0x1) != 0;
                doesPlayMusic = (flags & 0x2) != 0;
                break;
            case UPDATE_PERCENT:
                percent = in.readFloat();
                break;
            case UPDATE_TEXT:
                text = Message.fromString(in.readString());
                break;
            case UPDATE_STYLE:
                color = MagicValues.key(BossBarColor.class, in.readUnsignedByte());
                type = MagicValues.key(BossBarType.class, in.readUnsignedByte());
                break;
            case UPDATE_PROPERTIES:
                flags = in.readByte();
                doesDarkenSky = (flags & 0x1) != 0;
                doesPlayMusic = (flags & 0x2) != 0;
                break;
            case REMOVE:
            default:
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeUUID(uuid);
        out.writeByte(MagicValues.value(Integer.class, action));
        switch (action) {
            case ADD:
                out.writeString(text.toJsonString());
                out.writeFloat(percent);
                out.writeByte(MagicValues.value(Integer.class, color));
                out.writeByte(MagicValues.value(Integer.class, type));
                byte flags = 0;
                if (doesDarkenSky) {
                    flags |= 0x1;
                }
                if (doesPlayMusic) {
                    flags |= 0x2;
                }
                out.writeByte(flags);
                break;
            case UPDATE_PERCENT:
                out.writeFloat(percent);
                break;
            case UPDATE_TEXT:
                out.writeString(text.toJsonString());
                break;
            case UPDATE_STYLE:
                out.writeByte(MagicValues.value(Integer.class, color));
                out.writeByte(MagicValues.value(Integer.class, type));
                break;
            case UPDATE_PROPERTIES:
                flags = 0;
                if (doesDarkenSky) {
                    flags |= 0x1;
                }
                if (doesPlayMusic) {
                    flags |= 0x2;
                }
                out.writeByte(flags);
                break;
            case REMOVE:
            default:
        }
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
