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
package com.l2jfree.gameserver.network.client.packets.sendable;

import com.l2jfree.gameserver.gameobjects.L2Player;
import com.l2jfree.gameserver.network.client.L2Client;
import com.l2jfree.gameserver.network.client.packets.L2ServerPacket;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * @author savormix (generated)
 */
public abstract class ExNavitAdventTimeChange extends L2ServerPacket
{
	/**
	 * A nicer name for {@link ExNavitAdventTimeChange}.
	 * 
	 * @author savormix (generated)
	 * @see ExNavitAdventTimeChange
	 */
	public static final class NevitAdventTimeUpdate extends ExNavitAdventTimeChange
	{
		/**
		 * Constructs this packet.
		 * 
		 * @see ExNavitAdventTimeChange#ExNavitAdventTimeChange()
		 */
		public NevitAdventTimeUpdate()
		{
		}
	}
	
	private static final int[] EXT_OPCODES = { 0xe1, 0x00 };
	
	/** Constructs this packet. */
	public ExNavitAdventTimeChange()
	{
	}
	
	@Override
	protected int getOpcode()
	{
		return 0xfe;
	}
	
	@Override
	protected int[] getAdditionalOpcodes()
	{
		return EXT_OPCODES;
	}
	
	@Override
	protected void writeImpl(L2Client client, L2Player activeChar, MMOBuffer buf) throws RuntimeException
	{
		// TODO: when implementing, consult an up-to-date packets_game_server.xml and/or savormix
		buf.writeC(0); // Time paused
		buf.writeD(0); // Advent bonus time left
	}
}
