
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
	public static LanguageRegistry LANG = LanguageRegistry.instance();
	
	public static void addToolTips()
	{
		LANG.addNameForObject(BASEMOD.blockGrinder, "en_US", "Grinder");
		LANG.addNameForObject(BASEMOD.blockWasher, "en_US", "Washer");
		
		LANG.addNameForObject(BASEMOD.endiumPick, "en_US", "Endium Pickaxe");
		LANG.addNameForObject(BASEMOD.endiumAxe, "en_US", "Endium Axe");
		LANG.addNameForObject(BASEMOD.endiumShovel, "en_US", "Endium Shovel");
		
		LANG.addStringLocalization(ACH.timeToGrind.getName(), "en_US", "Time to Grind!");
		LANG.addStringLocalization(ACH.timeToGrind.getName() + ".desc", "en_US", "The matriarch of Hawk's Machinery!");
		
		LANG.addStringLocalization(ACH.minerkiin.getName(), "en_US", "Minerkiin");
		LANG.addStringLocalization(ACH.minerkiin.getName() + ".desc", "en_US", "In their tongue...");
		
		LANG.addStringLocalization(ACH.compactCompact.getName(), "en_US", "Compact Compact");
		LANG.addStringLocalization(ACH.compactCompact.getName() + ".desc", "en_US", "Am I missing, an eyebrow?");
		
		LANG.addStringLocalization(ACH.prospector.getName(), "en_US", "Prospector");
		LANG.addStringLocalization(ACH.prospector.getName() + ".desc", "en_US", "There's Emerald in them hills!");
		
		
		for (int counter = 0; counter <= 10; ++counter)
		{
			if (counter <= 3)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.blockOre, 1, counter), "en_US", HawkItemBlockOre.en_USNames[counter]);
				LANG.addNameForObject(new ItemStack(BASEMOD.blockMetalStorage, 1, counter), "en_US", HawkItemBlockMetalStorage.en_USNames[counter]);
				LANG.addNameForObject(new ItemStack(BASEMOD.plating, 1, counter), "", HawkItemPlating.en_USNames[counter] + " Plating");
				LANG.addNameForObject(new ItemStack(BASEMOD.parts, 1, counter), "", HawkItemParts.en_USNames[counter]);
			}
			
			if (counter <= 8)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.dustRaw, 1, counter), "en_US", HawkItemRawDust.en_USNames[counter] + " Dust");
			}
			
			LANG.addNameForObject(new ItemStack(BASEMOD.dustRefined), "en_US", HawkItemRefinedDust.en_USNames[counter] + " Dust");
			
		}
		
	}
}
