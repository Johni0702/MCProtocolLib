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

public class PacketEntityEquipment extends Packet {

	public int entityId;
	public short slot;
	public StandardItemStack item;

	public PacketEntityEquipment() {
	}

	public PacketEntityEquipment(int entityId, short slot, StandardItemStack item) {
		this.entityId = entityId;
		this.slot = slot;
		this.item = item;
	}

	public int getEntityId() {
		return this.entityId;
	}

	public short getSlot() {
		return this.slot;
	}

	public StandardItemStack getItem() {
		return this.item;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.entityId = in.readInt();
		this.slot = in.readShort();
		this.item = ((StandardInput) in).readItem();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.entityId);
		out.writeShort(this.slot);
		((StandardOutput) out).writeItem(this.item);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 5;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
