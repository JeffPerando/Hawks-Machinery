
package hawksmachinery.lang;

import java.io.File;
import cpw.mods.fml.common.Loader;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property.Type;
import hawksmachinery.HawksMachinery;
import hawksmachinery.api.HMLanguageCore.IHMLangHandler;
import hawksmachinery.block.HMBlock;
import hawksmachinery.item.HMItem;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLangen_US implements IHMLangHandler
{
	public static Configuration langFile = new Configuration(new File(Loader.instance().getConfigDir() + "/HawksMachinery/lang/en_US.cfg"));
	public static final String catBlock = "Blocks";
	public static final String catItem = "Items";
	public static final String catAch = "Achievements";
	
	public static final String[] dustUnrefLang = new String[]{"Coal Dust", "Unrefined Iron Dust", "Unrefined Gold Dust", "Unrefined Copper Dust",
		"Unrefined Tin Dust", "Obsidian Dust", "Unrefined Endium Dust", "Unrefined Cobalt Dust"};
	
	public static final String[] dustRefLang = new String[]{"Diamond Dust", "Ender Dust", "Glass Dust", "Iron Dust",
		"Gold Dust", "Copper Dust", "Tin Dust", "Emerald Dust",
		"Star Dust", "Endium Dust", "Cobalt Dust"};
	
	public static final String[] blueprintLang = new String[]{"Bluepinrt (Crusher)", "Bluepinrt (Washer)", "Blueprint (Bottler)", "Blueprint (Fisher)",
		"Blueprint (Mini-Drill Tank)", "Blueprint (Cutter)", "Blueprint (Sinterer)", "Blueprint (Rivet Gun)",
		"Blueprint (HM-E2MM)"};
	
	public static final String[] partLang = new String[]{"Electric Piston", "Laser", "I have NO idea!", "Light Bulb",
		"Heating Coil", "Electric Magnet", "Engine"};
	
	public static final String[] rivetLang = new String[]{"Copper Rivet", "Bronze Rivet", "Iron Rivet", "Steel Rivet",
		"Gold Rivet", "Endium Rivet", "Cobalt Rivet"};
	
	public static final String[] ingotLang = new String[]{"Endium Ingot", "Cobalt Ingot"};
	
	public static final String[] platingLang = new String[]{"Endium Plating", "Cobalt Plating"};
	
	public void addToolTips(String lang)
	{
		langFile.load();
		
		LANG.addNameForObject(HMBlock.crusher, "en_US", langFile.get(catBlock, "block.crusher", "Crusher", Type.STRING).value);
		LANG.addNameForObject(HMBlock.washer, "en_US", langFile.get(catBlock, "block.washer", "Washer", Type.STRING).value);
		LANG.addNameForObject(HMBlock.endiumChunkloader, "en_US", langFile.get(catBlock, "block.chunkloader", "Endium Chunkloader", Type.STRING).value);
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter), "en_US", langFile.get(catBlock, "block.telesend", "Endium Teleporter (Send)", Type.STRING).value);
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter, 1, 1), "en_US", langFile.get(catBlock, "block.telereceive", "Endium Teleporter (Receive)", Type.STRING).value);
		LANG.addNameForObject(HMBlock.fisher, "en_US", "Fisher");
		LANG.addNameForObject(new ItemStack(HMBlock.metalBlock, 1, 0), "en_US", "Endium Block");
		LANG.addNameForObject(new ItemStack(HMBlock.metalBlock, 1, 1), "en_US", "Cobalt Block");
		
		LANG.addNameForObject(new ItemStack(HMBlock.ore), "en_US", langFile.get(catBlock, "block.ore0", "Endium Ore", Type.STRING).value);
		LANG.addNameForObject(new ItemStack(HMBlock.ore, 1, 1), "en_US", langFile.get(catBlock, "block.ore1", "Cobalt Ore", Type.STRING).value);
		
		LANG.addNameForObject(HMItem.rivetGun, "en_US", langFile.get(catItem, "item.rivetgun", "Rivet Gun", Type.STRING).value);
		LANG.addNameForObject(HMItem.fishFood, "en_US", langFile.get(catItem, "item.fishfood", "Fish Food", Type.STRING).value);
		
		LANG.addStringLocalization(HawksMachinery.timeToCrush.getName(), "en_US", langFile.get(catAch, "ach.crush", "Time to Crush!", Type.STRING).value);
		LANG.addStringLocalization(HawksMachinery.timeToCrush.getName() + ".desc", "en_US", langFile.get(catAch, "ach.crush.desc", "Craft a Crusher", Type.STRING).value);
		LANG.addStringLocalization(HawksMachinery.minerkiin.getName(), "en_US", langFile.get(catAch, "ach.minerkiin", "Minerkiin", Type.STRING).value);
		LANG.addStringLocalization(HawksMachinery.minerkiin.getName() + ".desc", "en_US", langFile.get(catBlock, "ach.minerkiin.desc", "Mine some Endium", Type.STRING).value);
		LANG.addStringLocalization(HawksMachinery.wash.getName(), "en_US", langFile.get(catAch, "ach.wash", "Workin' at the--", Type.STRING).value);
		LANG.addStringLocalization(HawksMachinery.wash.getName() + ".desc", "en_US", langFile.get(catBlock, "ach.wash.desc", "Craft a Washer", Type.STRING).value);
		
		for (int counter = 0; counter < 16; ++counter)
		{
			if (counter < dustUnrefLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.dustRaw, 1, counter), lang, langFile.get(catItem, "item.dustunref" + counter, dustUnrefLang[counter]).value);
				
			}
			
			if (counter < dustRefLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.dustRefined, 1, counter), lang, langFile.get(catItem, "item.dustref" + counter, dustRefLang[counter]).value);
				
			}
			
			if (counter < blueprintLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.blueprints, 1, counter), lang, langFile.get(catItem, "item.blueprint" + counter, blueprintLang[counter]).value);
				
			}
			
			if (counter < partLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.parts, 1, counter), lang, langFile.get(catItem, "item.part" + counter, partLang[counter]).value);
				
			}
			
			if (counter < rivetLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.rivets, 1, counter), lang, langFile.get(catItem, "item.rivet" + counter, rivetLang[counter]).value);
				
			}
			
			if (counter < ingotLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.ingots, 1, counter), lang, langFile.get(catItem, "item.ingot" + counter, ingotLang[counter]).value);
			}

			if (counter < platingLang.length)
			{
				LANG.addNameForObject(new ItemStack(HMItem.plating, 1, counter), lang, langFile.get(catItem, "item.plating" + counter, platingLang[counter]).value);
			}
			
		}
		
		langFile.save();
		
	}
	
}
