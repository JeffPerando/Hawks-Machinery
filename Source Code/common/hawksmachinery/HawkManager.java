
package hawksmachinery;

import hawksmachinery.padAPI.HawkPadAPICore;
import hawksmachinery.padAPI.IHawkPadEffect;
import hawksmachinery.padAPI.IHawkPadElectricity;
import hawksmachinery.padAPI.IHawkPadTexture;
import java.io.*;
import java.util.ArrayList;
import universalelectricity.basiccomponents.BasicComponents;
import universalelectricity.recipe.RecipeManager;
import net.minecraft.src.*;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 
 * This class takes cares and manages of all general things
 * 
 * @author Elusivehawk
 */
public class HawkManager
{
	public static HawksMachinery BASEMOD;
	public static RecipeManager RECIPE_GIVER;
	public static HawkProcessingRecipes PROCESS_RECIPES;
	
	public static ArrayList<ItemStack> copperOreList = OreDictionary.getOres("oreCopper");
	public static ArrayList<ItemStack> tinOreList = OreDictionary.getOres("oreTin");
	public static ArrayList<ItemStack> titaniumOreList = OreDictionary.getOres("oreTitanium");
	public static ArrayList<ItemStack> aluminumOreList = OreDictionary.getOres("oreAluminum");
	public static ArrayList<ItemStack> silverOreList = OreDictionary.getOres("oreSilver");
	
	public static ArrayList<ItemStack> copperIngotList = OreDictionary.getOres("ingotCopper");
	public static ArrayList<ItemStack> tinIngotList = OreDictionary.getOres("ingotTin");
	public static ArrayList<ItemStack> titaniumIngotList = OreDictionary.getOres("ingotTitanium");
	public static ArrayList<ItemStack> aluminumIngotList = OreDictionary.getOres("ingotAluminum");
	public static ArrayList<ItemStack> silverIngotList = OreDictionary.getOres("ingotSilver");
	
	public static int grinderID;
	public static int machineBlockID;
	public static int oreID;
	public static int metalStorageID;
	public static int washerID;
	
	public static int dustRawID;
	public static int dustRefinedID;
	public static int ingotsID;
	public static int partsID;
	
	public static int ACHtimeToGrind;
	public static int ACHminerkiin;
	public static int ACHcompactCompact;
	
	public static boolean generateTitanium;
	public static boolean generateAluminum;
	public static boolean generateSilver;
	
	public static final String GUI_PATH = "/hawksmachinery/textures/gui";
	public static final String BLOCK_TEXTURE_FILE = "/hawksmachinery/textures/blocks.png";
	public static final String ITEM_TEXTURE_FILE = "/hawksmachinery/textures/items.png";
	
	public static File CONFIGURATION = (new File("/confing/HMConfig.cfg"));
	
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
		machineBlockID = HMConfig.getOrCreateBlockIdProperty("Machine Blocks", 3961).getInt(3961);
		oreID = HMConfig.getOrCreateBlockIdProperty("Ores", 3962).getInt(3962);
		metalStorageID = HMConfig.getOrCreateBlockIdProperty("Metal Storage Blocks", 3963).getInt(3963);
		
		dustRawID = HMConfig.getOrCreateIntProperty("Raw Dusts", Configuration.CATEGORY_ITEM, 24150).getInt(24150);
		dustRefinedID = HMConfig.getOrCreateIntProperty("Refined Dusts", Configuration.CATEGORY_ITEM, 24151).getInt(24151);
		ingotsID = HMConfig.getOrCreateIntProperty("Ingots", Configuration.CATEGORY_ITEM, 24152).getInt(24152);
		partsID = HMConfig.getOrCreateIntProperty("Parts", Configuration.CATEGORY_ITEM, 24153).getInt(24153);
		
