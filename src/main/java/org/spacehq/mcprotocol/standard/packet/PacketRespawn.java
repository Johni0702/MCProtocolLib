package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketRespawn extends Packet {

	public int dimension;
	public byte difficulty;
	public byte gameMode;
	public short worldHeight;
	public String levelType;

	public PacketRespawn() {
	}

	public PacketRespawn(int dimension, byte difficulty, byte gameMode, short worldHeight, String levelType) {
		this.dimension = dimension;
		this.difficulty = difficulty;
		this.gameMode = gameMode;
		this.worldHeight = worldHeight;
		this.levelType = levelType;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.dimension = in.readInt();
		this.difficulty = in.readByte();
		this.gameMode = in.readByte();
		this.worldHeight = in.readShort();
		this.levelType = in.readString();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.dimension);
		out.writeByte(this.difficulty);
		out.writeByte(this.gameMode);
		out.writeShort(this.worldHeight);
		out.writeString(this.levelType);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 9;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
