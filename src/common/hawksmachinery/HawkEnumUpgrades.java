
package hawksmachinery;

import hawksmachinery.HawkProcessingRecipes.HawkEnumProcessing;;

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
	
	DUSTY_TALES(HawkProcessingRecipes.HawkEnumProcessing.CRUSHING),
	
	DIRT_TO_DIAMONDS(HawkProcessingRecipes.HawkEnumProcessing.HM_E2MM),
	
	DEEP_CHILL(HawkProcessingRecipes.HawkEnumProcessing.SINTERER),
	
	GOLD_PAN(HawkProcessingRecipes.HawkEnumProcessing.WASHING);
	
	private HawkProcessingRecipes.HawkEnumProcessing machineExclusiveTo;
	
	HawkEnumUpgrades(HawkEnumProcessing exclusiveMachine)
	{
		if (exclusiveMachine != null) this.machineExclusiveTo = exclusiveMachine;
		
	}
	
	public HawkEnumUpgrades getUpgrade(int id)
	{
		return HawkEnumUpgrades.values()[id];
	}
	
}
