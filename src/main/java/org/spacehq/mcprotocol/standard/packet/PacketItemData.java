package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketItemData extends Packet {

	public short item;
	public short damage;
	public byte data[];

	public PacketItemData() {
	}

	public PacketItemData(short item, short damage, byte data[]) {
		this.item = item;
		this.damage = damage;
		this.data = data;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.item = in.readShort();
		this.damage = in.readShort();
		this.data = in.readBytes(in.readShort());
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeShort(this.item);
		out.writeShort(this.damage);
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
		return 131;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
