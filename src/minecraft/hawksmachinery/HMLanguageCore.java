
package hawksmachinery;

import java.lang.reflect.Array;
import hawksmachinery.blocks.*;
import hawksmachinery.items.*;
import net.minecraft.src.Item;
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
	public static final String[] langs = new String[]{"en_US"};
	
	public static void addToolTips()
	{
		LANG.addNameForObject(HMBlock.crusher, "en_US", "Crusher");
		LANG.addNameForObject(HMBlock.washer, "en_US", "Washer");
		LANG.addNameForObject(HMBlock.endiumOre, "en_US", "Endium Ore");
		if (BASEMOD.MANAGER.enableChunkloader)
		{
			LANG.addNameForObject(HMBlock.endiumChunkloader, "en_US", "Endium Chunkloader");
		}
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter), "en_US", "Endium Teleporter (Send)");
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter, 1, 1), "en_US", "Endium Teleporter (Receive)");
		
		LANG.addNameForObject(HMItem.rivetGun, "en_US", "Rivet Gun");
		LANG.addNameForObject(new ItemStack(HMItem.endiumPlate), "en_US", "Endium Plate");
		
		LANG.addStringLocalization(BASEMOD.timeToCrush.getName(), "en_US", "Time to Crush!");
		LANG.addStringLocalization(BASEMOD.timeToCrush.getName() + ".desc", "en_US", "Craft a Crusher.");
		LANG.addStringLocalization(BASEMOD.minerkiin.getName(), "en_US", "Minerkiin");
		LANG.addStringLocalization(BASEMOD.minerkiin.getName() + ".desc", "en_US", "Mine some Endium.");
		LANG.addStringLocalization(BASEMOD.wash.getName(), "en_US", "Workin' at the--");
		LANG.addStringLocalization(BASEMOD.wash.getName() + ".desc", "en_US", "Craft a Washer.");
		
		for (String lang : langs)
		{
			for (int counter = 0; counter < 16; ++counter)
			{
				try
				{
					if (counter < Array.getLength(HMItemIngots.class.getField(lang + "Names").get(Array.class)))
					{
						LANG.addNameForObject(new ItemStack(HMItem.ingots, 1, counter), lang, HMItemIngots.en_USNames[counter] + " Ingot");
						
					}
					
					if (counter < Array.getLength(HMItemRivets.class.getField(lang + "Names").get(Array.class)))
					{
						LANG.addNameForObject(new ItemStack(HMItem.rivets, 1, counter), lang, HMItemRivets.en_USNames[counter] + " Rivet");
						
					}
					
					if (counter < Array.getLength(HMItemRawDust.class.getField(lang + "Names").get(Array.class)))
					{
						if (HMItemRawDust.en_USNames[counter] != "Coal" && HMItemRawDust.en_USNames[counter] != "Obsidian")
						{
							LANG.addNameForObject(new ItemStack(HMItem.dustRaw, 1, counter), lang, "Unrefined " + HMItemRawDust.en_USNames[counter] + " Dust");
						}
						else
						{
							LANG.addNameForObject(new ItemStack(HMItem.dustRaw, 1, counter), lang, HMItemRawDust.en_USNames[counter] + " Dust");
						}
						
					}
					
					if (counter < Array.getLength(HMItemParts.class.getField(lang + "Names").get(Array.class)))
					{
						LANG.addNameForObject(new ItemStack(HMItem.parts, 1, counter), lang, HMItemParts.en_USNames[counter]);
						
					}
					
					if (counter < Array.getLength(HMItemBlueprints.class.getField(lang + "Names").get(Array.class)))
					{
						LANG.addNameForObject(new ItemStack(HMItem.blueprints, 1, counter), lang, "Blueprints (" + HMItemBlueprints.en_USNames[counter] + ")");
						
					}
					
					if (counter < Array.getLength(HMItemRefinedDust.class.getField(lang + "Names").get(Array.class)))
					{
						LANG.addNameForObject(new ItemStack(HMItem.dustRefined, 1, counter), lang, HMItemRefinedDust.en_USNames[counter] + " Dust");
						
					}
					
					if (counter < Array.getLength(HMBlockOre.class.getField(lang + "Names").get(Array.class)))
					{
						LANG.addNameForObject(new ItemStack(HMBlock.endiumOre, 1, counter), lang, HMBlockOre.en_USNames[counter]);
						
					}
					
				}
				catch (IllegalArgumentException e)
				{
					e.printStackTrace();
				}
				catch (SecurityException e)
				{
					e.printStackTrace();
				}
				catch (NoSuchFieldException e)
				{
					e.printStackTrace();
				}
				catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
}
