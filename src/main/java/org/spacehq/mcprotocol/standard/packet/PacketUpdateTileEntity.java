package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketUpdateTileEntity extends Packet {

	public int x;
	public short y;
	public int z;
	public byte action;
	public byte nbt[];

	public PacketUpdateTileEntity() {
	}

	public PacketUpdateTileEntity(int x, short y, int z, byte action, byte nbt[]) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.action = action;
		this.nbt = nbt;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.x = in.readInt();
		this.y = in.readShort();
		this.z = in.readInt();
		this.action = in.readByte();
		this.nbt = in.readBytes(in.readShort());
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.x);
		out.writeShort(this.y);
		out.writeInt(this.z);
		out.writeByte(this.action);
		if(this.nbt != null) {
			out.writeShort(this.nbt.length);
			out.writeBytes(this.nbt);
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
		return 132;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
