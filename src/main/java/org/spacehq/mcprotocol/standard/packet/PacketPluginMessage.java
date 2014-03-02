package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketPluginMessage extends Packet {

	public String channel;
	public byte data[];

	public PacketPluginMessage() {
	}

	public PacketPluginMessage(String channel, byte data[]) {
		this.channel = channel;
		this.data = data;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.channel = in.readString();
		this.data = in.readBytes(in.readShort());
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeString(this.channel);
		out.writeShort(this.data.length);
		out.writeBytes(this.data);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 250;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
