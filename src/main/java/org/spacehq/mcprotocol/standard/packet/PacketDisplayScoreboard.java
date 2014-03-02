package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;
import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketDisplayScoreboard extends Packet {

	public byte position;
	public String scoreboard;

	public PacketDisplayScoreboard() {
	}

	public PacketDisplayScoreboard(byte position, String scoreboard) {
		this.position = position;
		this.scoreboard = scoreboard;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.position = in.readByte();
		this.scoreboard = in.readString();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeByte(this.position);
		out.writeString(this.scoreboard);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 208;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
