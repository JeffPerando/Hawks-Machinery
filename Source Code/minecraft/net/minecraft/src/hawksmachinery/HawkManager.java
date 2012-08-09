
package net.minecraft.src.hawksmachinery;

import java.io.*;
import net.minecraft.client.*;
import net.minecraft.src.*;
import net.minecraft.src.basiccomponents.BasicComponents;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.ore.UEOreManager;
import net.minecraft.src.universalelectricity.recipe.UERecipeManager;

/**
 * 
 * This class takes cares and manages of all general things
 * 
 * @author Elusivehawk
 */
public class HawkManager
{
	public static mod_HawksMachinery BASEMOD;
	public static UERecipeManager RECIPE_GIVER;
	
	public static int dustID;
	
	public static int machineBlockID;
	public static int grinderID;
	
	public static int ACHshellOfAMachine;
	public static int ACHbackToBasics;
	public static int ACHtimeToGrind;
	public static int ACHbuildABetterMachineBlock;
	public static int ACHcircuitsThatBe;
	public static int ACHredstonedWithCare;
	
	public static final String GUI_PATH = "/hawksmachinery/textures/gui";
	
	public static final String BLOCK_TEXTURE_FILE = "/hawksmachinery/textures/blocks.png";
	public static final String ITEM_TEXTURE_FILE = "/hawksmachinery/textures/items.png";
	
	public static File HAWKS_FOLDER = (new File(Minecraft.getMinecraftDir() + "/config/HawksMachinery"));
	
	public static File CONFIGURATION = (new File(HAWKS_FOLDER + "/Config.cfg"));

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

		dustID = HMConfig.getOrCreateIntProperty("Dusts", Configuration.CATEGORY_ITEM, 24150).getInt(24150);
		
