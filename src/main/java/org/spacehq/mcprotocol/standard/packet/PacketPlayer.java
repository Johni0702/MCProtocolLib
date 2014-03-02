package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketPlayer extends Packet {

	public boolean grounded;

	public PacketPlayer() {
	}

	public PacketPlayer(boolean grounded) {
		this.grounded = grounded;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.grounded = in.readBoolean();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeBoolean(this.grounded);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 10;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
