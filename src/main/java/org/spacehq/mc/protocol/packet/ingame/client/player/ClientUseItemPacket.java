package org.spacehq.mc.protocol.packet.ingame.client.player;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.entity.player.Hand;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ClientUseItemPacket implements Packet {

    private Hand hand;

    @SuppressWarnings("unused")
    private ClientUseItemPacket() {
    }

    public ClientUseItemPacket(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public void read(NetInput in) throws IOException {
        hand = MagicValues.key(Hand.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(MagicValues.value(Integer.class, hand));
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
