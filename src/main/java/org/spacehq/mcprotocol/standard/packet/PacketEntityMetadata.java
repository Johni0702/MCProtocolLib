package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;
import org.spacehq.mcprotocol.standard.data.WatchableObject;
import org.spacehq.mcprotocol.standard.io.StandardInput;
import org.spacehq.mcprotocol.standard.io.StandardOutput;

public class PacketEntityMetadata extends Packet {

	public int entityId;
	public WatchableObject metadata[];

	public PacketEntityMetadata() {
	}

	public PacketEntityMetadata(int entityId, WatchableObject metadata[]) {
		this.entityId = entityId;
		this.metadata = metadata;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.entityId = in.readInt();
		this.metadata = ((StandardInput) in).readMetadata();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.entityId);
		((StandardOutput) out).writeMetadata(this.metadata);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 40;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
