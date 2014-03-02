package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketEntityAction extends Packet {

	public int entityId;
	public byte action;
	public int jumpBar;

	public PacketEntityAction() {
	}

	public PacketEntityAction(int entityId, byte action) {
		this(entityId, action, 0);
	}

	public PacketEntityAction(int entityId, byte action, int jumpBar) {
		this.entityId = entityId;
		this.action = action;
		this.jumpBar = jumpBar;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.entityId = in.readInt();
		this.action = in.readByte();
		this.jumpBar = in.readInt();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.entityId);
		out.writeByte(this.action);
		out.writeInt(this.jumpBar);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 19;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
