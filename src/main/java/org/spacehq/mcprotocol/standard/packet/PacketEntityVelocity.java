package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketEntityVelocity extends Packet {

	public int entityId;
	public short velX;
	public short velY;
	public short velZ;

	public PacketEntityVelocity() {
	}

	public PacketEntityVelocity(int entityId, short velX, short velY, short velZ) {
		this.entityId = entityId;
		this.velX = velX;
		this.velY = velY;
		this.velZ = velZ;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.entityId = in.readInt();
		this.velX = in.readShort();
		this.velY = in.readShort();
		this.velZ = in.readShort();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.entityId);
		out.writeShort(this.velX);
		out.writeShort(this.velY);
		out.writeShort(this.velZ);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 28;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
