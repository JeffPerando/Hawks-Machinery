
package hawksmachinery;

import hawksmachinery.blocks.*;
import hawksmachinery.items.*;
import hawksmachinery.tileentity.*;
import hawksmachinery.HawkProcessingRecipes.HawkEnumProcessing;
import java.io.File;
import java.util.List;
import com.google.common.collect.ObjectArrays;
import universalelectricity.UniversalElectricity;
import universalelectricity.BasicComponents;
import universalelectricity.basiccomponents.ItemBattery;
import universalelectricity.network.PacketManager;
import universalelectricity.recipe.CraftingRecipe;
import universalelectricity.recipe.RecipeManager;
import vazkii.um.client.ModReleaseType;
import vazkii.um.client.ModType;
import vazkii.um.common.ModConverter;
import vazkii.um.common.UpdateManager;
import vazkii.um.common.UpdateManagerMod;
import vazkii.um.common.checking.CheckingMethod;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.StepSound;
import net.minecraft.src.World;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

/**
 * 
 * The mod file for Hawk's Machinery.
 * 
 * @author Elusivehawk
 */
@Mod(modid = "HawksMachinery", name = "Hawk's Machinery", version = "Alpha v1.4.0", dependencies = "after:BasicComponents")
@NetworkMod(channels = {"HawksMachinery"}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketManager.class)
public class HawksMachinery implements ICraftingHandler
{
	@Instance("HawksMachinery")
	private static HawksMachinery INSTANCE;
	
	@SidedProxy(clientSide = "hawksmachinery.HMClientProxy", serverSide = "hawksmachinery.HMCommonProxy")
	public static HMCommonProxy PROXY;
	
	public static RecipeManager RECIPE_GIVER;
	public static HawkProcessingRecipes PROCESS_RECIPES;
	public static HawkEnumProcessing CRUSH = HawkEnumProcessing.CRUSHING;
	public static HawkEnumProcessing WASH = HawkEnumProcessing.WASHING;
	
	public static int crusherID;
	public static int endiumOreID;
	public static int washerID;
	public static int chunkloaderID;
	public static int sintererID;
	public static int refrigeratorID;
	
	public static int dustRawID;
	public static int dustRefinedID;
	public static int partsID;
	public static int blueprintID;
	public static int endiumAlloyID;
	
	public static int ACHprospector;
	public static int ACHtimeToCrush;
	public static int ACHminerkiin;
	public static int ACHwash;
	
	public static int crusherTicks;
	public static int washerTicks;
	public static int maxChunksLoaded = 100;
	
	public static boolean generateEndium;
	public static boolean enableUpdateChecking;
	public static boolean enableAutoDL;
	public static boolean enableChunkloader;
	
	public static final String GUI_PATH = "/hawksmachinery/resources/gui";
	public static final String BLOCK_TEXTURE_FILE = "/hawksmachinery/resources/textures/blocks.png";
	public static final String ITEM_TEXTURE_FILE = "/hawksmachinery/resources/textures/items.png";
	public static final String TEXTURE_PATH = "/hawksmachinery/resources/textures";
	public static final String SOUND_PATH = "/hawksmachinery/resources/sounds";
	
	public static Configuration HMConfig = new Configuration(new File(Loader.instance().getConfigDir(), "HawksMachinery/HMConfig.cfg"));
	
	public static HawkManager MANAGER = new HawkManager(instance(), maxChunksLoaded);
	
	public static Block crusher;
	public static Block washer;
	public static Block chunkloader;
	public static Block endiumOre;
	
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Obsidian, 6 - Endium.
	 */
	public static Item dustRaw;
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Emerald, 8 - Nether Star, 9 - Endium.
	 */
	public static Item dustRefined;
	public static Item parts;
	public static Item blueprints;
	public static Item endiumItems;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		HMConfig.load();
		
		crusherID = HMConfig.getBlock("Crusher", 3960).getInt(3960);
		endiumOreID = HMConfig.getBlock("Endium Ore", 3961).getInt(3961);
		washerID = HMConfig.getBlock("Washer", 3962).getInt(3962);
		//NOTE ID #3963 saved for Endium Chunkloader.
		sintererID = HMConfig.getBlock("Sinterer", 3964).getInt(3965);
		refrigeratorID = HMConfig.getBlock("Refridgerator", 3965).getInt(3965);
		