		ACHshellOfAMachine = HMConfig.getOrCreateIntProperty("Shell of a Machine", Configuration.CATEGORY_GENERAL, 1500).getInt(1500);
		ACHbackToBasics = HMConfig.getOrCreateIntProperty("Back to Basics", Configuration.CATEGORY_GENERAL, 1501).getInt(1501);
		ACHtimeToGrind = HMConfig.getOrCreateIntProperty("Time to Grind", Configuration.CATEGORY_GENERAL, 1502).getInt(1502);
		ACHbuildABetterMachineBlock = HMConfig.getOrCreateIntProperty("Build a Better Machine Block", Configuration.CATEGORY_GENERAL, 1503).getInt(1503);
		ACHcircuitsThatBe = HMConfig.getOrCreateIntProperty("Circuits That Be", Configuration.CATEGORY_GENERAL, 1504).getInt();
		ACHredstonedWithCare = HMConfig.getOrCreateIntProperty("Redstoned With Care", Configuration.CATEGORY_GENERAL, 1505).getInt(1505);
		
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
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 0), new Object[]{"oxo", "xpx", "oxo", 'o', BasicComponents.ItemSteelIngot, 'x', BasicComponents.ItemSteelPlate, 'p', Item.blazePowder});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockGrinder, 1), new Object[]{"xMx", "xpx", "xmx", 'x', BasicComponents.ItemSteelIngot, 'p', Item.pickaxeSteel, 'm', new ItemStack(BASEMOD.blockEmptyMachine, 1, 4), 'M', BasicComponents.ItemMotor});
		RECIPE_GIVER.addRecipe(new ItemStack(BasicComponents.ItemBattery), new Object[]{" x ", "xrx", "xcx", 'x', BasicComponents.ItemTinIngot, 'c', new ItemStack(BASEMOD.dust, 1, 0), 'r', Item.redstone});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"c", "s", 'c', new ItemStack(BASEMOD.dust, 1, 0), 's', Item.stick});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[]{" b ", "dod", "ooo", 'b', Item.book, 'd', new ItemStack(BASEMOD.dust, 1, 1), 'o', Block.obsidian});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 1), new Object[]{" C ", "CMC", " C ", 'C', new ItemStack(BasicComponents.ItemCircuit, 1, 0), 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 0)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 2), new Object[]{" C ", "CMC", " C ", 'C', new ItemStack(BasicComponents.ItemCircuit, 1, 1), 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 0)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 3), new Object[]{" C ", "CMC", " C ", 'C', new ItemStack(BasicComponents.ItemCircuit, 1, 2), 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 0)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 4), new Object[]{"RRR", "RMR", "RRR", 'R', Item.redstone, 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 1)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 5), new Object[]{"RRR", "RMR", "RRR", 'R', Item.redstone, 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 2)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 6), new Object[]{"RRR", "RMR", "RRR", 'R', Item.redstone, 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 3)});
		
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 5), new Object[]{BasicComponents.ItemSteelPlate});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 24), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 0)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 2)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 3)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 4)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 5)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 6)});
		
		
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dust, 1, 4), new ItemStack(Block.thinGlass));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dust, 1, 8), new ItemStack(Item.ingotIron));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dust, 1, 9), new ItemStack(Item.ingotGold));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dust, 1, 10), new ItemStack(BasicComponents.ItemCopperIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dust, 1, 11), new ItemStack(BasicComponents.ItemTinIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dust, 1, 12), new ItemStack(Block.obsidian));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockEmptyMachine, 1, 0), new ItemStack(BasicComponents.ItemSteelIngot, 24));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockGrinder, 1, 0), new ItemStack(BasicComponents.ItemSteelPlate, 15));
		
		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.diamond.shiftedIndex, new ItemStack(BASEMOD.dust, 1, 1), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.oreGold.blockID, new ItemStack(BASEMOD.dust, 2, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.enderPearl.shiftedIndex, new ItemStack(BASEMOD.dust, 1, 3), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.glass.blockID, new ItemStack(mod_HawksMachinery.dust, 4, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.oreIron.blockID, new ItemStack(mod_HawksMachinery.dust, 2, 5), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(BasicComponents.ItemCopperIngot.shiftedIndex, new ItemStack(BASEMOD.dust, 1, 10), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(BasicComponents.ItemTinIngot.shiftedIndex, new ItemStack(BASEMOD.dust, 1, 11), 1);		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.blazeRod.shiftedIndex, new ItemStack(Item.blazePowder, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bone.shiftedIndex, new ItemStack(Item.dyePowder, 3, 15), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.stone.blockID, new ItemStack(Block.gravel), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.cobblestone.blockID, new ItemStack(Block.sand), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.gravel.blockID, new ItemStack(Item.flint), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.eyeOfEnder.shiftedIndex, new ItemStack(BASEMOD.dust, 1, 3), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pumpkin.blockID, new ItemStack(Item.pumpkinSeeds, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pistonBase.blockID, new ItemStack(BASEMOD.dust, 1, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pistonStickyBase.blockID, new ItemStack(BASEMOD.dust, 1, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pumpkinLantern.blockID, new ItemStack(Item.pumpkinSeeds, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.dispenser.blockID, new ItemStack(Item.bow), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.stoneOvenIdle.blockID, new ItemStack(Block.cobblestone, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.thinGlass.blockID, new ItemStack(BASEMOD.dust, 1, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.glowStone.blockID, new ItemStack(Item.lightStoneDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.redstoneLampIdle.blockID, new ItemStack(Item.lightStoneDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.enchantmentTable.blockID, new ItemStack(Item.diamond, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.brewingStand.shiftedIndex, new ItemStack(Item.blazeRod, 1), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.sandStone.blockID, new ItemStack(Block.sand, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.obsidian.blockID, new ItemStack(BASEMOD.dust, 1, 12), 1);
		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.helmetSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 5, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.plateSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 8, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.legsSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 7, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bootsSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 4, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.helmetGold.shiftedIndex, new ItemStack(BASEMOD.dust, 5, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.plateGold.shiftedIndex, new ItemStack(BASEMOD.dust, 8, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.legsGold.shiftedIndex, new ItemStack(BASEMOD.dust, 7, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bootsGold.shiftedIndex, new ItemStack(BASEMOD.dust, 4, 9), 1);
		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.swordSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 2, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.pickaxeSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 3, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.shovelSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 1, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.axeSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 3, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.hoeSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 2, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.swordGold.shiftedIndex, new ItemStack(BASEMOD.dust, 2, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.pickaxeGold.shiftedIndex, new ItemStack(BASEMOD.dust, 3, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.shovelGold.shiftedIndex, new ItemStack(BASEMOD.dust, 1, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.axeGold.shiftedIndex, new ItemStack(BASEMOD.dust, 3, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.hoeGold.shiftedIndex, new ItemStack(BASEMOD.dust, 2, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.shears.shiftedIndex, new ItemStack(BASEMOD.dust, 2, 8), 1);
		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.minecartEmpty.shiftedIndex, new ItemStack(BASEMOD.dust, 5, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.minecartPowered.shiftedIndex, new ItemStack(BASEMOD.dust, 2, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.minecartCrate.shiftedIndex, new ItemStack(BASEMOD.dust, 2, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.doorSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 6, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bucketEmpty.shiftedIndex, new ItemStack(BASEMOD.dust, 3, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.compass.shiftedIndex, new ItemStack(BASEMOD.dust, 4, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.pocketSundial.shiftedIndex, new ItemStack(BASEMOD.dust, 4, 9), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.cauldron.shiftedIndex, new ItemStack(BASEMOD.dust, 7, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.plantRed.blockID, new ItemStack(Item.dyePowder, 2, 1), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.plantYellow.blockID, new ItemStack(Item.dyePowder, 2, 11), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.flintAndSteel.shiftedIndex, new ItemStack(BASEMOD.dust, 1, 8), 1);
		
		
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 0, new ItemStack(BASEMOD.dust, 1, 0), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 1, new ItemStack(BASEMOD.dust, 1, 0), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.CopperOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.CopperOreID), new ItemStack(BASEMOD.dust, 2, 6), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.TinOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.TinOreID), new ItemStack(BASEMOD.dust, 2, 7), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 0, new ItemStack(Block.cobblestone), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 1, new ItemStack(Block.cobblestone), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 2, new ItemStack(Block.cobblestoneMossy), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 3, new ItemStack(Block.cobblestone), 1);
		
		
		HawkProcessingRecipes.addHawkExplosive(Block.tnt.blockID, 1);
		HawkProcessingRecipes.addHawkExplosive(Item.gunpowder.shiftedIndex, 1);
		HawkProcessingRecipes.addHawkExplosive(Item.fireballCharge.shiftedIndex, 1);
	}
}
