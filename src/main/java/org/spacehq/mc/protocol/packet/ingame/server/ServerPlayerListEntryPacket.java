package org.spacehq.mc.protocol.packet.ingame.server;

import org.spacehq.mc.auth.properties.Property;
import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.PlayerListEntry;
import org.spacehq.mc.protocol.data.game.values.PlayerListEntryAction;
import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;
import java.util.UUID;

public class ServerPlayerListEntryPacket implements Packet {
	private PlayerListEntryAction action;
	private PlayerListEntry entries[];

	@SuppressWarnings("unused")
	private ServerPlayerListEntryPacket() {
	}

	public ServerPlayerListEntryPacket(PlayerListEntryAction action, PlayerListEntry entries[]) {
		this.action = action;
		this.entries = entries;
	}

	public PlayerListEntryAction getAction() {
		return this.action;
	}

	public PlayerListEntry[] getEntries() {
		return this.entries;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.action = MagicValues.key(PlayerListEntryAction.class, in.readVarInt());
		this.entries = new PlayerListEntry[in.readVarInt()];
		for(int count = 0; count < this.entries.length; count++) {
			UUID uuid = UUID.fromString(in.readString());
			PlayerListEntry entry = null;
			switch(this.action) {
				case ADD_PLAYER:
					String name = in.readString();
					Property properties[] = new Property[in.readVarInt()];
					for(int index = 0; index < properties.length; index++) {
						String propertyName = in.readString();
						String value = in.readString();
						String signature = null;
						if(in.readBoolean()) {
							signature = in.readString();
						}

						properties[index] = new Property(propertyName, value, signature);
					}

					GameMode gameMode = MagicValues.key(GameMode.class, in.readVarInt());
					int ping = in.readVarInt();
					entry = new PlayerListEntry(uuid, name, properties, gameMode, ping);
					break;
				case UPDATE_GAMEMODE:
					GameMode mode = MagicValues.key(GameMode.class, in.readVarInt());
					entry = new PlayerListEntry(uuid, mode);
					break;
				case UPDATE_LATENCY:
					int png = in.readVarInt();
					entry = new PlayerListEntry(uuid, png);
					break;
				case REMOVE_PLAYER:
					entry = new PlayerListEntry(uuid);
					break;
			}

			this.entries[count] = entry;
		}
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeVarInt(MagicValues.value(Integer.class, this.action));
		out.writeVarInt(this.entries.length);
		for(PlayerListEntry entry : this.entries) {
			out.writeString(entry.getUUID().toString());
			switch(this.action) {
				case ADD_PLAYER:
					out.writeString(entry.getName());
					out.writeVarInt(entry.getProperties().length);
					for(Property property : entry.getProperties()) {
						out.writeString(property.getName());
						out.writeString(property.getValue());
						out.writeBoolean(property.hasSignature());
						if(property.hasSignature()) {
							out.writeString(property.getSignature());
						}
					}

					out.writeVarInt(MagicValues.value(Integer.class, entry.getGameMode()));
					out.writeVarInt(entry.getPing());
					break;
				case UPDATE_GAMEMODE:
					out.writeVarInt(MagicValues.value(Integer.class, entry.getGameMode()));
					break;
				case UPDATE_LATENCY:
					out.writeVarInt(entry.getPing());
					break;
				case REMOVE_PLAYER:
					break;
			}
		}
	}

	@Override
	public boolean isPriority() {
		return false;
	}
}