		generateEndium = HMConfig.get(Configuration.CATEGORY_GENERAL, "Generate Endium", true).getBoolean(true);
		enableUpdateChecking = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Update Checking", true).getBoolean(true);
		enableAutoDL = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Auto DL", true).getBoolean(true);
		enableChunkloader = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Chunkloader Block", true).getBoolean(true);
		
		if (enableChunkloader)
		{
			chunkloaderID = HMConfig.getBlock("Endium Chunkloader", 3964).getInt(3964);
			maxChunksLoaded = HMConfig.get("Max Chunks Loaded", Configuration.CATEGORY_GENERAL, 25).getInt(25);
			
		}
		
		dustRawID = HMConfig.get(Configuration.CATEGORY_ITEM, "Raw Dusts", 24150).getInt(24150);
		dustRefinedID = HMConfig.get(Configuration.CATEGORY_ITEM, "Refined Dusts", 24151).getInt(24151);
		partsID = HMConfig.get(Configuration.CATEGORY_ITEM, "Parts", 24152).getInt(24152);
		blueprintID = HMConfig.get(Configuration.CATEGORY_ITEM, "Blueprints", 24153).getInt(24153);
		endiumAlloyID = HMConfig.get(Configuration.CATEGORY_ITEM, "Endium Alloy", 24154).getInt(24154);
		
		ACHprospector = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Prospector", 1500).getInt(1500);
		ACHtimeToCrush = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Time To Crush", 1501).getInt(1501);
		ACHminerkiin = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Minerkiin", 1503).getInt(1503);
		ACHwash = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Wash", 1504).getInt(1504);
		
		if (FMLCommonHandler.instance().getSide().isServer())
		{
			HMConfig.addCustomCategoryComment("advanced_settings", "Advanced server OP settings, don't be a moron with them.");
			crusherTicks = HMConfig.get("advanced_settings", "Crusher Ticks", 180).getInt(180);
			washerTicks = HMConfig.get("advanced_settings", "Washer Ticks", 100).getInt(100);
			
		}
		
		HMConfig.save();
		
		crusher = new HawkBlockCrusher(crusherID).setStepSound(Block.soundMetalFootstep);
		endiumOre = new HawkBlock(endiumOreID, Material.rock, 19).setStepSound(Block.soundStoneFootstep);
		washer = new HawkBlockWasher(washerID).setStepSound(Block.soundMetalFootstep);
		if (enableChunkloader)
		{
			chunkloader = new HawkBlockChunkloader(chunkloaderID);
			ForgeChunkManager.setForcedChunkLoadingCallback(this, MANAGER);
			
		}
		
		dustRaw = new HawkItemRawDust(dustRawID - 256);
		dustRefined = new HawkItemRefinedDust(dustRefinedID - 256);
		parts = new HawkItemParts(partsID - 256);
		blueprints = new HawkItemBlueprints(blueprintID - 256);
		endiumItems = new HawkItemEndium(endiumAlloyID - 256);
		
