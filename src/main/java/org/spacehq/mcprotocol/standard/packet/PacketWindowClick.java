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

public class PacketWindowClick extends Packet {

	public byte id;
	public short slot;
	public byte mousebutton;
	public short action;
	public byte type;
	public StandardItemStack clicked;

	public PacketWindowClick() {
	}

	public PacketWindowClick(byte id, short slot, byte mousebutton, short action, byte type, StandardItemStack clicked) {
		this.id = id;
		this.slot = slot;
		this.mousebutton = mousebutton;
		this.action = action;
		this.type = type;
		this.clicked = clicked;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.id = in.readByte();
		this.slot = in.readShort();
		this.mousebutton = in.readByte();
		this.action = in.readShort();
		this.type = in.readByte();
		this.clicked = ((StandardInput) in).readItem();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeByte(this.id);
		out.writeShort(this.slot);
		out.writeByte(this.mousebutton);
		out.writeShort(this.action);
		out.writeByte(this.type);
		if(this.clicked != null) {
			((StandardOutput) out).writeItem(this.clicked);
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
		return 102;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
