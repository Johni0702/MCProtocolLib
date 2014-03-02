package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;
import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketScoreboardObjective extends Packet {

	public String name;
	public String value;
	public byte action;

	public PacketScoreboardObjective() {
	}

	public PacketScoreboardObjective(String name, String value, byte action) {
		this.name = name;
		this.value = value;
		this.action = action;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.name = in.readString();
		this.value = in.readString();
		this.action = in.readByte();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeString(this.name);
		out.writeString(this.value);
		out.writeByte(this.action);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 206;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
