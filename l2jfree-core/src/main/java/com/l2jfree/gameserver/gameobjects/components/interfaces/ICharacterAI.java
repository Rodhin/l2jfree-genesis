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
package com.l2jfree.gameserver.gameobjects.components.interfaces;

import com.l2jfree.gameserver.gameobjects.L2Object;
import com.l2jfree.gameserver.gameobjects.ai.AIDesire;
import com.l2jfree.gameserver.gameobjects.components.IComponent;

/**
 * @author hex1r0
 */
public interface ICharacterAI extends IComponent
{
	public void worldRegionActivated();
	
	public void worldRegionDeactivated();
	
	public void addDesire(AIDesire desire);
	
	public void setDesire(AIDesire desire);
	
	public void clearDesires();
	
	public void onEventArrived();
	
	public void onIntentionOnAction(L2Object target, boolean cantMove, boolean forceAttack);
	
	public void onIntentionMove(int x, int y, int z);
	
	// TODO	
}
