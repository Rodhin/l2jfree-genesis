/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jfree.gameserver.network.client.packets.receivable;

import java.nio.BufferUnderflowException;

import com.l2jfree.gameserver.gameobjects.L2Player;
import com.l2jfree.gameserver.network.client.packets.L2ClientPacket;
import com.l2jfree.network.mmocore.InvalidPacketException;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * @author hex1r0
 * @author savormix (generated)
 */
public abstract class ValidatePosition extends L2ClientPacket
{
	/**
	 * A nicer name for {@link ValidatePosition}.
	 * 
	 * @author savormix (generated)
	 * @see ValidatePosition
	 */
	public static final class ReportLocation extends ValidatePosition
	{
		// only for convenience
	}
	
	/** Packet's identifier */
	public static final int OPCODE = 0x59;
	
	@Override
	protected int getMinimumLength()
	{
		return READ_D + READ_D + READ_D + READ_D + READ_D;
	}
	
	/* Fields for storing read data */
	private int _clientX;
	private int _clientY;
	private int _clientZ;
	private int _clientHeading;
	@SuppressWarnings("unused")
	private int _vehicle;
	
	@Override
	protected void read(MMOBuffer buf) throws BufferUnderflowException, RuntimeException
	{
		// TODO: when implementing, consult an up-to-date packets_game_server.xml and/or savormix
		_clientX = buf.readD(); // Current client X
		_clientY = buf.readD(); // Current client Y
		_clientZ = buf.readD(); // Current client Z
		_clientHeading = buf.readD(); // Heading
		_vehicle = buf.readD(); // Vehicle OID
	}
	
	@Override
	protected void runImpl() throws InvalidPacketException, RuntimeException
	{
		// TODO: implement
		final L2Player activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		
		activeChar.getPosition().setClientPosition(_clientX, _clientY, _clientZ, _clientHeading);
	}
}
