package org.spacehq.mcprotocol.standard.example;

import java.text.DecimalFormat;

import org.spacehq.mcprotocol.event.DisconnectEvent;
import org.spacehq.mcprotocol.event.PacketReceiveEvent;
import org.spacehq.mcprotocol.event.PacketSendEvent;
import org.spacehq.mcprotocol.event.ProtocolListener;
import org.spacehq.mcprotocol.exception.ConnectException;
import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.packet.Packet;
import org.spacehq.mcprotocol.standard.StandardClient;
import org.spacehq.mcprotocol.standard.packet.PacketChat;
import org.spacehq.mcprotocol.standard.packet.PacketPlayerPositionLook;
import org.spacehq.mcprotocol.util.Util;

/**
 * A simple bot that prints
 * "Hello, this is Heisenberg at coordinate <coordinate>". Be sure to use the
 * Bukkit server setting online-mode=false in server.properties. Otherwise
 * supply a valid minecraft.net username and password.
 */
public class ChatBot {

	private Client client;
	private Listener listener;

	public ChatBot(String host, int port) {
		this.client = new StandardClient(host, port);
		this.listener = new Listener();

		this.client.listen(this.listener);
	}

	public void login(String username) {
		this.client.setUsername(username);

		try {
			this.client.connect();
		} catch(ConnectException e) {
			e.printStackTrace();
		}
	}

	public void say(String text) {
		PacketChat chat = new PacketChat();
		chat.message = text;
		this.client.send(chat);
	}

	public static void main(String[] args) {
		ChatBot bot = new ChatBot("127.0.0.1", 25565);
		Util.logger().info("Logging in...");
		bot.login("Heisenberg");
	}

	private class Listener extends ProtocolListener {
		@Override
		public void onPacketReceive(PacketReceiveEvent event) {
			Packet packet = event.getPacket();

			switch(event.getPacket().getId()) {
				case 0x0D:
					onPositionLook((PacketPlayerPositionLook) packet);
					break;
			}
		}

		@Override
		public void onPacketSend(PacketSendEvent event) {
		}

		@Override
		public void onDisconnect(DisconnectEvent event) {
			Util.logger().info("Disconnected: " + event.getReason());
		}

		public void onPositionLook(PacketPlayerPositionLook packet) {
			client.send(packet);
			DecimalFormat format = new DecimalFormat("#.00");

			ChatBot.this.say("Hello, this is Heisenberg at coordinate (" + format.format(packet.x) + ", " + format.format(packet.y) + ", " + format.format(packet.z) + ")");
		}
	}

}
