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

/**
 * @author savormix (generated)
 */
public abstract class ExCloseMPCCPacket extends StaticPacket
{
	/**
	 * A nicer name for {@link ExCloseMPCCPacket}.
	 * 
	 * @author savormix (generated)
	 * @see ExCloseMPCCPacket
	 */
	public static final class CommandChannelClosed extends ExCloseMPCCPacket
	{
		/** This packet. */
		public static final CommandChannelClosed PACKET = new CommandChannelClosed();
		
		/**
		 * Constructs this packet.
		 * 
		 * @see ExCloseMPCCPacket#ExCloseMPCCPacket()
		 */
		private CommandChannelClosed()
		{
		}
	}
	
	private static final int[] EXT_OPCODES = { 0x13, 0x00 };
	
	/** Constructs this packet. */
	public ExCloseMPCCPacket()
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
}
