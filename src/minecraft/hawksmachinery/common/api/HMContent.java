
package hawksmachinery.common.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;

/**
 * 
 * Just references to all of the blocks and items in HM, requested by Darkguardsman.
 * 
 * NOTICE: You will need to do a check for HM before using these, otherwise you will crash for obvious reasons.
 * 
 * @author Elusivehawk
 */
public class HMContent
{
	public static Block crusher;
	public static Block washer;
	public static Block endiumChunkloader;
	public static Block ore;
	public static Block endiumTeleporter;
	public static Block fisher;
	public static Block metalBlock;
	
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Obsidian, 6 - Endium, 7 - Cobalt.
	 */
	public static Item dustRaw;
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Emerald, 8 - Nether Star, 9 - Endium, 10 - Cobalt.
	 */
	public static Item dustRefined;
	/**
	 * Parts! 0 - Electric Pistons, 1 - Laser, 2 - Being Redone, 3 - Light Bulb, 4 - Heating Coil, 5 - Electric Magnet, 6 - Engine.
	 */
	public static Item parts;
	public static Item blueprints;
	public static Item plating;
	public static Item rivets;
	public static Item rivetGun;
	public static Item ingots;
	public static Item fishFood;
	
	public HMContent()
	{
		if (Loader.isModLoaded("HawksMachinery"))
		{
			try
			{
				crusher = (Block)Class.forName("hawksmachinery.common.block.HMBlock").getField("crusher").get(Block.class);
				washer = (Block)Class.forName("hawksmachinery.common.block.HMBlock").getField("washer").get(Block.class);
				endiumChunkloader = (Block)Class.forName("hawksmachinery.common.block.HMBlock").getField("endiumChunkloader").get(Block.class);
				ore = (Block)Class.forName("hawksmachinery.common.block.HMBlock").getField("ore").get(Block.class);
				endiumTeleporter = (Block)Class.forName("hawksmachinery.common.block.HMBlock").getField("endiumTeleporter").get(Block.class);
				fisher = (Block)Class.forName("hawksmachinery.common.block.HMBlock").getField("fisher").get(Block.class);
				
				dustRaw = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("dustRaw").get(Item.class);
				dustRefined = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("dustRefined").get(Item.class);
				parts = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("parts").get(Item.class);
				blueprints = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("blueprints").get(Item.class);
				plating = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("plating").get(Item.class);
				rivets = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("rivets").get(Item.class);
				rivetGun = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("rivetGun").get(Item.class);
				ingots = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("ingots").get(Item.class);
				fishFood = (Item)Class.forName("hawksmachinery.common.item.HMItem").getField("fishFood").get(Item.class);
				
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (SecurityException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (NoSuchFieldException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
			OreDictionary.registerOre("dustCoal", new ItemStack(dustRaw, 1, 0));
			OreDictionary.registerOre("dustRawIron", new ItemStack(dustRaw, 1, 1));
			OreDictionary.registerOre("dustRawGold", new ItemStack(dustRaw, 1, 2));
			OreDictionary.registerOre("dustRawCopper", new ItemStack(dustRaw, 1, 3));
			OreDictionary.registerOre("dustRawTin", new ItemStack(dustRaw, 1, 4));
			OreDictionary.registerOre("dustObsidian", new ItemStack(dustRaw, 1, 5));
			OreDictionary.registerOre("dustRawEndium", new ItemStack(dustRaw, 1, 6));
			
			OreDictionary.registerOre("dustDiamond", new ItemStack(dustRefined, 1, 0));
			OreDictionary.registerOre("dustEnder", new ItemStack(dustRefined, 1, 1));
			OreDictionary.registerOre("dustGlass", new ItemStack(dustRefined, 1, 2));
			OreDictionary.registerOre("dustIron", new ItemStack(dustRefined, 1, 3));
			OreDictionary.registerOre("dustGold", new ItemStack(dustRefined, 1, 4));
			OreDictionary.registerOre("dustCopper", new ItemStack(dustRefined, 1, 5));
			OreDictionary.registerOre("dustTin", new ItemStack(dustRefined, 1, 6));
			OreDictionary.registerOre("dustEmerald", new ItemStack(dustRefined, 1, 7));
			OreDictionary.registerOre("dustStar", new ItemStack(dustRefined, 1, 8));
			OreDictionary.registerOre("dustEndium", new ItemStack(dustRefined, 1, 9));
			
			OreDictionary.registerOre("ingotEndium", new ItemStack(ingots, 1, 0));
			OreDictionary.registerOre("ingotCobalt", new ItemStack(ingots, 1, 1));
			
		}
		
	}
	
}
