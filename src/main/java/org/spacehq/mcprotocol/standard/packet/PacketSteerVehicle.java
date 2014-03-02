package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketSteerVehicle extends Packet {

	public float sideways;
	public float forward;
	public boolean jump;
	public boolean unmount;

	public PacketSteerVehicle() {
	}

	public PacketSteerVehicle(float sideways, float forward, boolean jump, boolean unmount) {
		this.sideways = sideways;
		this.forward = forward;
		this.jump = jump;
		this.unmount = unmount;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.sideways = in.readFloat();
		this.forward = in.readFloat();
		this.jump = in.readBoolean();
		this.unmount = in.readBoolean();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeFloat(this.sideways);
		out.writeFloat(this.forward);
		out.writeBoolean(this.jump);
		out.writeBoolean(this.unmount);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 27;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
