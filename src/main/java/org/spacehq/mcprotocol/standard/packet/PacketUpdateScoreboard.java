package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;
import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketUpdateScoreboard extends Packet {

	public String item;
	public byte action;
	public String scoreboard;
	public int value;

	public PacketUpdateScoreboard() {
	}

	public PacketUpdateScoreboard(String item, byte action, String scoreboard, int value) {
		this.item = item;
		this.action = action;
		this.scoreboard = scoreboard;
		this.value = value;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.item = in.readString();
		this.action = in.readByte();
		if(this.action != 1) {
			this.scoreboard = in.readString();
			this.value = in.readInt();
		}
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeString(this.item);
		out.writeByte(this.action);
		if(this.action != 1) {
			out.writeString(this.scoreboard);
			out.writeInt(this.value);
		}
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 207;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
