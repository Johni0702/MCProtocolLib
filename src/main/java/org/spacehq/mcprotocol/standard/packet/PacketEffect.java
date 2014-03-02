package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketEffect extends Packet {

	public int effectId;
	public int x;
	public int y;
	public int z;
	public int data;
	public boolean ignoreVolume;

	public PacketEffect() {
	}

	public PacketEffect(int effectId, int x, int y, int z, int data, boolean ignoreVolume) {
		this.effectId = effectId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.data = data;
		this.ignoreVolume = ignoreVolume;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.effectId = in.readInt();
		this.x = in.readInt();
		this.y = in.readUnsignedByte();
		this.z = in.readInt();
		this.data = in.readInt();
		this.ignoreVolume = in.readBoolean();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.effectId);
		out.writeInt(this.x);
		out.writeByte(this.y);
		out.writeInt(this.z);
		out.writeInt(this.data);
		out.writeBoolean(this.ignoreVolume);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 61;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
