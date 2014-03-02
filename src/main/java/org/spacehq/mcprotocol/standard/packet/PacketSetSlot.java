package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;
import org.spacehq.mcprotocol.standard.data.StandardItemStack;
import org.spacehq.mcprotocol.standard.io.StandardInput;
import org.spacehq.mcprotocol.standard.io.StandardOutput;

public class PacketSetSlot extends Packet {

	public byte id;
	public short slot;
	public StandardItemStack item;

	public PacketSetSlot() {
	}

	public PacketSetSlot(byte id, short slot, StandardItemStack item) {
		this.id = id;
		this.slot = slot;
		this.item = item;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.id = in.readByte();
		this.slot = in.readShort();
		this.item = ((StandardInput) in).readItem();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeByte(this.id);
		out.writeShort(this.slot);
		if(this.item != null) {
			((StandardOutput) out).writeItem(this.item);
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
		return 103;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
