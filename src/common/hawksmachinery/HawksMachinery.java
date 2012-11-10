
package hawksmachinery;

import hawksmachinery.api.HMContent;
import hawksmachinery.api.HMProcessingRecipes;
import hawksmachinery.api.HMProcessingRecipes.HMEnumProcessing;
import hawksmachinery.blocks.*;
import hawksmachinery.items.*;
import hawksmachinery.tileentity.*;
import java.io.File;
import java.util.List;
import obsidian.api.ItemRetriever;
import com.google.common.collect.ObjectArrays;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.ItemElectric;
import universalelectricity.prefab.network.ConnectionHandler;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.ore.OreGenBase;
import universalelectricity.prefab.ore.OreGenerator;
import vazkii.um.client.ModReleaseType;
import vazkii.um.client.ModType;
import vazkii.um.common.ModConverter;
import vazkii.um.common.UpdateManager;
import vazkii.um.common.UpdateManagerMod;
import vazkii.um.common.checking.CheckingMethod;
import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementList;
import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.StepSound;
import net.minecraft.src.World;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
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
@Mod(modid = "HawksMachinery", name = "Hawk's Machinery", version = HawksMachinery.VERSION, useMetadata = true)
@NetworkMod(channels = {"HawksMachinery"}, clientSideRequired = true, serverSideRequired = false, connectionHandler = ConnectionHandler.class, packetHandler = PacketManager.class)
public class HawksMachinery
{
	@Instance("HawksMachinery")
	private static HawksMachinery INSTANCE;
	
	@SidedProxy(clientSide = "hawksmachinery.HMClientProxy", serverSide = "hawksmachinery.HMCommonProxy")
	public static HMCommonProxy PROXY;
	
	@Metadata("HawksMachinery")
	public static ModMetadata HAWK_META = new HMDummyContainer().getMetadata();
	
	public static final String VERSION = "Alpha v1.5.1";
	
	public static HMProcessingRecipes PROCESS_RECIPES;
	public static HMEnumProcessing CRUSH = HMEnumProcessing.CRUSHING;
	public static HMEnumProcessing WASH = HMEnumProcessing.WASHING;
	
	public static HMManager MANAGER = new HMManager(instance());
	
	public static final String GUI_PATH = "/hawksmachinery/resources/gui";
	public static final String BLOCK_TEXTURE_FILE = "/hawksmachinery/resources/textures/blocks.png";
	public static final String ITEM_TEXTURE_FILE = "/hawksmachinery/resources/textures/items.png";
	public static final String TEXTURE_PATH = "/hawksmachinery/resources/textures";
	public static final String SOUND_PATH = "/hawksmachinery/resources/sounds";
	
	public static Achievement timeToCrush;
	public static Achievement minerkiin;
	public static Achievement wash;
	
	public static AchievementPage HAWKSPAGE;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		HMBlock.crusher = new HMBlockCrusher(MANAGER.loadConfig());
		HMBlock.ore = new HMBlockOre(MANAGER.endiumOreID);
		HMBlock.washer = new HMBlockWasher(MANAGER.washerID);
		HMBlock.endiumTeleporter = new HMBlockEndiumTeleporter(MANAGER.endiumTeleporterID);
		
		HMContent.crusher = HMBlock.crusher;
		HMContent.ore = HMBlock.ore;
		HMContent.washer = HMBlock.washer;
		HMContent.endiumTeleporter = HMBlock.endiumTeleporter;
		
		if (MANAGER.enableChunkloader)
		{
			HMBlock.endiumChunkloader = new HMBlockEndiumChunkloader(MANAGER.endiumChunkloaderID);
			HMContent.endiumChunkloader = HMBlock.endiumChunkloader;
			ForgeChunkManager.setForcedChunkLoadingCallback(this, MANAGER);
			
		}
		
