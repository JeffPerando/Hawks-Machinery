
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
 * This class takes cares and manages of all general things
 * 
 * @author Elusivehawk
 */
public class HawkManager implements IGuiHandler
{
	public static mod_HawksMachinery BASEMOD;
	public static UERecipeManager RECIPE_GIVER;
	public static HawkProcessingRecipes PROCESS_RECIPES;
	
	public static int machineBlockID;
	public static int grinderID;
	public static int oreID;
	public static int metalStorageID;
	
	public static int dustRawID;
	public static int dustRefinedID;
	public static int ingotsID;
	
	public static int ACHshellOfAMachine;
	public static int ACHbackToBasics;
	public static int ACHtimeToGrind;
	public static int ACHbuildABetterMachineBlock;
	public static int ACHcircuitsThatBe;
	public static int ACHredstonedWithCare;
	public static int ACHminerkiin;
	public static int ACHspartaMiner;
	public static int ACHcompactCompact;
	
	public static final String GUI_PATH = "/hawksmachinery/textures/gui";
	public static final String BLOCK_TEXTURE_FILE = "/hawksmachinery/textures/blocks.png";
	public static final String ITEM_TEXTURE_FILE = "/hawksmachinery/textures/items.png";
	
	public static File HAWKS_FOLDER = (new File("/config/HawksMachinery"));
	
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
		
		grinderID = HMConfig.getOrCreateBlockIdProperty("Grinder", 3960).getInt(3960);
		machineBlockID = HMConfig.getOrCreateBlockIdProperty("Machine Block", 3961).getInt(3961);
		oreID = HMConfig.getOrCreateBlockIdProperty("Ores", 3962).getInt(3962);
		metalStorageID = HMConfig.getOrCreateBlockIdProperty("Metal Storage Blocks", 3963).getInt(3963);
		
		dustRawID = HMConfig.getOrCreateIntProperty("Raw Dusts", Configuration.CATEGORY_ITEM, 24150).getInt(24150);
		dustRefinedID = HMConfig.getOrCreateIntProperty("Refined Dusts", Configuration.CATEGORY_ITEM, 24151).getInt(24151);
		ingotsID = HMConfig.getOrCreateIntProperty("Ingots", Configuration.CATEGORY_ITEM, 24152).getInt(24152);

		ACHshellOfAMachine = HMConfig.getOrCreateIntProperty("ACH Shell of a Machine", Configuration.CATEGORY_GENERAL, 1500).getInt(1500);
		ACHbackToBasics = HMConfig.getOrCreateIntProperty("ACH Back to Basics", Configuration.CATEGORY_GENERAL, 1501).getInt(1501);
		ACHtimeToGrind = HMConfig.getOrCreateIntProperty("ACH Time to Grind", Configuration.CATEGORY_GENERAL, 1502).getInt(1502);
		ACHbuildABetterMachineBlock = HMConfig.getOrCreateIntProperty("ACH Build a Better Machine Block", Configuration.CATEGORY_GENERAL, 1503).getInt(1503);
		ACHcircuitsThatBe = HMConfig.getOrCreateIntProperty("ACH Circuits That Be", Configuration.CATEGORY_GENERAL, 1504).getInt();
		ACHredstonedWithCare = HMConfig.getOrCreateIntProperty("ACH Redstoned With Care", Configuration.CATEGORY_GENERAL, 1505).getInt(1505);
		ACHminerkiin = HMConfig.getOrCreateIntProperty("ACH Minerkiin", Configuration.CATEGORY_GENERAL, 1506).getInt(1506);
		ACHspartaMiner = HMConfig.getOrCreateIntProperty("ACH Tonight we mine IN HELL", Configuration.CATEGORY_GENERAL, 1507).getInt(1507);
		ACHcompactCompact = HMConfig.getOrCreateIntProperty("ACH Compact Compact", Configuration.CATEGORY_GENERAL, 1508).getInt(1508);
		
		HMConfig.save();
		
