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
package com.l2jfree.gameserver.gameobjects;

import java.util.Collection;

/**
 * @author NB4L1
 */
public interface IObjectKnownList
{
	public Collection<L2Object> getKnownObjects();
	
	public Collection<L2PcInstance> getKnownPlayers();
	
	public Collection<L2Object> getKnowingObjects();
	
	public boolean removeObject(L2Object activeChar);
	
	public void addKnowingObject(L2Object activeChar);
	
	public void removeKnowingObject(L2Object activeChar);
	
	public void update(L2Object activeChar);
	
	public void update(L2Object[][] surroundingObjects);
}