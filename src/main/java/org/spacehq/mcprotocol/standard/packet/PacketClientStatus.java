package org.spacehq.mcprotocol.standard.packet;

import org.spacehq.mcprotocol.event.PacketVisitor;
import java.io.BufferedReader;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;

import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.packet.Packet;
import org.spacehq.mcprotocol.standard.StandardServer;
import org.spacehq.mcprotocol.standard.StandardServerConnection;
import org.spacehq.mcprotocol.util.Util;

public class PacketClientStatus extends Packet {

	public byte status;

	public PacketClientStatus() {
	}

	public PacketClientStatus(byte status) {
		this.status = status;
	}

	public byte getStatus() {
		return this.status;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.status = in.readByte();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeByte(this.status);
	}

	@Override
	public void handleClient(Client conn) {
	}

	@Override
	public void handleServer(ServerConnection conn) {
		if(this.status == 0 && conn.getServer().isAuthEnabled()) {
			String encrypted = new BigInteger(Util.encrypt(((StandardServerConnection) conn).getLoginKey(), ((StandardServer) conn.getServer()).getKeys().getPublic(), ((StandardServerConnection) conn).getSecretKey())).toString(16);
			String response = null;

			try {
				URL url = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(conn.getUsername(), "UTF-8") + "&serverId=" + URLEncoder.encode(encrypted, "UTF-8"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				response = reader.readLine();
				reader.close();
			} catch(IOException e) {
				response = e.toString();
			}

			if(!response.equals("YES")) {
				conn.disconnect("Failed to verify username!");
				return;
			}
		}

		for(ServerConnection c : conn.getServer().getConnections()) {
			if(c.getUsername().equals(conn.getUsername())) {
				c.disconnect("You logged in from another location!");
				break;
			}
		}
	}

	@Override
	public int getId() {
		return 205;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
