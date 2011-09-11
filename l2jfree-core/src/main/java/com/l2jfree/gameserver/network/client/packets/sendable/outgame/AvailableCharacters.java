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
package com.l2jfree.gameserver.network.client.packets.sendable.outgame;

import java.util.List;

import com.l2jfree.gameserver.network.client.L2Client;
import com.l2jfree.gameserver.network.client.packets.L2ServerPacket;
import com.l2jfree.gameserver.sql.PlayerDB;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * Original name was <TT>CharSelectionInfo</TT>
 * 
 * @author hex1r0
 */
public class AvailableCharacters extends L2ServerPacket
{
	private final String _accountName;
	private final int _sessionId;
	private final List<PlayerDB> _players;
	
	public AvailableCharacters(L2Client client)
	{
		_sessionId = client.getSessionId(); // TODO
		_accountName = client.getAccountName();
		_players = PlayerDB.findByAccount(_accountName); // FIXME don't fetch the whole player only thee required data
		
		client.definePlayerSlots(_players);
	}
	
	@Override
	protected int getOpcode()
	{
		return 0x09;
	}
	
	@Override
	protected void writeImpl(L2Client client, MMOBuffer buf) throws RuntimeException
	{
		buf.writeD(_players.size());
		buf.writeD(0x07);
		buf.writeC(0);
		
		for (PlayerDB p : _players)
		{
			buf.writeS(p.name);
			buf.writeD(p.objectId);
			buf.writeS(_accountName);
			buf.writeD(_sessionId);
			buf.writeD(0); // TODO clan id
			buf.writeD(0); // ??
			
			buf.writeD(p.gender); // TODO sex
			buf.writeD(p.baseClassId.getRace()); // TODO race
			
			buf.writeD(p.baseClassId); // TODO class id
			
			buf.writeD(0x01); // active ?? (no difference between 0 and 1)
			
			buf.writeD(p.x);
			buf.writeD(p.y);
			buf.writeD(p.z);
			
			buf.writeF(100.0); // TODO hp cur
			buf.writeF(100.0); // TODO mp cur
			
			buf.writeD(50); // TODO SP
			buf.writeQ(50); // TODO EXP
			buf.writeF(0.5); // TODO EXP % HF
			buf.writeD(5); // TODO Level 
			
			buf.writeD(5); // TODO karma
			buf.writeD(15); // TODO pk
			buf.writeD(15); // TODO PVP
			
			for (int k = 0; k < 7; k++)
				buf.writeD(0x00);
			
			for (int slot = 0; slot < 26; slot++)
				buf.writeD(0); // TODO PaperdollSlots
			
			buf.writeD(p.hairStyle); // TODO hair style
			buf.writeD(p.hairColor); // TODO hair color
			buf.writeD(p.face); // TODO face
			
			buf.writeF(200.0); // TODO hp max
			buf.writeF(200.0); // TODO mp max
			
			buf.writeD(0x00); // TODO delete days left before
			buf.writeD(p.activeClassId); // TODO class id
			buf.writeD(1); // TODO active
			buf.writeC(0); // TODO enchant effect
			buf.writeD(0); // TODO augmentation
			
			buf.writeD(0); // TODO transformation
			
			buf.writeD(0x00);
			buf.writeD(0x00);
			buf.writeD(0x00);
			buf.writeD(0x00);
			buf.writeF(0x00);
			buf.writeF(0x00);
			
			buf.writeD(0); // vitallity points HF
		}
		// breaks L2Object.setState()
		//for (L2Player player : _players)
		//player.removeFromWorld(); // FIXME don't fetch the whole player only the required data
	}
}