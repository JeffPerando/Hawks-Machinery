
package hawksmachinery;

import java.io.*;
import java.util.ArrayList;
import cpw.mods.fml.common.IFuelHandler;
import universalelectricity.basiccomponents.BasicComponents;
import universalelectricity.basiccomponents.ItemBattery;
import universalelectricity.recipe.RecipeManager;
import net.minecraft.src.*;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 
 * This class takes cares and manages of all general things
 * 
 * @author Elusivehawk
 */
public class HawkManager implements IFuelHandler
{
	public static HawksMachinery BASEMOD;
	public static RecipeManager RECIPE_GIVER;
	public static HawkProcessingRecipes PROCESS_RECIPES;
	
	public static EnumToolMaterial endiumTool = EnumHelper.addToolMaterial("Endium", 5, 3001, 15.0F, 4, 25);
	
	public static int grinderID;
	public static int oreID;
	public static int metalStorageID;
	public static int washerID;
	public static int verticalDrillID;
	public static int endstoneFurnaceID;
	
	public static int dustRawID;
	public static int dustRefinedID;
	public static int ingotsID;
	public static int partsID;
	public static int platingID;
	public static int drillID;
	
	public static int endiumPickID;
	public static int endiumShovelID;
	public static int endiumAxeID;
	
	public static int ACHprospector;
	public static int ACHtimeToGrind;
	public static int ACHcompactCompact;
	public static int ACHminerkiin;

	public static boolean generateTitanium;
	public static boolean generateAluminum;
	public static boolean generateSilver;
	public static boolean generateEndium;
	public static boolean enableEndiumTools;
	
	public static final String GUI_PATH = "/hawksmachinery/textures/gui";
	public static final String BLOCK_TEXTURE_FILE = "/hawksmachinery/textures/blocks.png";
	public static final String ITEM_TEXTURE_FILE = "/hawksmachinery/textures/items.png";
	
	public static Configuration HMConfig = new Configuration(new File("config/HawksMachinery/HMConfig.cfg"));
	
