package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketPlayerListItem extends Packet {

	public String name;
	public boolean online;
	public short ping;

	public PacketPlayerListItem() {
	}

	public PacketPlayerListItem(String name, boolean online, short ping) {
		this.name = name;
		this.online = online;
		this.ping = ping;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.name = in.readString();
		this.online = in.readBoolean();
		this.ping = in.readShort();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeString(this.name);
		out.writeBoolean(this.online);
		out.writeShort(this.ping);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 201;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
