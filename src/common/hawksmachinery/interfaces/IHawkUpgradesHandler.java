
package hawksmachinery.interfaces;

import hawksmachinery.HawkEnumUpgrades;
import hawksmachinery.tileentity.HawkTileEntityMachine;

/**
 * 
 * Used in order to give a certain upgrade it's potency.
 * 
 * @author Elusivehawk
 */
public interface IHawkUpgradesHandler
{
	/**
	 * 
	 * Called every tick when an upgrade is in a machine.
	 * 
	 * @param machine The machine with the upgrade in it.
	 * @param upgrades All of the upgrades that are in the machine.
	 */
	public void upgradeTick(HawkTileEntityMachine machine, HawkEnumUpgrades[] upgrades);
	
}