		NetworkRegistry.instance().registerGuiHandler(this, this.PROXY);
		GameRegistry.registerWorldGenerator(MANAGER);
		GameRegistry.registerCraftingHandler(this);
		AchievementPage.registerAchievementPage(MANAGER.HAWKSPAGE);
		NetworkRegistry.instance().registerConnectionHandler(this.PROXY);
		VillagerRegistry.instance().registerVillageTradeHandler(0, MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(1, MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(2, MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(3, MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(4, MANAGER);
		
		OreDictionary.registerOre("dustCoal", new ItemStack(dustRaw, 1, 0));
		OreDictionary.registerOre("dustRawIron", new ItemStack(dustRaw, 1, 1));
		OreDictionary.registerOre("dustRawGold", new ItemStack(dustRaw, 1, 2));
		OreDictionary.registerOre("dustRawCopper", new ItemStack(dustRaw, 1, 3));
		OreDictionary.registerOre("dustRawTin", new ItemStack(dustRaw, 1, 4));
		OreDictionary.registerOre("dustRawObsidian", new ItemStack(dustRaw, 1, 5));
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
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		PROXY.registerRenderInformation();
		loadRecipes();
		new HMUpdateHandler(ModConverter.getMod(getClass()));
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		loadProcessingRecipes();
		
	}
	
	public static HawksMachinery instance()
	{
		return INSTANCE;
	}
	
	/**
	 * Loads all of the recipes for Hawk's Machinery, including regular crafting recipes.
	 */
	public static void loadRecipes()
	{
		
		//RECIPE_GIVER.addRecipe(new ItemStack(blockCrusher, 1), new Object[]{"TPT", "TMT", "TBT", 'T', new ItemStack(plating, 1, 0), 'P', Item.pickaxeSteel, 'M', BasicComponents.itemMotor, 'B', ((ItemBattery)BasicComponents.itemBattery).getUnchargedItemStack()});
		//RECIPE_GIVER.addRecipe(new ItemStack(blockWasher, 1), new Object[]{"AWA", "BPA", "AbD", 'A', new ItemStack(plating, 1, 1), 'W', Block.cloth, 'P', new ItemStack(parts, 1, 0), 'D', Block.dispenser, 'B', ((ItemBattery)BasicComponents.itemBattery).getUnchargedItemStack(), 'b', Item.bucketEmpty});
		if (enableChunkloader)
		{
			//RECIPE_GIVER.addRecipe(new ItemStack(blockChunkloader, 1), new Object[]{"EBE", "BIB", "EBE", 'E', "ingotEndium", 'B', BasicComponents.itemBronzePlate, 'I', Item.eyeOfEnder});
			//RECIPE_GIVER.addShapelessRecipe(new ItemStack(ingots, 4, 3), new Object[]{blockChunkloader});
			
		}
		
		RECIPE_GIVER.addRecipe(new ItemStack(BasicComponents.itemBattery), new Object[]{" x ", "xrx", "xcx", 'x', BasicComponents.itemTinIngot, 'c', new ItemStack(dustRaw, 1, 0), 'r', Item.redstone});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"c", "s", 'c', new ItemStack(dustRaw, 1, 0), 's', Item.stick});
		RECIPE_GIVER.addRecipe(new ItemStack(Block.glass, 1), new Object[]{"GG", "GG", 'G', new ItemStack(dustRefined, 1, 2)});
		
		RECIPE_GIVER.addRecipe(new ItemStack(parts, 1, 0), new Object[]{" B ", "PSM", " B ", 'P', BasicComponents.itemSteelPlate, 'S', BasicComponents.itemSteelIngot, 'M', BasicComponents.itemMotor, 'B', Item.blazePowder});
		RECIPE_GIVER.addRecipe(new ItemStack(parts, 1, 1), new Object[]{" B ", "RGR", "SLS", 'B', Item.blazePowder, 'R', Item.redstone, 'G', Block.glass, 'S', "ingotSteel", 'L', new ItemStack(parts, 1, 3)});
		RECIPE_GIVER.addRecipe(new ItemStack(parts, 1, 2), new Object[]{" T ", "TET", " T ", 'T', "ingotTitanium", 'E', Item.enderPearl});
		RECIPE_GIVER.addRecipe(new ItemStack(parts, 1, 3), new Object[]{" G ", "GBG", "cCc", 'G', Block.thinGlass, 'B', Item.blazeRod, 'c', "ingotCopper", 'C', BasicComponents.blockCopperWire});
		RECIPE_GIVER.addRecipe(new ItemStack(parts, 1, 4), new Object[]{"CC", "CC", 'C', BasicComponents.blockCopperWire});
		RECIPE_GIVER.addRecipe(new ItemStack(parts, 1, 5), new Object[]{"ici", 'i', "ingotIron", 'c', new ItemStack(parts, 1, 4)});
		
		RECIPE_GIVER.addShapelessRecipe(BasicComponents.itemSteelDust, new Object[]{new ItemStack(dustRaw, 1, 0), new ItemStack(dustRefined, 1, 3)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.blazePowder, Item.gunpowder, new ItemStack(dustRaw, 1, 0)});
		RECIPE_GIVER.addShapelessRecipe(new ItemStack(endiumItems, 1, 2), new Object[]{new ItemStack(dustRefined, 1, 12), new ItemStack(dustRefined, 1, 11), new ItemStack(dustRefined, 1, 9)});
		
		RECIPE_GIVER.addSmelting(new ItemStack(dustRefined, 1, 2), new ItemStack(Block.thinGlass));
		RECIPE_GIVER.addSmelting(new ItemStack(dustRefined, 1, 3), new ItemStack(Item.ingotIron));
		RECIPE_GIVER.addSmelting(new ItemStack(dustRefined, 1, 4), new ItemStack(Item.ingotGold));
		RECIPE_GIVER.addSmelting(new ItemStack(dustRefined, 1, 5), new ItemStack(BasicComponents.itemCopperIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(dustRefined, 1, 6), new ItemStack(BasicComponents.itemTinIngot));
		RECIPE_GIVER.addSmelting(new ItemStack(dustRaw, 1, 8), new ItemStack(Block.obsidian));
		
	}
	
	public static void loadProcessingRecipes()
	{
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.diamond, new ItemStack(dustRefined, 1, 0), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.enderPearl, new ItemStack(dustRefined, 1, 1), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.glass, new ItemStack(dustRefined, 4, 2), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(BasicComponents.itemCopperIngot, new ItemStack(dustRaw, 1, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(BasicComponents.itemTinIngot, new ItemStack(dustRaw, 1, 4), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.blazeRod, new ItemStack(Item.blazePowder, 2), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.bone, new ItemStack(Item.dyePowder, 4, 15), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.stone, new ItemStack(Block.gravel), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.cobblestone, new ItemStack(Block.sand), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.gravel, new ItemStack(Item.flint, 2), CRUSH);
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.eyeOfEnder, new ItemStack(dustRefined, 1, 1), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.pistonBase, new ItemStack(dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.pistonStickyBase, new ItemStack(dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.dispenser, new ItemStack(Item.bow), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.stoneOvenIdle, new ItemStack(Block.cobblestone, 8), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.thinGlass, new ItemStack(dustRefined, 1, 2), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.glowStone, new ItemStack(Item.lightStoneDust, 4), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.redstoneLampIdle, new ItemStack(Item.lightStoneDust, 4), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.enchantmentTable, new ItemStack(Item.diamond, 2), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.brewingStand, new ItemStack(Item.blazeRod, 1), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.sandStone, new ItemStack(Block.sand, 4), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.obsidian, new ItemStack(dustRaw, 1, 8), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.glassBottle, new ItemStack(dustRefined, 4, 2), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.thinGlass, new ItemStack(dustRefined, 1, 2), CRUSH);
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.minecartEmpty, new ItemStack(dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.minecartPowered, new ItemStack(dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.minecartCrate, new ItemStack(dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.doorSteel, new ItemStack(dustRefined, 6, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.bucketEmpty, new ItemStack(dustRefined, 3, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.compass, new ItemStack(dustRefined, 4, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.pocketSundial, new ItemStack(dustRefined, 4, 4), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.cauldron, new ItemStack(dustRefined, 7, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.plantRed, new ItemStack(Item.dyePowder, 2, 1), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(Block.plantYellow, new ItemStack(Item.dyePowder, 2, 11), CRUSH);
		
		PROCESS_RECIPES.addHawkProcessingRecipe(Item.emerald, new ItemStack(dustRefined, 1, 10), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.coal, 1, 0), new ItemStack(dustRaw, 1, 0), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.coal, 1, 1), new ItemStack(dustRaw, 1, 0), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 0), new ItemStack(Block.stoneBrick, 1), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 1), new ItemStack(Block.cobblestone), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 2), new ItemStack(Block.cobblestoneMossy), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 3), new ItemStack(Block.cobblestone), CRUSH);
		
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.helmetSteel), new ItemStack(dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.plateSteel), new ItemStack(dustRefined, 8, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.legsSteel), new ItemStack(dustRefined, 7, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.bootsSteel), new ItemStack(dustRefined, 4, 3), CRUSH);
		
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.swordSteel), new ItemStack(dustRefined, 2, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.pickaxeSteel), new ItemStack(dustRefined, 3, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.shovelSteel), new ItemStack(dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.axeSteel), new ItemStack(dustRefined, 3, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(Item.hoeSteel), new ItemStack(dustRefined, 2, 3), CRUSH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(BasicComponents.itemBattery), new ItemStack(dustRefined, 5, 6), CRUSH);
		
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("oreIron", new ItemStack(dustRaw, 2, 1), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("oreGold", new ItemStack(dustRaw, 2, 2), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("oreCopper", new ItemStack(dustRaw, 2, 3), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("oreTin", new ItemStack(dustRaw, 2, 4), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("oreAluminum", new ItemStack(dustRaw, 2, 5), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("oretitanium", new ItemStack(dustRaw, 2, 6), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("oreSilver", new ItemStack(dustRaw, 2, 7), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("oreEndium", new ItemStack(dustRaw, 2, 9), CRUSH);
		
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("ingotCopper", new ItemStack(dustRefined, 1, 5), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("ingotTin", new ItemStack(dustRefined, 1, 6), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("ingotTitanium", new ItemStack(dustRefined, 1, 7), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("ingotAluminum", new ItemStack(dustRefined, 1, 8), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("ingotSilver", new ItemStack(dustRefined, 1, 9), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("ingotEndium", new ItemStack(dustRefined, 1, 12), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("ingotSteel", new ItemStack(BasicComponents.itemSteelDust), CRUSH);
		PROCESS_RECIPES.addHawkFoDProcessingRecipe("ingotBronze", new ItemStack(BasicComponents.itemBronzeDust), CRUSH);
		
		//WASHING RECIPES
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(dustRaw, 1, 1), new ItemStack(dustRefined, 1, 3), WASH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(dustRaw, 1, 2), new ItemStack(dustRefined, 1, 4), WASH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(dustRaw, 1, 3), new ItemStack(dustRefined, 1, 5), WASH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(dustRaw, 1, 4), new ItemStack(dustRefined, 1, 6), WASH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(dustRaw, 1, 5), new ItemStack(dustRefined, 1, 7), WASH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(dustRaw, 1, 6), new ItemStack(dustRefined, 1, 8), WASH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(dustRaw, 1, 7), new ItemStack(dustRefined, 1, 9), WASH);
		PROCESS_RECIPES.addHawkProcessingRecipe(new ItemStack(dustRaw, 1, 9), new ItemStack(dustRefined, 1, 12), WASH);
		
	}
	
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
	{
		if (item.itemID == crusher.blockID)
		{
			player.addStat(MANAGER.timeToCrush, 1);
		}
		
		/*
		if (item.itemID == blockMetalStorage.blockID)
		{
			player.addStat(MANAGER.compactCompact, 1);
		}
		*/
	}
	
	@Override
	public void onSmelting(EntityPlayer player, ItemStack item){}
	
	public class HMUpdateHandler extends UpdateManagerMod
	{
		public HMUpdateHandler(Mod m)
		{
			super(m);
		}
		
		@Override
		public String getModURL()
		{
			return "http://bit.ly/UeISkn";
		}
		
		@Override
		public String getUpdateURL()
		{
			return "https://dl.dropbox.com/u/100525141/HawksMachineryVersion.txt";
		}
		
		@Override
		public ModType getModType()
		{
			return ModType.ADDON;
		}
		
		@Override
		public String getModName()
		{
			return "Hawk's Machinery";
		}
		
		@Override
		public String getChangelogURL()
		{
			return this.getReleaseType() == ModReleaseType.DEVBUILD ? "https://dl.dropbox.com/u/100525141/HawksMachineryDevbuildNotice.txt"
					: "https://dl.dropbox.com/u/100525141/HawksMachineryAlphav133Changelog.txt" ;
		}
		
		@Override
		public String getDirectDownloadURL()
		{
			return "https://dl.dropbox.com/u/100525141/HawksMachneryDLLink.txt";
		}
		
		@Override
		public String getDisclaimerURL()
		{
			return "https://dl.dropbox.com/u/100525141/HawksMachineryDisclaimer.txt";
		}
		
		@Override
		public boolean enableAutoDownload()
		{
			return enableAutoDL;
		}
		
		@Override
		public CheckingMethod getCheckingMethod()
		{
			return CheckingMethod.LEXICOGRAPHICAL;
		}
		
		@Override
		public boolean disableChecks()
		{
			return UpdateManager.isDebug ? true : !enableUpdateChecking;
		}
		
		@Override
		public boolean srcOnly()
		{
			return false;
		}
		
		@Override
		public ModReleaseType getReleaseType()
		{
			return ModReleaseType.DEVBUILD;
		}
		
		@Override
		public ItemStack getIconStack()
		{
			return new ItemStack(crusher);
		}
		
		@Override
		public String getSpecialButtonName()
		{
			return "Forge Forum";
		}
		
		@Override
		public void onSpecialButtonClicked()
		{
			UpdateManager.openWebpage("http://bit.ly/TbyBoC");
			
		}
		
	}
	
}