		HMItem.dustRaw = new HMItemRawDust(MANAGER.dustRawID - 256);
		HMItem.dustRefined = new HMItemRefinedDust(MANAGER.dustRefinedID - 256);
		HMItem.parts = new HMItemParts(MANAGER.partsID - 256);
		HMItem.blueprints = new HMItemBlueprints(MANAGER.blueprintID - 256);
		HMItem.endiumPlate = new HMItem(MANAGER.endiumPlateID - 256).setIconIndex(51, 0).setEffect(0).setItemName("endiumPlate", 0).setCreativeTab(CreativeTabs.tabMaterials);
		HMItem.rivets = new HMItemRivets(MANAGER.rivetsID - 256).setEffect(5);
		HMItem.rivetGun = new HMItemRivetGun(MANAGER.rivetGunID - 256);
		HMItem.ingots = new HMItemIngots(MANAGER.ingotsID - 256);
		
		HMContent.dustRaw = HMItem.dustRaw;
		HMContent.dustRefined = HMItem.dustRefined;
		HMContent.parts = HMItem.parts;
		HMContent.blueprints = HMItem.blueprints;
		HMContent.endiumPlate = HMItem.endiumPlate;
		HMContent.rivets = HMItem.rivets;
		HMContent.rivetGun = HMItem.rivetGun;
		HMContent.ingots = HMItem.ingots;
		
		timeToCrush = new Achievement(MANAGER.ACHtimeToCrush, "Time to Crush", -2, -3, new ItemStack(HMBlock.crusher, 1, 0), AchievementList.buildBetterPickaxe).registerAchievement();
		minerkiin = new Achievement(MANAGER.ACHminerkiin, "Minerkiin", -5, 2, new ItemStack(HMBlock.ore), AchievementList.theEnd2).registerAchievement().setSpecial();
		wash = new Achievement(MANAGER.ACHwash, "Wash", 0, -4, new ItemStack(HMBlock.washer, 1, 0), AchievementList.buildBetterPickaxe).registerAchievement();
		
		HAWKSPAGE = new AchievementPage("Hawk's Machinery", timeToCrush, minerkiin, wash);
		
