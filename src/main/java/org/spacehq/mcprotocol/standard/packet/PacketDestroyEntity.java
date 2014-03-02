package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketDestroyEntity extends Packet {

	public int entityIds[];

	public PacketDestroyEntity() {
	}

	public PacketDestroyEntity(int... entityIds) {
		this.entityIds = entityIds.clone();
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.entityIds = new int[in.readUnsignedByte()];
		for(int count = 0; count < this.entityIds.length; count++) {
			this.entityIds[count] = in.readInt();
		}
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeByte(this.entityIds.length);
		for(int id : this.entityIds) {
			out.writeInt(id);
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
		return 29;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
