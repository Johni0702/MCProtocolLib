package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketGameState extends Packet {

	public byte reason;
	public byte gamemode;

	public PacketGameState() {
	}

	public PacketGameState(byte reason, byte gamemode) {
		this.reason = reason;
		this.gamemode = gamemode;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.reason = in.readByte();
		this.gamemode = in.readByte();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeByte(this.reason);
		out.writeByte(this.gamemode);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 70;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