		NetworkRegistry.instance().registerGuiHandler(this, PROXY);
		GameRegistry.registerCraftingHandler(MANAGER);
		GameRegistry.registerBlock(HMBlock.ore, HMItemBlockEndium.class);
		AchievementPage.registerAchievementPage(HAWKSPAGE);
		NetworkRegistry.instance().registerConnectionHandler(PROXY);
		VillagerRegistry.instance().registerVillageTradeHandler(0, MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(1, MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(2, MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(3, MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(4, MANAGER);
		
		OreDictionary.registerOre("dustCoal", new ItemStack(HMItem.dustRaw, 1, 0));
		OreDictionary.registerOre("dustRawIron", new ItemStack(HMItem.dustRaw, 1, 1));
		OreDictionary.registerOre("dustRawGold", new ItemStack(HMItem.dustRaw, 1, 2));
		OreDictionary.registerOre("dustRawCopper", new ItemStack(HMItem.dustRaw, 1, 3));
		OreDictionary.registerOre("dustRawTin", new ItemStack(HMItem.dustRaw, 1, 4));
		OreDictionary.registerOre("dustObsidian", new ItemStack(HMItem.dustRaw, 1, 5));
		OreDictionary.registerOre("dustRawEndium", new ItemStack(HMItem.dustRaw, 1, 6));
		
		OreDictionary.registerOre("dustDiamond", new ItemStack(HMItem.dustRefined, 1, 0));
		OreDictionary.registerOre("dustEnder", new ItemStack(HMItem.dustRefined, 1, 1));
		OreDictionary.registerOre("dustGlass", new ItemStack(HMItem.dustRefined, 1, 2));
		OreDictionary.registerOre("dustIron", new ItemStack(HMItem.dustRefined, 1, 3));
		OreDictionary.registerOre("dustGold", new ItemStack(HMItem.dustRefined, 1, 4));
		OreDictionary.registerOre("dustCopper", new ItemStack(HMItem.dustRefined, 1, 5));
		OreDictionary.registerOre("dustTin", new ItemStack(HMItem.dustRefined, 1, 6));
		OreDictionary.registerOre("dustEmerald", new ItemStack(HMItem.dustRefined, 1, 7));
		OreDictionary.registerOre("dustStar", new ItemStack(HMItem.dustRefined, 1, 8));
		OreDictionary.registerOre("dustEndium", new ItemStack(HMItem.dustRefined, 1, 9));
		
		OreDictionary.registerOre("ingotEndium", new ItemStack(HMItem.ingots));
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		PROXY.registerRenderInformation();
		loadRecipes();
		new HMUpdateHandler(ModConverter.getMod(getClass()));
		OreGenerator.addOre(new HMEndiumOreGen());
		
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
	 * Loads all of the vMC recipes for Hawk's Machinery.
	 */
	public static void loadRecipes()
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMBlock.crusher), new Object[]{"IPI", "SES", "SCS", 'I', "ingotIron", 'P', Item.pickaxeSteel, 'S', "plateSteel", 'E', new ItemStack(HMItem.parts, 1, 6), 'C', "copperWire"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMBlock.washer), new Object[]{"iBi", "iWi", "IEI", 'i', "ingotIron", 'B', Item.bucketEmpty, 'I', Block.blockSteel, 'W', Block.cloth, 'E', new ItemStack(HMItem.parts, 1, 6)}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(((HMItemRivetGun)HMItem.rivetGun).getUncharged(), new Object[]{"SLS", "XBX", "TPT", 'S', "ingotSteel", 'L', Block.lever, 'X', "plateSteel", 'B', "battery", 'T', "plateTin", 'P', Block.pistonBase}));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMBlock.crusher), new Object[]{"IP", "BE", "SS", 'I', "ingotIron", 'P', Item.pickaxeSteel, 'B', new ItemStack(HMItem.blueprints), 'E', new ItemStack(HMItem.parts, 1, 6), 'S', "plateSteel"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMBlock.washer), new Object[]{"ibi", "BWi", "iEi", 'i', "ingotIron", 'b', Item.bucketEmpty, 'B', new ItemStack(HMItem.blueprints, 1, 1), 'W', Block.cloth, 'E', new ItemStack(HMItem.parts, 1, 6)}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(((HMItemRivetGun)HMItem.rivetGun).getUncharged(), new Object[]{"SL ", "XBb", "TP ", 'S', "ingotSteel", 'L', Block.lever, 'X', "plateSteel", 'B', "battery", 'b', new ItemStack(HMItem.blueprints, 1, 8), 'T', "plateTin", 'P', Block.pistonBase}));
		
