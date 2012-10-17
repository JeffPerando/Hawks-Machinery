
package hawksmachinery;

import hawksmachinery.interfaces.HMProcessingRecipes.HawkEnumProcessing;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public enum HMEnumUpgrades
{
	TORQUE(null),
	
	ENERGY_SAVER(null),
	
	CAPACITOR(null),
	
	POWER_PACK(null),
	
	DUSTY_TALES(HawkEnumProcessing.CRUSHING),
	
	DIRT_TO_DIAMONDS(HawkEnumProcessing.HM_E2MM),
	
	DEEP_CHILL(HawkEnumProcessing.SINTERER),
	
	GOLD_PAN(HawkEnumProcessing.WASHING);
	
	private HawkEnumProcessing machineExclusiveTo;
	
	HMEnumUpgrades(HawkEnumProcessing exclusiveMachine)
	{
		if (exclusiveMachine != null) this.machineExclusiveTo = exclusiveMachine;
		
	}
	
	public HMEnumUpgrades getUpgrade(int id)
	{
		return HMEnumUpgrades.values()[id];
	}
	
}
