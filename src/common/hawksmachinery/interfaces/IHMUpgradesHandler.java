
package hawksmachinery.interfaces;

import hawksmachinery.HMEnumUpgrades;
import hawksmachinery.tileentity.HMTileEntityMachine;

/**
 * 
 * Used in order to give a certain upgrade it's potency.
 * 
 * @author Elusivehawk
 */
public interface IHMUpgradesHandler
{
	/**
	 * 
	 * Called every tick when an upgrade is in a machine.
	 * 
	 * @param machine The machine with the upgrade in it.
	 * @param upgrades All of the upgrades that are in the machine.
	 */
	public void upgradeTick(HMTileEntityMachine machine, HMEnumUpgrades[] upgrades);
	
}
