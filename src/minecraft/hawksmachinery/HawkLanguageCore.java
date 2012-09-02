
package hawksmachinery;

import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * This class manages language support for Hawk's Machinery.
 * 
 * @author Elusivehawk
 */
public class HawkLanguageCore
{
	public static HawksMachinery BASEMOD;
	public static HawkAchievements ACH;
	
	public static void addToolTips()
	{
		LanguageRegistry.instance().addNameForObject(BASEMOD.blockGrinder, "en_US", "Grinder");
		LanguageRegistry.instance().addNameForObject(BASEMOD.blockWasher, "en_US", "Washer");
		
		
		LanguageRegistry.instance().addStringLocalization(ACH.timeToGrind.getName(), "en_US", "Time to Grind!");
		LanguageRegistry.instance().addStringLocalization(ACH.timeToGrind.getName() + ".desc", "en_US", "The matriarch of Hawk's Machinery!");
		
		LanguageRegistry.instance().addStringLocalization(ACH.minerkiin.getName(), "en_US", "Minerkiin");
		LanguageRegistry.instance().addStringLocalization(ACH.minerkiin.getName() + ".desc", "en_US", "In their tongue...");
		
		LanguageRegistry.instance().addStringLocalization(ACH.compactCompact.getName(), "en_US", "Compact Compact");
		LanguageRegistry.instance().addStringLocalization(ACH.compactCompact.getName() + ".desc", "en_US", "Am I missing, an eyebrow?");
		
		LanguageRegistry.instance().addStringLocalization(ACH.prospector.getName(), "en_US", "Prospector");
		LanguageRegistry.instance().addStringLocalization(ACH.prospector.getName() + ".desc", "en_US", "There's Emerald in them hills!");
		
		
		for (int counter = 0; counter <= 3; ++counter)
		{
			LanguageRegistry.instance().addNameForObject(new ItemStack(BASEMOD.blockOre, 1, counter), "en_US", HawkItemBlockOre.en_USNames[counter]);
			LanguageRegistry.instance().addNameForObject(new ItemStack(BASEMOD.blockMetalStorage, 1, counter), "en_US", HawkItemBlockMetalStorage.en_USNames[counter]);
		}
		
		//TODO: Convert item naming system over to here.
		
	}
}