		return grinderID;
	}

	public static mod_HawksMachinery getModInstance()
	{
		return mod_HawksMachinery.instance;
	}
	
	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			return new HawkContainerGrinder(player.inventory, ((HawkTileEntityGrinder)tileEntity));
        }
		return null;
	}
	
	/**
	 * Loads all of the recipes for Hawk's Machinery, including regular crafting recipes.
	 */
	public static void loadRecipes()
	{
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 0), new Object[]{"oxo", "xpx", "oxo", 'o', BasicComponents.ItemSteelIngot, 'x', BasicComponents.ItemSteelPlate, 'p', Item.blazePowder});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockGrinder, 1), new Object[]{"xMx", "xpx", "xmx", 'x', BasicComponents.ItemSteelIngot, 'p', Item.pickaxeSteel, 'm', new ItemStack(BASEMOD.blockEmptyMachine, 1, 4), 'M', BasicComponents.ItemMotor});
		RECIPE_GIVER.addRecipe(new ItemStack(BasicComponents.ItemBattery), new Object[]{" x ", "xrx", "xcx", 'x', BasicComponents.ItemTinIngot, 'c', new ItemStack(BASEMOD.dustRaw, 1, 0), 'r', Item.redstone});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"c", "s", 'c', new ItemStack(BASEMOD.dustRaw, 1, 0), 's', Item.stick});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[]{" b ", "dod", "ooo", 'b', Item.book, 'd', new ItemStack(BASEMOD.dustRaw, 1, 1), 'o', Block.obsidian});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 1), new Object[]{" C ", "CMC", " C ", 'C', new ItemStack(BasicComponents.ItemCircuit, 1, 0), 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 0)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 2), new Object[]{" C ", "CMC", " C ", 'C', new ItemStack(BasicComponents.ItemCircuit, 1, 1), 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 0)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 3), new Object[]{" C ", "CMC", " C ", 'C', new ItemStack(BasicComponents.ItemCircuit, 1, 2), 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 0)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 4), new Object[]{"RRR", "RMR", "RRR", 'R', Item.redstone, 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 1)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 5), new Object[]{"RRR", "RMR", "RRR", 'R', Item.redstone, 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 2)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 6), new Object[]{"RRR", "RMR", "RRR", 'R', Item.redstone, 'M', new ItemStack(BASEMOD.blockEmptyMachine, 1, 3)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockEmptyMachine, 1, 0), new Object[]{"oxo", "xex", "oxo", 'o', BasicComponents.ItemSteelIngot, 'x', BasicComponents.ItemSteelPlate, 'e', new ItemStack(BASEMOD.dustRefined, 1, 1)});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.glass, 4), new Object[]{"GG", "GG", 'G', new ItemStack(BASEMOD.dustRefined, 1, 2)});
		
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 5), new Object[]{BasicComponents.ItemSteelPlate});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 24), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 0)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 2)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 3)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 4)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 5)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BasicComponents.ItemSteelIngot, 44), new Object[]{new ItemStack(BASEMOD.blockEmptyMachine, 1, 6)});
		RECIPE_GIVER.addShapelessRecipe(BasicComponents.ItemSteelClump, new Object[]{new ItemStack(BASEMOD.dustRaw, 1, 0), new ItemStack(BASEMOD.dustRefined, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 3), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 1), new ItemStack(BASEMOD.dustRaw, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 4), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 2), new ItemStack(BASEMOD.dustRaw, 1, 2)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 5), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 3), new ItemStack(BASEMOD.dustRaw, 1, 3)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 6), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 4), new ItemStack(BASEMOD.dustRaw, 1, 4)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.blazePowder, Item.gunpowder, new ItemStack(BASEMOD.dustRaw, 1, 0)});
		
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 2), new ItemStack(Block.thinGlass));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 3), new ItemStack(Item.ingotIron));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 4), new ItemStack(Item.ingotGold));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 5), new ItemStack(BasicComponents.ItemCopperIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 6), new ItemStack(BasicComponents.ItemTinIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRaw, 1, 5), new ItemStack(Block.obsidian));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockGrinder, 1, 0), new ItemStack(BasicComponents.ItemSteelPlate, 15));
		
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.diamond.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 1, 0), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.oreGold.blockID, new ItemStack(BASEMOD.dustRaw, 2, 2), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.enderPearl.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 1, 1), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.glass.blockID, new ItemStack(BASEMOD.dustRefined, 4, 2), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.oreIron.blockID, new ItemStack(BASEMOD.dustRaw, 2, 1), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(BasicComponents.ItemCopperIngot.shiftedIndex, new ItemStack(BASEMOD.dustRaw, 1, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(BasicComponents.ItemTinIngot.shiftedIndex, new ItemStack(BASEMOD.dustRaw, 1, 4), 1);		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.blazeRod.shiftedIndex, new ItemStack(Item.blazePowder, 2), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.bone.shiftedIndex, new ItemStack(Item.dyePowder, 3, 15), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.stone.blockID, new ItemStack(Block.gravel), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.cobblestone.blockID, new ItemStack(Block.sand), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.gravel.blockID, new ItemStack(Item.flint), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.eyeOfEnder.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 1, 1), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.pumpkin.blockID, new ItemStack(Item.pumpkinSeeds, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.pistonBase.blockID, new ItemStack(BASEMOD.dustRefined, 1, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.pistonStickyBase.blockID, new ItemStack(BASEMOD.dustRefined, 1, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.pumpkinLantern.blockID, new ItemStack(Item.pumpkinSeeds, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.dispenser.blockID, new ItemStack(Item.bow), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.stoneOvenIdle.blockID, new ItemStack(Block.cobblestone, 8), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.thinGlass.blockID, new ItemStack(BASEMOD.dustRefined, 1, 2), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.glowStone.blockID, new ItemStack(Item.lightStoneDust, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.redstoneLampIdle.blockID, new ItemStack(Item.lightStoneDust, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.enchantmentTable.blockID, new ItemStack(Item.diamond, 2), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.brewingStand.shiftedIndex, new ItemStack(Item.blazeRod, 1), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.sandStone.blockID, new ItemStack(Block.sand, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.obsidian.blockID, new ItemStack(BASEMOD.dustRaw, 1, 8), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.glassBottle.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 4, 2), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.thinGlass.blockID, new ItemStack(BASEMOD.dustRefined, 1, 2), 1);		
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.helmetSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 5, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.plateSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 8, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.legsSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 7, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.bootsSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 4, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.helmetGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 5, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.plateGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 8, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.legsGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 7, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.bootsGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 4, 4), 1);
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.swordSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 2, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.pickaxeSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 3, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.shovelSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 1, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.axeSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 3, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.hoeSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 2, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.swordGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 2, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.pickaxeGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 3, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.shovelGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 1, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.axeGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 3, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.hoeGold.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 2, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.shears.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 2, 4), 1);
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.minecartEmpty.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 5, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.minecartPowered.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 5, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.minecartCrate.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 5, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.doorSteel.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 6, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.bucketEmpty.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 3, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.compass.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 4, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.pocketSundial.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 4, 4), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.cauldron.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 7, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.plantRed.blockID, new ItemStack(Item.dyePowder, 2, 1), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.plantYellow.blockID, new ItemStack(Item.dyePowder, 2, 11), 1);
		
		
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 0, new ItemStack(BASEMOD.dustRaw, 1, 0), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 1, new ItemStack(BASEMOD.dustRaw, 1, 0), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.CopperOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.CopperOreID), new ItemStack(BASEMOD.dustRaw, 2, 3), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.TinOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.TinOreID), new ItemStack(BASEMOD.dustRaw, 2, 4), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 0, new ItemStack(Block.cobblestone), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 1, new ItemStack(Block.cobblestone), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 2, new ItemStack(Block.cobblestoneMossy), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 3, new ItemStack(Block.cobblestone), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(BASEMOD.blockOre.blockID, 0, new ItemStack(BASEMOD.dustRaw, 2, 5), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(BASEMOD.blockOre.blockID, 1, new ItemStack(BASEMOD.dustRaw, 2, 6), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(BASEMOD.blockOre.blockID, 2, new ItemStack(BASEMOD.dustRaw, 2, 7), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(BASEMOD.blockOre.blockID, 5, new ItemStack(BASEMOD.dustRaw, 3, 5), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(BASEMOD.blockOre.blockID, 6, new ItemStack(BASEMOD.dustRaw, 3, 6), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(BASEMOD.blockOre.blockID, 7, new ItemStack(BASEMOD.dustRaw, 3, 7), 1);
		
		
		PROCESS_RECIPES.addHawkExplosive(Block.tnt.blockID, 1);
		PROCESS_RECIPES.addHawkExplosive(Item.gunpowder.shiftedIndex, 1);
		PROCESS_RECIPES.addHawkExplosive(Item.fireballCharge.shiftedIndex, 1);
	}
	
}
