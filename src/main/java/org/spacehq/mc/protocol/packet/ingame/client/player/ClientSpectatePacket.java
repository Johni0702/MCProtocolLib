package org.spacehq.mc.protocol.packet.ingame.client.player;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;
import java.util.UUID;

public class ClientSpectatePacket implements Packet {
	private UUID target;

	@SuppressWarnings("unused")
	private ClientSpectatePacket() {
	}

	public ClientSpectatePacket(UUID target) {
		this.target = target;
	}

	public UUID getTarget() {
		return this.target;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.target = UUID.fromString(in.readString());
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeString(this.target.toString());
	}

	@Override
	public boolean isPriority() {
		return false;
	}
}
