
package hawksmachinery;

import hawksmachinery.itemblocks.HawkItemBlockMetalStorage;
import hawksmachinery.itemblocks.HawkItemBlockOre;
import hawksmachinery.items.*;
import hawksmachinery.misc.HawkAchievements;
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
		
		LANG.addStringLocalization(ACH.timeToGrind.getName(), "en_US", "Time to Grind!");
		LANG.addStringLocalization(ACH.timeToGrind.getName() + ".desc", "en_US", "The matriarch of Hawk's Machinery!");
		
		LANG.addStringLocalization(ACH.compactCompact.getName(), "en_US", "Compact Compact");
		LANG.addStringLocalization(ACH.compactCompact.getName() + ".desc", "en_US", "Am I missing, an eyebrow?");
		
		LANG.addStringLocalization(ACH.prospector.getName(), "en_US", "Prospector");
		LANG.addStringLocalization(ACH.prospector.getName() + ".desc", "en_US", "There's Emerald in them hills!");
		
		LANG.addStringLocalization(ACH.minerkiin.getName(), "en_US", "Minerkiin");
		LANG.addStringLocalization(ACH.minerkiin.getName() + ".desc", "en_US", "In their tongue...");
		
		for (int counter = 0; counter <= 11; ++counter)
		{
			if (counter <= 3)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.blockOre, 1, counter), "en_US", HawkItemBlockOre.en_USNames[counter] + " Ore");
				LANG.addNameForObject(new ItemStack(BASEMOD.blockMetalStorage, 1, counter), "en_US", HawkItemBlockMetalStorage.en_USNames[counter] + " Block");
				LANG.addNameForObject(new ItemStack(BASEMOD.ingots, 1, counter), "en_US", HawkItemIngots.en_USNames[counter] + " Ingot");
				LANG.addNameForObject(new ItemStack(BASEMOD.plating, 1, counter), "en_US", HawkItemPlating.en_USNames[counter] + " Plating");
				
			}
			
			if (counter <= 5)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.parts, 1, counter), "en_US", HawkItemParts.en_USNames[counter]);
				
			}
			
			if (counter <= 8)
			{
				if (HawkItemRawDust.en_USNames[counter] != "Coal" && HawkItemRawDust.en_USNames[counter] != "Obsidian")
				{
					LANG.addNameForObject(new ItemStack(BASEMOD.dustRaw, 1, counter), "en_US", "Unrefined " + HawkItemRawDust.en_USNames[counter] + " Dust");
				}
				else
				{
					LANG.addNameForObject(new ItemStack(BASEMOD.dustRaw, 1, counter), "en_US", HawkItemRawDust.en_USNames[counter] + " Dust");
				}
				
			}
			
			if (counter <= 10)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.dustRefined, 1, counter), "en_US", HawkItemRefinedDust.en_USNames[counter] + " Dust");
				
			}
			
			LANG.addNameForObject(new ItemStack(BASEMOD.blueprints, 1, counter), "en_US", "Blueprints (" + HawkItemBlueprints.en_USNames[counter] + ")");
			
		}
		
	}
	
}
