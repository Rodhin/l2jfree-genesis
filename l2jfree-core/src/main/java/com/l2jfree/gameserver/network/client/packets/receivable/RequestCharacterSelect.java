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

import com.l2jfree.gameserver.network.client.L2ClientState;
import com.l2jfree.gameserver.network.client.packets.L2ClientPacket;
import com.l2jfree.network.mmocore.InvalidPacketException;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * @author hex1r0
 */
public class RequestCharacterSelect extends L2ClientPacket
{
	public static final int OPCODE = 0x12;
	private int _charSlot;
	
	@Override
	protected int getMinimumLength()
	{
		return READ_D + READ_H + READ_D + READ_D + READ_D;
	}
	
	@Override
	protected void read(MMOBuffer buf) throws BufferUnderflowException, RuntimeException
	{
		_charSlot = buf.readD();
		/*_unk1 = */buf.readH();
		/*_unk2 = */buf.readD();
		/*_unk3 = */buf.readD();
		/*_unk4 = */buf.readD();
	}
	
	@Override
	protected void runImpl() throws InvalidPacketException, RuntimeException
	{
		// TODO
		getClient().setState(L2ClientState.LOGGED_IN);
		//sendPacket(new CharSelected(cha, getClient().getSessionId().playOkID1));
		sendActionFailed();
	}
	
}