package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketMultiBlockChange extends Packet {

	public int x;
	public int z;
	public int records;
	public byte data[];

	public PacketMultiBlockChange() {
	}

	public PacketMultiBlockChange(int x, int z, int records, byte data[]) {
		this.x = x;
		this.z = z;
		this.records = records;
		this.data = data;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.x = in.readInt();
		this.z = in.readInt();
		this.records = in.readShort() & 0xffff;
		int size = in.readInt();
		if(size > 0) {
			this.data = in.readBytes(size);
		}
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.x);
		out.writeInt(this.z);
		out.writeShort((short) this.records);
		if(this.data != null) {
			out.writeInt(this.data.length);
			out.writeBytes(this.data);
		} else {
			out.writeInt(0);
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
		return 52;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
