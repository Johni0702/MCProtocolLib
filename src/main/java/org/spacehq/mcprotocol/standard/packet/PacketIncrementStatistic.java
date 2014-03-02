package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;

public class PacketIncrementStatistic extends Packet {

	public int statistic;
	public int amount;

	public PacketIncrementStatistic() {
	}

	public PacketIncrementStatistic(int statistic, int amount) {
		this.statistic = statistic;
		this.amount = amount;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.statistic = in.readInt();
		this.amount = in.readInt();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.statistic);
		out.writeInt(this.amount);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 200;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
