package org.spacehq.mcprotocol.net;

/**
 * A server's connection to a client.
 */
public interface ServerConnection extends Connection {

	/**
	 * Gets the server this connection belongs to.
	 * @return The connection's server.
	 */
	public Server getServer();

}