		if (MANAGER.enableChunkloader)
		{
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMBlock.endiumChunkloader), new Object[]{"EBE", "B@B", "EBE", 'E', new ItemStack(HMItem.ingots), 'B', "plateBronze", '@', Item.eyeOfEnder}));
			GameRegistry.addSmelting(HMBlock.endiumChunkloader.blockID, new ItemStack(HMItem.ingots, 3), 2.0F);
			
		}
		
		GameRegistry.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"c", "s", 'c', new ItemStack(HMItem.dustRaw, 1, 0), 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(Block.glass, 1), new Object[]{"GG", "GG", 'G', new ItemStack(HMItem.dustRefined, 1, 2)});
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 0), new Object[]{" B ", "PSM", " B ", 'P', "plateSteel", 'S', "ingotSteel", 'M', "motor", 'B', Item.blazePowder}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 1), new Object[]{" B ", "RGR", "SLS", 'B', Item.blazePowder, 'R', Item.redstone, 'G', Block.glass, 'S', "ingotSteel", 'L', new ItemStack(HMItem.parts, 1, 3)}));
		//CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 2), new Object[]{" T ", "TET", " T ", 'T', "ingotIron", 'E', Item.enderPearl}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 3), new Object[]{" G ", "GBG", "cCc", 'G', Block.thinGlass, 'B', Item.blazeRod, 'c', "ingotCopper", 'C', "copperWire"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 4), new Object[]{"CC", "CC", 'C', "copperWire"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 5), new Object[]{"ici", 'i', "ingotIron", 'c', new ItemStack(HMItem.parts, 1, 4)}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 6), new Object[]{"OOS", "BPb", "OOS", 'O', Block.obsidian, 'S', "ingotSteel", 'B', Item.blazePowder, 'P', new ItemStack(HMItem.parts), 'b', "ingotGold"}));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 0), new Object[]{"CCC", "BCB", " C ", 'C', "ingotCopper", 'B', Item.blazePowder}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 1), new Object[]{"BBB", "bBb", " B ", 'B', "ingotBronze", 'b', Item.blazePowder}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 2), new Object[]{"III", "BIB", " I ", 'I', "ingotIron", 'B', Item.blazePowder}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 3), new Object[]{"SSS", "BSB", " S ", 'S', "ingotSteel", 'B', Item.blazePowder}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 4), new Object[]{"GGG", "BGB", " G ", 'G', "ingotGold", 'B', Item.blazePowder}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 5), new Object[]{"EEE", "BEB", " E ", 'E', "ingotEndium", 'B', Item.blazePowder}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.blazePowder, Item.gunpowder, new ItemStack(HMItem.dustRaw, 1, 0)});
		
		FurnaceRecipes.smelting().addSmelting(HMItem.dustRefined.shiftedIndex, 2, new ItemStack(Block.thinGlass));
		FurnaceRecipes.smelting().addSmelting(HMItem.dustRefined.shiftedIndex, 3, new ItemStack(Item.ingotIron));
		FurnaceRecipes.smelting().addSmelting(HMItem.dustRefined.shiftedIndex, 4, new ItemStack(Item.ingotGold));
		FurnaceRecipes.smelting().addSmelting(HMItem.dustRaw.shiftedIndex, 8, new ItemStack(Block.obsidian));
		
	}
	
	public static void loadProcessingRecipes()
	{
		PROCESS_RECIPES.addHMProcessingRecipe(Item.diamond, new ItemStack(HMItem.dustRefined, 1, 0), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.enderPearl, new ItemStack(HMItem.dustRefined, 1, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.glass, new ItemStack(HMItem.dustRefined, 4, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.blazeRod, new ItemStack(Item.blazePowder, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.bone, new ItemStack(Item.dyePowder, 4, 15), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.stone, new ItemStack(Block.gravel), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.cobblestone, new ItemStack(Block.sand), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.gravel, new ItemStack(Item.flint, 2), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(Item.eyeOfEnder, new ItemStack(HMItem.dustRefined, 1, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.pistonBase, new ItemStack(HMItem.dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.pistonStickyBase, new ItemStack(HMItem.dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.dispenser, new ItemStack(Item.bow), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.stoneOvenIdle, new ItemStack(Block.cobblestone, 8), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.thinGlass, new ItemStack(HMItem.dustRefined, 1, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.glowStone, new ItemStack(Item.lightStoneDust, 4), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.redstoneLampIdle, new ItemStack(Item.lightStoneDust, 4), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.brewingStand, new ItemStack(Item.blazeRod, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.sandStone, new ItemStack(Block.sand, 4), CRUSH);
		
		if (Loader.isModLoaded("ObsidianIngots"))
		{
			PROCESS_RECIPES.addHMProcessingRecipe(ItemRetriever.getItem("obsidianDust"), new ItemStack(HMItem.dustRaw, 1, 5), CRUSH);
		}
		else
		{
			PROCESS_RECIPES.addHMProcessingRecipe(Block.obsidian, new ItemStack(HMItem.dustRaw, 1, 5), CRUSH);
		}
		PROCESS_RECIPES.addHMProcessingRecipe(Item.glassBottle, new ItemStack(HMItem.dustRefined, 4, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.thinGlass, new ItemStack(HMItem.dustRefined, 1, 2), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(Item.minecartEmpty, new ItemStack(HMItem.dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.minecartPowered, new ItemStack(HMItem.dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.minecartCrate, new ItemStack(HMItem.dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.doorSteel, new ItemStack(HMItem.dustRefined, 6, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.bucketEmpty, new ItemStack(HMItem.dustRefined, 3, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.compass, new ItemStack(HMItem.dustRefined, 4, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.pocketSundial, new ItemStack(HMItem.dustRefined, 4, 4), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Item.cauldron, new ItemStack(HMItem.dustRefined, 7, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.plantRed, new ItemStack(Item.dyePowder, 2, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(Block.plantYellow, new ItemStack(Item.dyePowder, 2, 11), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(Item.emerald, new ItemStack(HMItem.dustRefined, 1, 7), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.coal, 1, 0), new ItemStack(HMItem.dustRaw, 1, 0), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.coal, 1, 1), new ItemStack(HMItem.dustRaw, 1, 0), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 0), new ItemStack(Block.stoneBrick, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 1), new ItemStack(Block.cobblestone), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 2), new ItemStack(Block.cobblestoneMossy), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 3), new ItemStack(Block.cobblestone), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.helmetSteel), new ItemStack(HMItem.dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.plateSteel), new ItemStack(HMItem.dustRefined, 8, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.legsSteel), new ItemStack(HMItem.dustRefined, 7, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.bootsSteel), new ItemStack(HMItem.dustRefined, 4, 3), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.swordSteel), new ItemStack(HMItem.dustRefined, 2, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.pickaxeSteel), new ItemStack(HMItem.dustRefined, 3, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.shovelSteel), new ItemStack(HMItem.dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.axeSteel), new ItemStack(HMItem.dustRefined, 3, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.hoeSteel), new ItemStack(HMItem.dustRefined, 2, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.field_82792_bS), new ItemStack(HMItem.dustRefined, 3, 8), CRUSH);
		
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreIron", new ItemStack(HMItem.dustRaw, 2, 1), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreGold", new ItemStack(HMItem.dustRaw, 2, 2), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreCopper", new ItemStack(HMItem.dustRaw, 2, 3), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreTin", new ItemStack(HMItem.dustRaw, 2, 4), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreEndium", new ItemStack(HMItem.dustRaw, 2, 6), CRUSH);
		
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotIron", new ItemStack(HMItem.dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotGold", new ItemStack(HMItem.dustRefined, 1, 4), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotCopper", new ItemStack(HMItem.dustRefined, 1, 5), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotTin", new ItemStack(HMItem.dustRefined, 1, 6), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotEndium", new ItemStack(HMItem.dustRefined, 1, 9), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 1), new ItemStack(HMItem.dustRefined, 1, 3), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 2), new ItemStack(HMItem.dustRefined, 1, 4), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 3), new ItemStack(HMItem.dustRefined, 1, 5), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 4), new ItemStack(HMItem.dustRefined, 1, 6), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 6), new ItemStack(HMItem.dustRefined, 1, 9), WASH);
		
	}
	
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
					: "https://dl.dropbox.com/u/100525141/HawksMachineryAlphav150Changelog.txt" ;
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
			return MANAGER.enableAutoDL;
		}
		
		@Override
		public CheckingMethod getCheckingMethod()
		{
			return CheckingMethod.LEXICOGRAPHICAL;
		}
		
		@Override
		public boolean disableChecks()
		{
			return UpdateManager.isDebug ? true : !MANAGER.enableUpdateChecking;
		}
		
		@Override
		public boolean srcOnly()
		{
			return false;
		}
		
		@Override
		public ModReleaseType getReleaseType()
		{
			return ModReleaseType.ALPHA;
		}
		
		@Override
		public ItemStack getIconStack()
		{
			return new ItemStack(HMBlock.crusher);
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
