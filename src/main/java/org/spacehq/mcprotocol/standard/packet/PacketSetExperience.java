package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketSetExperience extends Packet {

	public float experienceBar;
	public short level;
	public short experience;

	public PacketSetExperience() {
	}

	public PacketSetExperience(float experienceBar, short level, short experience) {
		this.experienceBar = experienceBar;
		this.level = level;
		this.experience = experience;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.experienceBar = in.readFloat();
		this.level = in.readShort();
		this.experience = in.readShort();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeFloat(this.experienceBar);
		out.writeShort(this.level);
		out.writeShort(this.experience);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 43;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
