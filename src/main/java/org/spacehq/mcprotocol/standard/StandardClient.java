package org.spacehq.mcprotocol.standard;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.auth.UserAuthentication;
import org.spacehq.mc.auth.exception.AuthenticationException;
import org.spacehq.mcprotocol.exception.ConnectException;
import org.spacehq.mcprotocol.exception.LoginException;
import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.standard.packet.PacketHandshake;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * A client implementing standard Minecraft protocol.
 */
public class StandardClient extends StandardConnection implements Client {

	/**
	 * The client's profile.
	 */
	private GameProfile profile;

	/**
	 * The client's access token.
	 */
	private String accessToken;

	/**
	 * Creates a new standard client.
	 * @param host Host to connect to.
	 * @param port Port to connect to.
	 */
	public StandardClient(String host, int port) {
		super(host, port);
	}

	/**
	 * Gets the client's profile.
	 * @return The client's profile.
	 */
	public GameProfile getProfile() {
		return this.profile;
	}

	/**
	 * Gets the client's session id.
	 * @return The client's session id.
	 */
	public String getAccessToken() {
		return this.accessToken;
	}

	@Override
	public String getUsername() {
		return this.profile != null ? this.profile.getName() : null;
	}

	@Override
	public void setUsername(String username) {
		if(this.profile != null) {
			return;
		}

		this.profile = new GameProfile((UUID) null, username);
	}

	@Override
	public boolean login(String username, String password) throws LoginException {
		if(this.profile != null) {
			throw new LoginException("Already logged in with username: " + this.getProfile().getName());
		}

		String clientToken = UUID.randomUUID().toString();
		UserAuthentication auth = new UserAuthentication(clientToken);
		auth.setUsername(username);
		auth.setPassword(password);
		try {
			auth.login();
		} catch(AuthenticationException e) {
			throw new LoginException("Failed to login to minecraft.net", e);
		}

		this.profile = auth.getSelectedProfile();
		this.accessToken = auth.getAccessToken();
		return true;
	}

	@Override
	public void connect() throws ConnectException {
		try {
			Socket sock = new Socket(InetAddress.getByName(this.getRemoteHost()), this.getRemotePort());
			sock.setSoTimeout(30000);
			sock.setTrafficClass(24);
			super.connect(sock);
			this.send(new PacketHandshake(this.getUsername(), this.getRemoteHost(), this.getRemotePort()));
		} catch(UnknownHostException e) {
			throw new ConnectException("Unknown host: " + this.getRemoteHost());
		} catch(IOException e) {
			throw new ConnectException("Failed to open stream: " + this.getRemoteHost(), e);
		}
	}

}
