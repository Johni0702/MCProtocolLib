package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;
import org.spacehq.mcprotocol.util.Constants;

public class PacketServerPing extends Packet {

	public boolean readSuccessfully = false;

	public PacketServerPing() {
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.readSuccessfully = in.readBoolean();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeByte(Constants.StandardProtocol.PING_MAGIC);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 254;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
