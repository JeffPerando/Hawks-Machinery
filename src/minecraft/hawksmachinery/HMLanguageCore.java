
package hawksmachinery;

import hawksmachinery.items.*;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * This class manages language support for Hawk's Machinery.
 * 
 * @author Elusivehawk
 */
public class HMLanguageCore
{
	public static HawksMachinery BASEMOD;
	public static LanguageRegistry LANG = LanguageRegistry.instance();
	
	public static void addToolTips()
	{
		LANG.addNameForObject(BASEMOD.crusher, "en_US", "Crusher");
		LANG.addNameForObject(BASEMOD.washer, "en_US", "Washer");
		if (BASEMOD.MANAGER.enableChunkloader)
		{
			LANG.addNameForObject(BASEMOD.chunkloader, "en_US", "Endium Chunkloader");
		}
		
		LANG.addNameForObject(BASEMOD.rivetGun, "en_US", "Rivet Gun");
		                         
		LANG.addStringLocalization(BASEMOD.timeToCrush.getName(), "en_US", "Time to Crush!");
		LANG.addStringLocalization(BASEMOD.timeToCrush.getName() + ".desc", "en_US", "The matriarch of Hawk's Machinery!");
		
		LANG.addStringLocalization(BASEMOD.prospector.getName(), "en_US", "Prospector");
		LANG.addStringLocalization(BASEMOD.prospector.getName() + ".desc", "en_US", "There's Emerald in them hills!");
		
		LANG.addStringLocalization(BASEMOD.wash.getName(), "en_US", "Workin' at the--");
		LANG.addStringLocalization(BASEMOD.wash.getName() + ".desc", "en_US", "Wait, what?");
		
		for (int counter = 0; counter <= 9; ++counter)
		{
			if (counter <= 5)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.parts, 1, counter), "en_US", HMItemParts.en_USNames[counter]);
				LANG.addNameForObject(new ItemStack(BASEMOD.rivets, 1, counter), "en_US", HMItemRivets.en_USNames[counter] + " Rivet");
				
			}
			
			if (counter <= 6)
			{
				if (HMItemRawDust.en_USNames[counter] != "Coal" && HMItemRawDust.en_USNames[counter] != "Obsidian")
				{
					LANG.addNameForObject(new ItemStack(BASEMOD.dustRaw, 1, counter), "en_US", "Unrefined " + HMItemRawDust.en_USNames[counter] + " Dust");
				}
				else
				{
					LANG.addNameForObject(new ItemStack(BASEMOD.dustRaw, 1, counter), "en_US", HMItemRawDust.en_USNames[counter] + " Dust");
				}
				
			}
			
			if (counter <= 8)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.blueprints, 1, counter), "en_US", "Blueprints (" + HMItemBlueprints.en_USNames[counter] + ")");
			}
			
			LANG.addNameForObject(new ItemStack(BASEMOD.dustRefined, 1, counter), "en_US", HMItemRefinedDust.en_USNames[counter] + " Dust");
			
		}
		
	}
	
}
