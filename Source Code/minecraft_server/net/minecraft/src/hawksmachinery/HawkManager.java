
package net.minecraft.src.hawksmachinery;

import java.io.*;
import net.minecraft.server.*;
import net.minecraft.src.*;
import net.minecraft.src.basiccomponents.BasicComponents;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.ore.UEOreManager;
import net.minecraft.src.universalelectricity.recipe.UERecipeManager;

/**
 * 
 * 
 * This class takes cares and manages of all general things
 * 
 * @author Elusivehawk
 */
public class HawkManager
{
	public static int dustID;
	
	public static int machineBlockID;
	public static int grinderID;
	
	public static final String GUI_PATH = "/hawksmachinery/textures/gui";
	public static final String CONFIG_PATH = "/config/HawksMachinery";
	
	public static final String BLOCK_TEXTURE_FILE = "/hawksmachinery/textures/blocks.png";
	public static final String ITEM_TEXTURE_FILE = "/hawksmachinery/textures/items.png";
	
	public static File CONFIGURATION = (new File(CONFIG_PATH + "/Config.cfg"));

	public static int initProps()
	{	
		try
		{
			CONFIGURATION.createNewFile();
			System.out.println("Hawk's Machinery: Config file created/read.");
		}
		catch (IOException e)
		{
			System.out.println("Hawk's Machinery: Something went wrong while creating the config file. Reason: ");
			System.out.println(e);
		}
		
		Configuration HMConfig = new Configuration(CONFIGURATION);
		
		HMConfig.load();
		
		grinderID = HMConfig.getOrCreateIntProperty("Grinder", Configuration.CATEGORY_BLOCK, 3960).getInt(3960);
		machineBlockID = HMConfig.getOrCreateIntProperty("Machine Block", Configuration.CATEGORY_BLOCK, 3961).getInt(3961);

		dustID	= HMConfig.getOrCreateIntProperty("Coal Dust", Configuration.CATEGORY_ITEM, 24150).getInt(24150);
	
		HMConfig.save();
		
		return grinderID;
	}

	public static mod_HawksMachinery getModInstance()
	{
		return mod_HawksMachinery.instance;
	}
	