	public static int initProps()
	{
		HMConfig.load();
		
		grinderID = HMConfig.getOrCreateBlockIdProperty("Grinder", 3960).getInt(3960);
		oreID = HMConfig.getOrCreateBlockIdProperty("Ores", 3961).getInt(3961);
		metalStorageID = HMConfig.getOrCreateBlockIdProperty("Metal Storage Blocks", 3962).getInt(3962);
		washerID = HMConfig.getOrCreateBlockIdProperty("Washer", 3963).getInt(3963);
		verticalDrillID = HMConfig.getOrCreateBlockIdProperty("Vertical Mining Drill", 3964).getInt(3964);
		endstoneFurnaceID = HMConfig.getOrCreateBlockIdProperty("Endstone Furnace", 3965).getInt(3965);
		
		dustRawID = HMConfig.getOrCreateIntProperty("Raw Dusts", Configuration.CATEGORY_ITEM, 24150).getInt(24150);
		dustRefinedID = HMConfig.getOrCreateIntProperty("Refined Dusts", Configuration.CATEGORY_ITEM, 24151).getInt(24151);
		ingotsID = HMConfig.getOrCreateIntProperty("Ingots", Configuration.CATEGORY_ITEM, 24152).getInt(24152);
		partsID = HMConfig.getOrCreateIntProperty("Parts", Configuration.CATEGORY_ITEM, 24153).getInt(24153);
		platingID = HMConfig.getOrCreateIntProperty("Plating", Configuration.CATEGORY_ITEM, 24154).getInt(24154);
		
		endiumPickID = HMConfig.getOrCreateIntProperty("Endium Pickaxe", Configuration.CATEGORY_ITEM, 25000).getInt(25000);
		endiumShovelID = HMConfig.getOrCreateIntProperty("Endium Shovel", Configuration.CATEGORY_ITEM, 25001).getInt(25001);
		endiumAxeID = HMConfig.getOrCreateIntProperty("Endium Axe", Configuration.CATEGORY_ITEM, 25002).getInt(25002);
		
		ACHprospector = HMConfig.getOrCreateIntProperty("ACH Prospector", Configuration.CATEGORY_GENERAL, 1500).getInt(1500);
		ACHtimeToGrind = HMConfig.getOrCreateIntProperty("ACH Time To Grind", Configuration.CATEGORY_GENERAL, 1501).getInt(1501);
		ACHcompactCompact = HMConfig.getOrCreateIntProperty("ACH Compact Compact", Configuration.CATEGORY_GENERAL, 1502).getInt(1502);
		ACHminerkiin = HMConfig.getOrCreateIntProperty("ACH Minerkiin", Configuration.CATEGORY_GENERAL, 1503).getInt(1503);
		
		generateTitanium = HMConfig.getOrCreateBooleanProperty("Generate Titanium", Configuration.CATEGORY_GENERAL, true).getBoolean(true);
		generateAluminum = HMConfig.getOrCreateBooleanProperty("Generate Aluminum", Configuration.CATEGORY_GENERAL, true).getBoolean(true);
		generateSilver = HMConfig.getOrCreateBooleanProperty("Generate Silver", Configuration.CATEGORY_GENERAL, true).getBoolean(true);
		generateEndium = HMConfig.getOrCreateBooleanProperty("Generate Endium", Configuration.CATEGORY_GENERAL, true).getBoolean(true);
		
		enableEndiumTools = HMConfig.getOrCreateBooleanProperty("Enable Endium Tools", Configuration.CATEGORY_GENERAL, false).getBoolean(false);
		
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
		
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockGrinder, 1), new Object[]{"TPT", "TMT", "TBT", 'T', new ItemStack(BASEMOD.plating, 1, 0), 'P', Item.pickaxeSteel, 'M', BasicComponents.itemMotor, 'B', (((ItemBattery)BasicComponents.itemBattery).getUnchargedItemStack())});
		RECIPE_GIVER.addRecipe(new ItemStack(BasicComponents.itemBattery), new Object[]{" x ", "xrx", "xcx", 'x', BasicComponents.itemTinIngot, 'c', new ItemStack(BASEMOD.dustRaw, 1, 0), 'r', Item.redstone});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"c", "s", 'c', new ItemStack(BASEMOD.dustRaw, 1, 0), 's', Item.stick});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[]{" b ", "dod", "ooo", 'b', Item.book, 'd', new ItemStack(BASEMOD.dustRaw, 1, 1), 'o', Block.obsidian});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.glass, 4), new Object[]{"GG", "GG", 'G', new ItemStack(BASEMOD.dustRefined, 1, 2)});
		
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockMetalStorage, 1, 0), new Object[]{"MMM", "MMM", "MMM", 'M', "ingotTitanium"});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockMetalStorage, 1, 1), new Object[]{"MMM", "MMM", "MMM", 'M', "ingotAluminum"});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockMetalStorage, 1, 2), new Object[]{"MMM", "MMM", "MMM", 'M', "ingotSilver"});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.blockMetalStorage, 1, 3), new Object[]{"MMM", "MMM", "MMM", 'M', "ingotEndium"});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.parts, 1, 0), new Object[]{" B ", "PSM", " B ", 'P', BasicComponents.itemSteelPlate, 'S', BasicComponents.itemSteelIngot, 'M', BasicComponents.itemMotor, 'B', Item.blazePowder});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.parts, 1, 1), new Object[]{"TLT", "TBT", " B ", 'T', "ingotTitanium", 'B', Item.blazeRod, 'L', new ItemStack(BASEMOD.parts, 1, 3)});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.parts, 1, 2), new Object[]{" T ", "TET", " T ", 'T', "ingotTitanium", 'E', Item.enderPearl});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.parts, 1, 3), new Object[]{" G ", "GBG", "cCc", 'G', Block.thinGlass, 'B', Item.blazeRod, 'c', "ingotCopper", 'C', BasicComponents.blockCopperWire});
		
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.plating, 1, 0), new Object[]{"TT", "TT", 'T', "ingotTitanium"});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.plating, 1, 1), new Object[]{"AA", "AA", 'A', "ingotAluminum"});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.plating, 1, 2), new Object[]{"SS", "SS", 'S', "ingotSilver"});
		RECIPE_GIVER.addRecipe(new ItemStack(BASEMOD.plating, 1, 3), new Object[]{"EE", "EE", 'E', "ingotEndium"});
		
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 4, 0), new Object[]{new ItemStack(BASEMOD.plating, 1, 0)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 4, 1), new Object[]{new ItemStack(BASEMOD.plating, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 4, 2), new Object[]{new ItemStack(BASEMOD.plating, 1, 2)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 4, 3), new Object[]{new ItemStack(BASEMOD.plating, 1, 3)});
		
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 0), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 0)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 1), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 2), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 2)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 3), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 3)});
		
		RECIPE_GIVER.addShapelessRecipe(BasicComponents.itemSteelDust, new Object[]{new ItemStack(BASEMOD.dustRaw, 1, 0), new ItemStack(BASEMOD.dustRefined, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 3), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 1), new ItemStack(BASEMOD.dustRaw, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 4), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 2), new ItemStack(BASEMOD.dustRaw, 1, 2)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 5), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 3), new ItemStack(BASEMOD.dustRaw, 1, 3)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 6), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 4), new ItemStack(BASEMOD.dustRaw, 1, 4)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 7), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 5), new ItemStack(BASEMOD.dustRaw, 1, 5)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 8), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 6), new ItemStack(BASEMOD.dustRaw, 1, 6)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.dustRefined, 2, 9), new Object[]{Item.bucketWater, new ItemStack(BASEMOD.dustRaw, 1, 7), new ItemStack(BASEMOD.dustRaw, 1, 7)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.blazePowder, Item.gunpowder, new ItemStack(BASEMOD.dustRaw, 1, 0)});
		
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 0), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 0)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 1), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 1)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(BASEMOD.ingots, 9, 2), new Object[]{new ItemStack(BASEMOD.blockMetalStorage, 1, 2)});
		
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 2), new ItemStack(Block.thinGlass));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 3), new ItemStack(Item.ingotIron));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 4), new ItemStack(Item.ingotGold));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 5), new ItemStack(BasicComponents.itemCopperIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 6), new ItemStack(BasicComponents.itemTinIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 7), new ItemStack(BASEMOD.ingots, 1, 0));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 8), new ItemStack(BASEMOD.ingots, 1, 1));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRefined, 1, 9), new ItemStack(BASEMOD.ingots, 1, 2));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.dustRaw, 1, 5), new ItemStack(Block.obsidian));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockGrinder, 1, 0), new ItemStack(BasicComponents.itemSteelPlate, 11));
		
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockOre, 1, 0), new ItemStack(BASEMOD.ingots, 1, 0));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockOre, 1, 1), new ItemStack(BASEMOD.ingots, 1, 1));
		RECIPE_GIVER.addSmelting(new ItemStack(BASEMOD.blockOre, 1, 2), new ItemStack(BASEMOD.ingots, 1, 2));
		
	}
	
	public static void loadProcessingRecipes()
	{
		
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
		
		for (ItemStack copper : OreDictionary.getOres("oreCopper"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(copper.itemID, copper.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 3), 1);
		}
		
		for (ItemStack tin : OreDictionary.getOres("oreTin"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(tin.itemID, tin.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 4), 1);
		}
		
		for (ItemStack titanium : OreDictionary.getOres("oreTitanium"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(titanium.itemID, titanium.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 5), 1);
		}
		
		for (ItemStack aluminum : OreDictionary.getOres("oreAluminum"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(aluminum.itemID, aluminum.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 6), 1);
		}
		
		for (ItemStack silver : OreDictionary.getOres("oreSilver"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(silver.itemID, silver.getItemDamage(), new ItemStack(BASEMOD.dustRaw, 2, 7), 1);
		}
		
		
		for (ItemStack copper : OreDictionary.getOres("ingotCopper"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(copper.itemID, copper.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 5), 1);
		}
		
		for (ItemStack tin : OreDictionary.getOres("ingotTin"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(tin.itemID, tin.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 6), 1);
		}
		
		for (ItemStack titanium : OreDictionary.getOres("ingotTitanium"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(titanium.itemID, titanium.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 5), 1);
		}
		
		for (ItemStack aluminum : OreDictionary.getOres("ingotAluminum"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(aluminum.itemID, aluminum.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 5), 1);
		}
		
		for (ItemStack silver : OreDictionary.getOres("ingotSilver"))
		{
			PROCESS_RECIPES.addHawkMetaProcessingRecipe(silver.itemID, silver.getItemDamage(), new ItemStack(BASEMOD.dustRefined, 1, 5), 1);
		}
		
		
		PROCESS_RECIPES.addHawkExplosive(Block.tnt.blockID, 1);
		PROCESS_RECIPES.addHawkExplosive(Item.gunpowder.shiftedIndex, 1);
		PROCESS_RECIPES.addHawkExplosive(Item.fireballCharge.shiftedIndex, 1);
	}
	
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		return 0;
	}
	
}
