package org.spacehq.mc.protocol.packet.ingame.client.player;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.entity.player.Hand;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ClientSwingArmPacket implements Packet {

    private Hand hand;

    @SuppressWarnings("unused")
    private ClientSwingArmPacket() {
    }

    public ClientSwingArmPacket(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.hand = MagicValues.key(Hand.class, in.readByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(MagicValues.value(Byte.class, this.hand));
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