	/**
	 * Loads all of the recipes for Hawk's Machinery, including regular crafting recipes.
	 */
	public static void loadRecipes()
	{
		UERecipeManager.addRecipe((new ItemStack(mod_HawksMachinery.blockEmptyMachine, 1)), new Object[]{"oxo", "xvx", "oxo", 'o', BasicComponents.ItemSteelIngot, 'x', BasicComponents.ItemSteelPlate, 'v', Item.blazePowder});
		UERecipeManager.addRecipe((new ItemStack(mod_HawksMachinery.blockGrinder, 1)), new Object[]{"xix", "xpx", "xex", 'x', BasicComponents.ItemSteelIngot, 'p', Item.pickaxeSteel, 'e', mod_HawksMachinery.blockEmptyMachine, 'i', new ItemStack(BasicComponents.ItemCircuit, 1, 1)});
		UERecipeManager.addRecipe((new ItemStack(BasicComponents.ItemSteelIngot, 24)), new Object[]{"e", 'e', mod_HawksMachinery.blockEmptyMachine});
		UERecipeManager.addRecipe((new ItemStack(BasicComponents.ItemBattery)), new Object[]{" x ", "xrx", "xcx", 'x', BasicComponents.ItemTinIngot, 'c', new ItemStack(mod_HawksMachinery.dust, 1, 0), 'r', Item.redstone});
		UERecipeManager.addRecipe((new ItemStack(Block.torchWood, 4)), new Object[]{"c", "s", 'c', new ItemStack(mod_HawksMachinery.dust, 1, 0), 's', Item.stick});
		UERecipeManager.addRecipe((new ItemStack(Block.enchantmentTable, 1)), new Object[]{" b ", "dod", "ooo", 'b', Item.book, 'd', new ItemStack(mod_HawksMachinery.dust, 1, 1), 'o', Block.obsidian});

		
		UERecipeManager.addShapelessRecipe((new ItemStack(BasicComponents.ItemSteelClump, 1)), new Object[]{Item.ingotIron, new ItemStack(mod_HawksMachinery.dust, 1, 0)});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.dust, 2, 8)), new Object[]{Item.bucketWater, new ItemStack(mod_HawksMachinery.dust, 1, 5), new ItemStack(mod_HawksMachinery.dust, 1, 5)});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.dust, 2, 9)), new Object[]{Item.bucketWater, new ItemStack(mod_HawksMachinery.dust, 1, 2), new ItemStack(mod_HawksMachinery.dust, 1, 2)});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.dust, 2, 10)), new Object[]{Item.bucketWater, new ItemStack(mod_HawksMachinery.dust, 1, 6), new ItemStack(mod_HawksMachinery.dust, 1, 6)});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.dust, 2, 11)), new Object[]{Item.bucketWater, new ItemStack(mod_HawksMachinery.dust, 1, 7), new ItemStack(mod_HawksMachinery.dust, 1, 7)});
		UERecipeManager.addShapelessRecipe((new ItemStack(BasicComponents.ItemSteelIngot, 5)), new Object[]{BasicComponents.ItemSteelPlate});
		UERecipeManager.addShapelessRecipe((new ItemStack(Item.pumpkinSeeds, 4)), new Object[]{Block.pumpkinLantern});
		UERecipeManager.addShapelessRecipe((new ItemStack(Item.fireballCharge, 3)), new Object[]{Item.gunpowder, Item.blazePowder, new ItemStack(mod_HawksMachinery.dust, 1, 0)});

		
		UERecipeManager.addSmelting(new ItemStack(mod_HawksMachinery.dust, 1, 4), new ItemStack(Block.thinGlass));
		UERecipeManager.addSmelting(new ItemStack(mod_HawksMachinery.dust, 1, 8), new ItemStack(Item.ingotIron));
		UERecipeManager.addSmelting(new ItemStack(mod_HawksMachinery.dust, 1, 9), new ItemStack(Item.ingotGold));
		UERecipeManager.addSmelting(new ItemStack(mod_HawksMachinery.dust, 1, 10), new ItemStack(BasicComponents.ItemCopperIngot));
		UERecipeManager.addSmelting(new ItemStack(mod_HawksMachinery.dust, 1, 11), new ItemStack(BasicComponents.ItemTinIngot));
		UERecipeManager.addSmelting(new ItemStack(mod_HawksMachinery.dust, 1, 12), new ItemStack(Block.obsidian));

		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.diamond.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 1, 1), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.oreGold.blockID, new ItemStack(mod_HawksMachinery.dust, 2, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.enderPearl.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 1, 3), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.glass.blockID, new ItemStack(mod_HawksMachinery.dust, 4, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.oreIron.blockID, new ItemStack(mod_HawksMachinery.dust, 2, 5), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(BasicComponents.ItemCopperIngot.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 1, 10), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(BasicComponents.ItemTinIngot.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 1, 11), 1);		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.blazeRod.shiftedIndex, new ItemStack(Item.blazePowder, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bone.shiftedIndex, new ItemStack(Item.dyePowder, 3, 15), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.stone.blockID, new ItemStack(Block.gravel), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.cobblestone.blockID, new ItemStack(Block.sand), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.gravel.blockID, new ItemStack(Item.flint), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.eyeOfEnder.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 1, 3), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pumpkin.blockID, new ItemStack(Item.pumpkinSeeds, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pistonBase.blockID, new ItemStack(mod_HawksMachinery.dust, 1, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pistonStickyBase.blockID, new ItemStack(mod_HawksMachinery.dust, 1, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pumpkinLantern.blockID, new ItemStack(Item.pumpkinSeeds, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.dispenser.blockID, new ItemStack(Item.bow), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.stoneOvenIdle.blockID, new ItemStack(Block.cobblestone, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.thinGlass.blockID, new ItemStack(mod_HawksMachinery.dust, 1, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.glowStone.blockID, new ItemStack(Item.lightStoneDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.redstoneLampIdle.blockID, new ItemStack(Item.lightStoneDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.enchantmentTable.blockID, new ItemStack(Item.diamond, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.brewingStand.shiftedIndex, new ItemStack(Item.blazeRod, 1), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.sandStone.blockID, new ItemStack(Block.sand, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.obsidian.blockID, new ItemStack(mod_HawksMachinery.dust, 1, 12), 1);

		HawkProcessingRecipes.addHawkProcessingRecipe(Item.helmetSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 5, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.plateSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 8, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.legsSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 7, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bootsSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 4, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.helmetGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 5, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.plateGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 8, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.legsGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 7, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bootsGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 4, 9), 1);

		HawkProcessingRecipes.addHawkProcessingRecipe(Item.swordSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 2, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.pickaxeSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 3, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.shovelSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 1, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.axeSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 3, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.hoeSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 2, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.swordGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 2, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.pickaxeGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 3, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.shovelGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 1, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.axeGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 3, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.hoeGold.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 2, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.shears.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 2, 8), 1);

		HawkProcessingRecipes.addHawkProcessingRecipe(Item.minecartEmpty.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 5, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.minecartPowered.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 2, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.minecartCrate.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 2, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.doorSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 6, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bucketEmpty.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 3, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.compass.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 4, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.pocketSundial.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 4, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.cauldron.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 7, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.plantRed.blockID, new ItemStack(Item.dyePowder, 2, 1), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.plantYellow.blockID, new ItemStack(Item.dyePowder, 2, 11), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.flintAndSteel.shiftedIndex, new ItemStack(mod_HawksMachinery.dust, 1, 8), 1);

		
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 0, new ItemStack(mod_HawksMachinery.dust, 1, 0), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 1, new ItemStack(mod_HawksMachinery.dust, 1, 0), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.CopperOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.CopperOreID), new ItemStack(mod_HawksMachinery.dust, 2, 6), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.TinOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.TinOreID), new ItemStack(mod_HawksMachinery.dust, 2, 7), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 0, new ItemStack(Block.cobblestone), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 1, new ItemStack(Block.cobblestone), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 2, new ItemStack(Block.cobblestoneMossy), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 3, new ItemStack(Block.cobblestone), 1);

		
		HawkProcessingRecipes.addHawkExplosive(Block.tnt.blockID, 1);
		HawkProcessingRecipes.addHawkExplosive(Item.gunpowder.shiftedIndex, 1);
		HawkProcessingRecipes.addHawkExplosive(Item.fireballCharge.shiftedIndex, 1);
	}
}
