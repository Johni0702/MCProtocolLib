package org.spacehq.mcprotocol.net;

import org.spacehq.mcprotocol.exception.LoginException;
import org.spacehq.mcprotocol.exception.OutdatedLibraryException;

/**
 * A client connected to a server.
 */
public interface Client extends Connection {

	/**
	 * Logs the client in using the given username and password.
	 * @param username Username to login with.
	 * @param password Password to login with.
	 * @return Whether the login was successful.
	 */
	public boolean login(String username, String password) throws LoginException, OutdatedLibraryException;

}
