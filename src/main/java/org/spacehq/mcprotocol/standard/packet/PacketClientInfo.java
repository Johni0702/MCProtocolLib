package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketClientInfo extends Packet {

	public String locale;
	public byte viewDistance;
	public byte chatFlags;
	public byte difficulty;
	public boolean cape;

	public PacketClientInfo() {
	}

	public PacketClientInfo(String locale, byte viewDistance, byte chatFlags, byte difficulty, boolean cape) {
		this.locale = locale;
		this.viewDistance = viewDistance;
		this.chatFlags = chatFlags;
		this.difficulty = difficulty;
		this.cape = cape;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.locale = in.readString();
		this.viewDistance = in.readByte();
		this.chatFlags = in.readByte();
		this.difficulty = in.readByte();
		this.cape = in.readBoolean();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeString(this.locale);
		out.writeByte(this.viewDistance);
		out.writeByte(this.chatFlags);
		out.writeByte(this.difficulty);
		out.writeBoolean(this.cape);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 204;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
