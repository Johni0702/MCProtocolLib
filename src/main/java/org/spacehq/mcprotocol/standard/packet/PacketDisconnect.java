package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketDisconnect extends Packet {

	public String reason;

	public PacketDisconnect() {
	}

	public PacketDisconnect(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.reason = in.readString();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeString(this.reason);
	}

	@Override
	public void handleClient(Client conn) {
		conn.disconnect(this.reason, false);
	}

	@Override
	public void handleServer(ServerConnection conn) {
		conn.disconnect(this.reason, false);
	}

	@Override
	public int getId() {
		return 255;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
