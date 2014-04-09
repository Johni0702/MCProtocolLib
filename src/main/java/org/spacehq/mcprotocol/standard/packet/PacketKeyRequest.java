package org.spacehq.mcprotocol.standard.packet;

import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.auth.SessionService;
import org.spacehq.mc.auth.exception.AuthenticationException;
import org.spacehq.mc.auth.exception.AuthenticationUnavailableException;
import org.spacehq.mc.auth.exception.InvalidCredentialsException;
import org.spacehq.mcprotocol.event.PacketVisitor;
import org.spacehq.mcprotocol.net.Client;
import org.spacehq.mcprotocol.net.ServerConnection;
import org.spacehq.mcprotocol.net.io.NetInput;
import org.spacehq.mcprotocol.net.io.NetOutput;
import org.spacehq.mcprotocol.packet.Packet;
import org.spacehq.mcprotocol.standard.StandardClient;
import org.spacehq.mcprotocol.util.Util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class PacketKeyRequest extends Packet {

	public String serverId;
	public byte[] pubKey;
	public byte[] verifyToken;

	public PacketKeyRequest() {
	}

	public PacketKeyRequest(String serverId, byte[] pubKey, byte[] verifyToken) {
		this.serverId = serverId;
		this.pubKey = pubKey;
		this.verifyToken = verifyToken;
	}

	public String getServerId() {
		return this.serverId;
	}

	public byte[] getPublicKey() {
		return this.pubKey;
	}

	public byte[] getVerifyToken() {
		return this.verifyToken;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.serverId = in.readString();
		byte pubKey[] = in.readBytes(in.readShort());
		this.pubKey = pubKey;

		byte verifyToken[] = in.readBytes(in.readShort());
		this.verifyToken = verifyToken;
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeString(this.serverId);
		out.writeShort(this.pubKey.length);
		out.writeBytes(this.pubKey);
		out.writeShort(this.verifyToken.length);
		out.writeBytes(this.verifyToken);
	}

	@Override
	public void handleClient(Client conn) {
		PublicKey key = toKey(this.pubKey);
		SecretKey secret = generateKey();
		if(!this.serverId.equals("-")) {
			String encrypted = new BigInteger(Util.encrypt(this.serverId, key, secret)).toString(16);
			if(!joinServer(conn, encrypted)) {
				return;
			}
		}

		conn.send(new PacketKeyResponse(encryptBytes(key, secret.getEncoded()), encryptBytes(key, this.verifyToken)));
		((StandardClient) conn).setSecretKey(secret);
	}

	@Override
	public void handleServer(ServerConnection conn) {
	}

	@Override
	public int getId() {
		return 253;
	}

	private static byte[] encryptBytes(PublicKey key, byte[] bytes) {
		try {
			Cipher cipher = Cipher.getInstance(key.getAlgorithm());
			cipher.init(1, key);
			return cipher.doFinal(bytes);
		} catch(InvalidKeyException e) {
			e.printStackTrace();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(NoSuchPaddingException e) {
			e.printStackTrace();
		} catch(IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch(BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static SecretKey generateKey() {
		CipherKeyGenerator gen = new CipherKeyGenerator();
		gen.init(new KeyGenerationParameters(new SecureRandom(), 128));
		return new SecretKeySpec(gen.generateKey(), "AES");
	}

	private static PublicKey toKey(byte[] key) {
		try {
			X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return factory.generatePublic(spec);
		} catch(NoSuchAlgorithmException e) {
			Util.logger().warning("Failed to get public key from array!");
			e.printStackTrace();
			return null;
		} catch(InvalidKeySpecException e) {
			Util.logger().warning("Failed to get public key from array!");
			e.printStackTrace();
			return null;
		}
	}

	private static boolean joinServer(Client client, String key) {
		GameProfile profile = ((StandardClient) client).getProfile();
		String accessToken = ((StandardClient) client).getAccessToken();
		try {
			new SessionService().joinServer(profile, accessToken, key);
			return true;
		} catch(AuthenticationUnavailableException e) {
			client.disconnect("Login failed: Authentication service unavailable.");
		} catch(InvalidCredentialsException e) {
			client.disconnect("Login failed: Invalid login session.");
		} catch(AuthenticationException e) {
			client.disconnect("Login failed: Authentication error: " + e.getMessage());
		}

		return false;
	}

	@Override
	public void accept(PacketVisitor visitor) {
		visitor.visit(this);
	}

}
