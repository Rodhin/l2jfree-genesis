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
package com.l2jfree.gameserver.network.loginserver.legacy.packets.sendable;

import com.l2jfree.gameserver.network.loginserver.legacy.L2LegacyLoginServer;
import com.l2jfree.gameserver.network.loginserver.legacy.packets.L2LegacyGameServerPacket;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * @author NB4L1
 */
public class PlayerAuthRequest extends L2LegacyGameServerPacket
{
	private final String _account;
	private final long _activeSessionKey;
	private final long _oldSessionKey;
	
	public PlayerAuthRequest(String account, long activeSessionKey, long oldSessionKey)
	{
		_account = account;
		_activeSessionKey = activeSessionKey;
		_oldSessionKey = oldSessionKey;
	}
	
	@Override
	protected int getOpcode()
	{
		return 0x05;
	}
	
	@Override
	protected void writeImpl(L2LegacyLoginServer client, MMOBuffer buf) throws RuntimeException
	{
		buf.writeS(_account);
		buf.writeQ(_activeSessionKey);
		buf.writeQ(_oldSessionKey);
	}
}