		ACHtimeToGrind = HMConfig.getOrCreateIntProperty("ACH Time To Grind", Configuration.CATEGORY_GENERAL, 1500).getInt(1500);
		ACHminerkiin = HMConfig.getOrCreateIntProperty("ACH Minerkiin", Configuration.CATEGORY_GENERAL, 1501).getInt(1501);
		ACHcompactCompact = HMConfig.getOrCreateIntProperty("ACH Compact Compact", Configuration.CATEGORY_GENERAL, 1502).getInt(1502);
		
		generateTitanium = HMConfig.getOrCreateBooleanProperty("Generate Titanium", Configuration.CATEGORY_GENERAL, true).getBoolean(true);
		generateAluminum = HMConfig.getOrCreateBooleanProperty("Generate Aluminum", Configuration.CATEGORY_GENERAL, true).getBoolean(true);
		generateSilver = HMConfig.getOrCreateBooleanProperty("Generate Silver", Configuration.CATEGORY_GENERAL, true).getBoolean(true);
		
		HMConfig.save();
		
		return grinderID;
	}
	
	public static HawksMachinery getModInstance()
	{
		return HawksMachinery.INSTANCE;
	}
	
	/**
	 * Loads all of the recipes for Hawk's Machinery, including regular crafting recipes.
	 */
	public static void loadRecipes()
	{
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockGrinder, 1), new Object[]{"SPS", "SMS", "SBS", 'S', BasicComponents.itemSteelPlate, 'P', Item.pickaxeSteel, 'M', BasicComponents.itemMotor, 'B', BasicComponents.itemBattery});
		RECIPE_GIVER.addRecipe(new ItemStack(BasicComponents.itemBattery), new Object[]{" x ", "xrx", "xcx", 'x', BasicComponents.itemTinIngot, 'c', new ItemStack(BASEMOD.dustRaw, 1, 0), 'r', Item.redstone});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"c", "s", 'c', new ItemStack(BASEMOD.dustRaw, 1, 0), 's', Item.stick});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[]{" b ", "dod", "ooo", 'b', Item.book, 'd', new ItemStack(BASEMOD.dustRaw, 1, 1), 'o', Block.obsidian});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.glass, 4), new Object[]{"GG", "GG", 'G', new ItemStack(BASEMOD.dustRefined, 1, 2)});
		
		for (ItemStack titanium : titaniumIngotList)
		{
			RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockMetalStorage, 1, 0), new Object[]{"MMM", "MMM", "MMM", 'M', titanium});
		}
		
		for (ItemStack aluminum : aluminumIngotList)
		{
			RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockMetalStorage, 1, 1), new Object[]{"MMM", "MMM", "MMM", 'M', aluminum});
		}
		
		for (ItemStack silver : silverIngotList)
		{
			RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockMetalStorage, 1, 2), new Object[]{"MMM", "MMM", "MMM", 'M', silver});
		}
		
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockMetalStorage, 1, 3), new Object[]{"MMM", "MMM", "MMM", 'M', new ItemStack(BASEMOD.ingots, 1, 3)});
		
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.parts, 1, 0), new Object[]{" B ", "PSM", " B ", 'P', BasicComponents.itemSteelPlate, 'S', BasicComponents.itemSteelIngot, 'M', BasicComponents.itemMotor, 'B', Item.blazePowder});
		
		RECIPE_GIVER.addShapelessRecipe(BasicComponents.itemSteelAlloy, new Object[]{new ItemStack(BASEMOD.dustRaw, 1, 0), new ItemStack(BASEMOD.dustRefined, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 3), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 1), new ItemStack(BASEMOD.dustRaw, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 4), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 2), new ItemStack(BASEMOD.dustRaw, 1, 2)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 5), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 3), new ItemStack(BASEMOD.dustRaw, 1, 3)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 6), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 4), new ItemStack(BASEMOD.dustRaw, 1, 4)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.blazePowder, Item.gunpowder, new ItemStack(BASEMOD.dustRaw, 1, 0)});
		
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 0), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 0)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 1), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 2), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 2)});
		
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 2), new ItemStack(Block.thinGlass));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 3), new ItemStack(Item.ingotIron));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 4), new ItemStack(Item.ingotGold));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 5), new ItemStack(BasicComponents.itemCopperIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 6), new ItemStack(BasicComponents.itemTinIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRaw, 1, 5), new ItemStack(Block.obsidian));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockGrinder, 1, 0), new ItemStack(BasicComponents.itemSteelPlate, 11));
		
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockOre, 1, 0), new ItemStack(BASEMOD.ingots, 1, 0));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockOre, 1, 1), new ItemStack(BASEMOD.ingots, 1, 1));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockOre, 1, 2), new ItemStack(BASEMOD.ingots, 1, 2));
		
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.diamond.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 1, 0), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.oreGold.blockID, new ItemStack(BASEMOD.dustRaw, 2, 2), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.enderPearl.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 1, 1), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.glass.blockID, new ItemStack(BASEMOD.dustRefined, 4, 2), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.oreIron.blockID, new ItemStack(BASEMOD.dustRaw, 2, 1), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(BasicComponents.itemCopperIngot.shiftedIndex, new ItemStack(BASEMOD.dustRaw, 1, 3), 1);
		PROCESS_RECIPES.addHawkProcessingRecipe(BasicComponents.itemTinIngot.shiftedIndex, new ItemStack(BASEMOD.dustRaw, 1, 4), 1);		
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
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.emerald.shiftedIndex, new ItemStack(BASEMOD.dustRefined, 1, 10), 1);
		
		
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 0, new ItemStack(BASEMOD.dustRaw, 1, 0), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 1, new ItemStack(BASEMOD.dustRaw, 1, 0), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 0, new ItemStack(Block.cobblestone), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 1, new ItemStack(Block.cobblestone), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 2, new ItemStack(Block.cobblestoneMossy), 1);
		PROCESS_RECIPES.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 3, new ItemStack(Block.cobblestone), 1);
		
		for (ItemStack copper : copperOreList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(copper.itemID, copper.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 3), 1);
		}
		
		for (ItemStack tin : tinOreList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(tin.itemID, tin.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 3), 1);
		}
		
		for (ItemStack titanium : titaniumOreList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(titanium.itemID, titanium.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 5), 1);
		}
		
		for (ItemStack aluminum : aluminumOreList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(aluminum.itemID, aluminum.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 6), 1);
		}
		
		for (ItemStack silver : silverOreList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(silver.itemID, silver.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 7), 1);
		}
		
		
		for (ItemStack copper : copperIngotList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(copper.itemID, copper.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 5), 1);
			RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.parts, 1, 3), new Object[]{" G ", "GBG", "cCc"});
		}
		
		for (ItemStack tin : tinIngotList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(tin.itemID, tin.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 6), 1);
		}
		
		for (ItemStack titanium : titaniumIngotList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(titanium.itemID, titanium.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 7), 1);
			RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.parts, 1, 1), new Object[]{"TLT", "TBT", " B ", 'T', titanium, 'L', new ItemStack(BASEMOD.parts, 1, 3), 'B', Item.blazeRod});
			RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.parts, 1, 2), new Object[]{" T ", "TET", " T ", 'T', titanium, 'E', Item.eyeOfEnder});
		}
		
		for (ItemStack aluminum : aluminumIngotList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(aluminum.itemID, aluminum.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 8), 1);
		}
		
		for (ItemStack silver : aluminumIngotList)
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(silver.itemID, silver.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 9), 1);
		}
		
		
		PROCESS_RECIPES.addHawkExplosive(Block.tnt.blockID, 1);
		PROCESS_RECIPES.addHawkExplosive(Item.gunpowder.shiftedIndex, 1);
		PROCESS_RECIPES.addHawkExplosive(Item.fireballCharge.shiftedIndex, 1);
	}
	
}
