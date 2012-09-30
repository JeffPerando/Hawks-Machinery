
package hawksmachinery;

import hawksmachinery.HawkProcessingRecipes.HawkEnumProcessing;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public enum HawkEnumUpgrades
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
	
	HawkEnumUpgrades(HawkEnumProcessing exclusiveMachine)
	{
		if (exclusiveMachine != null) this.machineExclusiveTo = exclusiveMachine;
		
	}
	
	public HawkEnumUpgrades getUpgrade(int id)
	{
		return HawkEnumUpgrades.values()[id];
	}
	
}
