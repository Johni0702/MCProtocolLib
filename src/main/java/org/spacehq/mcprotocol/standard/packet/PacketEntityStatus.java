package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketEntityStatus extends Packet {

	public int entityId;
	public byte status;

	public PacketEntityStatus() {
	}

	public PacketEntityStatus(int entityId, byte status) {
		this.entityId = entityId;
		this.status = status;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.entityId = in.readInt();
		this.status = in.readByte();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.entityId);
		out.writeByte(this.status);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 38;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
