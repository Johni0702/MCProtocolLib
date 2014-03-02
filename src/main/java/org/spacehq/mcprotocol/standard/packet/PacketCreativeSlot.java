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

public class PacketCreativeSlot extends Packet {

	public short slot;
	public StandardItemStack clicked;

	public PacketCreativeSlot() {
	}

	public PacketCreativeSlot(short slot, StandardItemStack clicked) {
		this.slot = slot;
		this.clicked = clicked;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.slot = in.readShort();
		this.clicked = ((StandardInput) in).readItem();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeShort(this.slot);
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
		return 107;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
