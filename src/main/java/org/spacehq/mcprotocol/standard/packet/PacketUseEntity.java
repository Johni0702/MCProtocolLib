package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketUseEntity extends Packet {

	public int user;
	public int target;
	public boolean leftclick;

	public PacketUseEntity() {
	}

	public PacketUseEntity(int user, int target, boolean leftclick) {
		this.user = user;
		this.target = target;
		this.leftclick = leftclick;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.user = in.readInt();
		this.target = in.readInt();
		this.leftclick = in.readBoolean();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.user);
		out.writeInt(this.target);
		out.writeBoolean(this.leftclick);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 7;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
