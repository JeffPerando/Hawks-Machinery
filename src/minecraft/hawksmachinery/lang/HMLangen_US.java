
package hawksmachinery.lang;

import hawksmachinery.HawksMachinery;
import hawksmachinery.api.HMLanguageCore.IHMLangHandler;
import hawksmachinery.block.HMBlock;
import hawksmachinery.item.HMItem;
import java.io.File;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property.Type;
import cpw.mods.fml.common.Loader;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLangen_US implements IHMLangHandler
{
	public static final String[] dustUnrefLang = new String[]{"Coal Dust", "Unrefined Iron Dust", "Unrefined Gold Dust", "Unrefined Copper Dust",
		"Unrefined Tin Dust", "Obsidian Dust", "Unrefined Endium Dust", "Unrefined Cobalt Dust"};
	
	public static final String[] dustRefLang = new String[]{"Diamond Dust", "Ender Dust", "Glass Dust", "Iron Dust",
		"Gold Dust", "Copper Dust", "Tin Dust", "Emerald Dust",
		"Star Dust", "Endium Dust", "Cobalt Dust"};
	
	public static final String[] blueprintLang = new String[]{"Bluepinrt (Crusher)", "Bluepinrt (Washer)", "Blueprint (Bottler)", "Blueprint (Fisher)",
		"Blueprint (Mini-Drill Tank)", "Blueprint (Cutter)", "Blueprint (Sinterer)", "Blueprint (Rivet Gun)",
		"Blueprint (HM-E2MM)"};
	
	public static final String[] partLang = new String[]{"Electric Piston", "Laser", "Gear Axle", "Light Bulb",
		"Heating Coil", "Electric Magnet", "Engine"};
	
	public static final String[] rivetLang = new String[]{"Copper Rivet", "Bronze Rivet", "Iron Rivet", "Steel Rivet",
		"Gold Rivet", "Endium Rivet", "Cobalt Rivet"};
	
	public static final String[] ingotLang = new String[]{"Endium Ingot", "Cobalt Ingot"};
	
	public static final String[] platingLang = new String[]{"Endium Plating", "Cobalt Plating"};
	
	public void addToolTips(String lang)
	{
		LANG.addNameForObject(HMBlock.crusher, "en_US", "Crusher");
		LANG.addNameForObject(HMBlock.washer, "en_US", "Washer");
		LANG.addNameForObject(HMBlock.endiumChunkloader, "en_US", "Endium Chunkloader");
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter), "en_US", "Endium Teleporter (Send)");
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter, 1, 1), "en_US", "Endium Teleporter (Receive)");
		LANG.addNameForObject(HMBlock.fisher, "en_US", "Fisher");
		LANG.addNameForObject(HMBlock.starForge, "en_US", "Star Forge");
		LANG.addNameForObject(HMBlock.starForgeTechnical, "en_US", "Look out, we got a badass over here!");
		LANG.addNameForObject(HMBlock.sinterer, "en_US", "Sinterer");
		LANG.addNameForObject(HMBlock.fireBlock, "en_US", "Fire Block");
		LANG.addNameForObject(new ItemStack(HMBlock.metalBlock, 1, 0), "en_US", "Endium Block");
		LANG.addNameForObject(new ItemStack(HMBlock.metalBlock, 1, 1), "en_US", "Cobalt Block");
		
		LANG.addNameForObject(new ItemStack(HMBlock.ore), "en_US", "Endium Ore");
		LANG.addNameForObject(new ItemStack(HMBlock.ore, 1, 1), "en_US", "Cobalt Ore");
		
		LANG.addNameForObject(HMItem.rivetGun, "en_US", "Rivet Gun");
		LANG.addNameForObject(HMItem.fishFood, "en_US", "Fish Food");
		
		LANG.addStringLocalization(HawksMachinery.timeToCrush.getName(), "en_US", "Time to Crush!");
		LANG.addStringLocalization(HawksMachinery.timeToCrush.getName() + ".desc", "en_US", "Craft a Crusher");
		LANG.addStringLocalization(HawksMachinery.minerkiin.getName(), "en_US", "Minerkiin");
		LANG.addStringLocalization(HawksMachinery.minerkiin.getName() + ".desc", "en_US", "Mine some Endium");
		LANG.addStringLocalization(HawksMachinery.wash.getName(), "en_US", "Workin' at the--");
		LANG.addStringLocalization(HawksMachinery.wash.getName() + ".desc", "en_US", "Craft a Washer");
		
		for (int counter = 0; counter < 16; ++counter)
		{
			if (counter < dustUnrefLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.dustRaw, 1, counter), lang, dustUnrefLang[counter]);
				
			}
			
			if (counter < dustRefLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.dustRefined, 1, counter), lang, dustRefLang[counter]);
				
			}
			
			if (counter < blueprintLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.blueprints, 1, counter), lang, blueprintLang[counter]);
				
			}
			
			if (counter < partLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.parts, 1, counter), lang, partLang[counter]);
				
			}
			
			if (counter < rivetLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.rivets, 1, counter), lang, rivetLang[counter]);
				
			}
			
			if (counter < ingotLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.ingots, 1, counter), lang, ingotLang[counter]);
				
			}

			if (counter < platingLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.plating, 1, counter), lang, platingLang[counter]);
				
			}
			
		}
		
	}
	
}
