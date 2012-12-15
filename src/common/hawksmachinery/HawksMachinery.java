
package hawksmachinery;

import hawksmachinery.api.*;
import hawksmachinery.api.HMRecipes.HMEnumProcessing;
import hawksmachinery.block.*;
import hawksmachinery.item.*;
import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementList;
import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import obsidian.api.ItemRetriever;
import universalelectricity.prefab.UETab;
import universalelectricity.prefab.multiblock.BlockMulti;
import universalelectricity.prefab.network.ConnectionHandler;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.ore.OreGenerator;
import updatemanager.client.ModReleaseType;
import updatemanager.client.ModType;
import updatemanager.common.ModConverter;
import updatemanager.common.UpdateManager;
import updatemanager.common.UpdateManagerMod;
import updatemanager.common.checking.CheckingMethod;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
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
	
	public static final String VERSION = "Beta v1.0.0";
	
	public static HMRecipes PROCESS_RECIPES;
	public static HMEnumProcessing CRUSH = HMEnumProcessing.CRUSHING;
	public static HMEnumProcessing WASH = HMEnumProcessing.WASHING;
	
	public HMManager MANAGER;
	
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
		this.MANAGER = new HMManager(INSTANCE);
		
		this.MANAGER.loadConfig();
		
		HMBlock.crusher = new HMBlockCrusher(this.MANAGER.getBlockID("Crusher", 2550));
		HMBlock.ore = new HMBlockOre(this.MANAGER.getBlockID("Ore", 2551));
		HMBlock.washer = new HMBlockWasher(this.MANAGER.getBlockID("Washer", 2552));
		HMBlock.endiumChunkloader = new HMBlockEndiumChunkloader(this.MANAGER.getBlockID("Endium Chunkloader", 2553));
		HMBlock.endiumTeleporter = new HMBlockEndiumTeleporter(this.MANAGER.getBlockID("Endium Teleporter", 2554));
		HMBlock.fisher = new HMBlockFisher(this.MANAGER.getBlockID("Fisher", 2555));
		HMBlock.metalBlock = new HMBlockMetalStorage(this.MANAGER.getBlockID("Metal Block", 2556));
		HMBlock.starForge = new HMBlockStarForge(this.MANAGER.getBlockID("Star Forge", 2557));
		HMBlock.starForgeTechnical = new HMBlockMulti(this.MANAGER.getBlockID("Star Forge Technical", 2558));
		HMBlock.sinterer = new HMBlockSinterer(this.MANAGER.getBlockID("Sinterer", 2559));
		HMBlock.fireBlock = new HMBlockFireBlock(this.MANAGER.getBlockID("Fire Block", 2560));
		
		HMItem.dustRaw = new HMItemRawDust(this.MANAGER.getItemID("Raw Dusts", 24150)).setMaxDmg(7);
		HMItem.dustRefined = new HMItemRefinedDust(this.MANAGER.getItemID("Refined Dusts", 24151)).setMaxDmg(10);
		HMItem.parts = new HMItemParts(this.MANAGER.getItemID("Parts", 24152)).setMaxDmg(6);
		HMItem.blueprints = new HMItemBlueprints(this.MANAGER.getItemID("Blueprints", 24153)).setMaxDmg(8);
		HMItem.plating = new HMItemPlating(this.MANAGER.getItemID("Plating", 24154)).setMaxDmg(1);
		HMItem.rivets = new HMItemRivets(this.MANAGER.getItemID("Rivets", 24155)).setMaxDmg(6);
		HMItem.rivetGun = new HMItemRivetGun(this.MANAGER.getItemID("Rivet Gun", 24156));
		HMItem.ingots = new HMItemIngots(this.MANAGER.getItemID("Ingots", 24157)).setMaxDmg(1);
		HMItem.fishFood = new HMItem(this.MANAGER.getItemID("Fish Food", 24158)).setIconIndex(104).setItemName("fishFood").setCreativeTab(UETab.INSTANCE);
		
		timeToCrush = new Achievement(this.MANAGER.ACHtimeToCrush, "Crush", -2, -3, new ItemStack(HMBlock.crusher, 1, 0), AchievementList.buildBetterPickaxe).registerAchievement();
		minerkiin = new Achievement(this.MANAGER.ACHminerkiin, "Minerkiin", -5, 2, new ItemStack(HMBlock.ore), AchievementList.theEnd2).registerAchievement().setSpecial();
		wash = new Achievement(this.MANAGER.ACHwash, "Wash", 0, -4, new ItemStack(HMBlock.washer, 1, 0), AchievementList.buildBetterPickaxe).registerAchievement();
		HAWKSPAGE = new AchievementPage("Hawk's Machinery", timeToCrush, minerkiin, wash);
		
		new HMContent();
		NetworkRegistry.instance().registerGuiHandler(this, PROXY);
		GameRegistry.registerCraftingHandler(this.MANAGER);
		AchievementPage.registerAchievementPage(HAWKSPAGE);
		NetworkRegistry.instance().registerConnectionHandler(PROXY);
		VillagerRegistry.instance().registerVillageTradeHandler(0, this.MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(1, this.MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(2, this.MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(3, this.MANAGER);
		VillagerRegistry.instance().registerVillageTradeHandler(4, this.MANAGER);
		ForgeChunkManager.setForcedChunkLoadingCallback(this, this.MANAGER);
		PROXY.addVanillaLangHandlers();
		MinecraftForge.EVENT_BUS.register(this.MANAGER);
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		this.loadRecipes();
		new HMUpdateHandler(ModConverter.getMod(getClass()));
		OreGenerator.addOre(new HMEndiumOreGen());
		OreGenerator.addOre(new HMCobaltOreGen());
		PROXY.registerRenderInformation();
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		this.loadProcessingRecipes();
		
	}
	
	public static HawksMachinery instance()
	{
		return INSTANCE;
	}
	
	/**
	 * Loads all of the vMC recipes for Hawk's Machinery.
	 */
	public void loadRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMBlock.crusher), new Object[]{"IPI", "IEI", "ICI", 'I', "ingotCobalt", 'P', Item.pickaxeSteel, 'E', new ItemStack(HMItem.parts, 1, 6), 'C', "ingotGold"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMBlock.washer), new Object[]{"iBi", "iWi", "IEI", 'i', "ingotCobalt", 'B', Item.bucketEmpty, 'I', Block.blockSteel, 'W', Block.cloth, 'E', new ItemStack(HMItem.parts, 1, 6)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(((HMItemRivetGun)HMItem.rivetGun).getUncharged(), new Object[]{"CLC", "IBI", "IPI", 'C', "ingotCobalt", 'I', "ingotIron", 'L', Block.lever, 'B', "ingotGold", 'P', Block.pistonBase}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMBlock.fisher), new Object[]{"CEC", "CFC", "CHC", 'C', "ingotCobalt", 'E', new ItemStack(HMItem.parts, 1, 6), 'F', Item.fishingRod, 'H', Block.chest}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(HMBlock.crusher), new Object[]{"ingotCobalt", "ingotCobalt", Item.pickaxeSteel, new ItemStack(HMItem.blueprints), new ItemStack(HMItem.parts, 1, 6)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(HMBlock.washer), new Object[]{"ingotIron", "ingotIron", "ingotIron", "ingotIron", Item.bucketEmpty, new ItemStack(HMItem.blueprints, 1, 1), Block.cloth, new ItemStack(HMItem.parts, 1, 6)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(((HMItemRivetGun)HMItem.rivetGun).getUncharged(), new Object[]{"ingotCobalt", "ingotIron", Block.lever, new ItemStack(HMItem.plating, 1, 1), Item.goldNugget, new ItemStack(HMItem.blueprints, 1, 8), Block.pistonBase}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(HMBlock.fisher), new Object[]{"ingotCobalt", "ingotCobalt", new ItemStack(HMItem.blueprints, 1, 3), Block.chest, Item.fishingRod, new ItemStack(HMItem.parts, 1, 6)}));
		
		if (this.MANAGER.enableChunkloader)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMBlock.endiumChunkloader), new Object[]{"ECE", "C@C", "ECE", 'E', new ItemStack(HMItem.ingots), 'C', new ItemStack(HMItem.plating, 1, 1), '@', Item.eyeOfEnder}));
			GameRegistry.addShapelessRecipe(new ItemStack(HMItem.ingots, 4, 0), HMBlock.endiumChunkloader);
			
		}
		
		GameRegistry.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"c", "s", 'c', new ItemStack(HMItem.dustRaw, 1, 0), 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(Block.glass, 1), new Object[]{"GG", "GG", 'G', new ItemStack(HMItem.dustRefined, 1, 2)});
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 0), new Object[]{" B ", "PSM", " B ", 'P', new ItemStack(HMItem.plating, 1, 1), 'S', "ingotCobalt", 'M', Block.pistonBase, 'B', Item.blazePowder}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 1), new Object[]{" B ", "RGR", "SLS", 'B', Item.blazePowder, 'R', Item.redstone, 'G', Block.glass, 'S', "ingotCobalt", 'L', new ItemStack(HMItem.parts, 1, 3)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.parts, 6, 2), new Object[]{"C", "C", "C", 'C', "ingotCobalt"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 3), new Object[]{" G ", "GBG", "CCC", 'G', Block.thinGlass, 'B', Item.blazeRod, 'C', Item.goldNugget}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 4), new Object[]{"CC", "CC", 'C', Block.fenceIron}));
		GameRegistry.addRecipe(new ItemStack(HMItem.parts, 1, 5), new Object[]{"ici", 'i', Item.ingotIron, 'c', new ItemStack(HMItem.parts, 1, 4)});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.parts, 1, 6), new Object[]{"OOS", "BPb", "OOS", 'O', Block.obsidian, 'S', "ingotCobalt", 'B', Item.blazePowder, 'P', new ItemStack(HMItem.parts), 'b', "ingotGold"}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 0), new Object[]{"CCC", "BCB", " C ", 'C', "ingotCopper", 'B', Item.blazePowder}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 1), new Object[]{"BBB", "bBb", " B ", 'B', "ingotBronze", 'b', Item.blazePowder}));
		GameRegistry.addRecipe(new ItemStack(HMItem.rivets, 10, 2), new Object[]{"III", "BIB", " I ", 'I', Item.ingotIron, 'B', Item.blazePowder});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 3), new Object[]{"SSS", "BSB", " S ", 'S', "ingotSteel", 'B', Item.blazePowder}));
		GameRegistry.addRecipe(new ItemStack(HMItem.rivets, 10, 4), new Object[]{"GGG", "BGB", " G ", 'G', Item.ingotGold, 'B', Item.blazePowder});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 5), new Object[]{"EEE", "BEB", " E ", 'E', "ingotEndium", 'B', Item.blazePowder}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.rivets, 10, 6), new Object[]{"CCC", "CEC", " C ", 'C', "ingotCobalt", 'B', Item.blazePowder}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMBlock.metalBlock, 1, 0), new Object[]{"EEE", "EEE", "EEE", 'E', "ingotEndium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMBlock.metalBlock, 1, 1), new Object[]{"CCC", "CCC", "CCC", 'C', "ingotCobalt"}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.plating, 1, 0), new Object[]{"MM", "MM", 'M', "ingotEndium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMItem.plating, 1, 1), new Object[]{"MM", "MM", 'M', "ingotCobalt"}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(HMItem.ingots, 9, 0), new ItemStack(HMBlock.metalBlock, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(HMItem.ingots, 9, 1), new ItemStack(HMBlock.metalBlock, 1, 1));
		
		//TODO Temporary until the Star Forge is done.
		GameRegistry.addShapelessRecipe(new ItemStack(HMItem.ingots, 1, 0), new ItemStack(HMItem.dustRefined, 1, 8), new ItemStack(HMItem.dustRefined, 1, 9), new ItemStack(HMItem.dustRefined));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.blazePowder, Item.gunpowder, new ItemStack(HMItem.dustRaw, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(HMItem.fishFood, 4), new Object[]{Item.bread, Item.rottenFlesh});
		
		FurnaceRecipes.smelting().addSmelting(HMItem.dustRefined.shiftedIndex, 2, new ItemStack(Block.thinGlass), 0.0F);
		FurnaceRecipes.smelting().addSmelting(HMItem.dustRefined.shiftedIndex, 3, new ItemStack(Item.ingotIron), 0.0F);
		FurnaceRecipes.smelting().addSmelting(HMItem.dustRefined.shiftedIndex, 4, new ItemStack(Item.ingotGold), 0.0F);
		FurnaceRecipes.smelting().addSmelting(HMItem.dustRaw.shiftedIndex, 8, new ItemStack(Block.obsidian), 0.0F);
		FurnaceRecipes.smelting().addSmelting(HMItem.plating.shiftedIndex, 0, new ItemStack(HMItem.ingots, 4, 0), 0.0F);
		FurnaceRecipes.smelting().addSmelting(HMItem.plating.shiftedIndex, 1, new ItemStack(HMItem.ingots, 4, 1), 0.0F);
		
		if (OreDictionary.getOres("ingotCopper") != null)
		{
			FurnaceRecipes.smelting().addSmelting(HMItem.dustRefined.shiftedIndex, 5, OreDictionary.getOres("ingotCopper").get(0), 0);
			
		}
		
		if (OreDictionary.getOres("ingotTin") != null)
		{
			FurnaceRecipes.smelting().addSmelting(HMItem.dustRefined.shiftedIndex, 6, OreDictionary.getOres("ingotTin").get(0), 0);
			
		}
		
	}
	
	public void loadProcessingRecipes()
	{
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.diamond), new ItemStack(HMItem.dustRefined, 1, 0), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.enderPearl), new ItemStack(HMItem.dustRefined, 1, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.glass), new ItemStack(HMItem.dustRefined, 4, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.blazeRod), new ItemStack(Item.blazePowder, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.bone), new ItemStack(Item.dyePowder, 4, 15), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.stone), new ItemStack(Block.gravel), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.cobblestone), new ItemStack(Block.sand), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.gravel), new ItemStack(Item.flint, 2), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.eyeOfEnder), new ItemStack(HMItem.dustRefined, 1, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.pistonBase), new ItemStack(HMItem.dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.pistonStickyBase), new ItemStack(HMItem.dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.dispenser), new ItemStack(Item.bow), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.stoneOvenIdle), new ItemStack(Block.cobblestone, 8), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.thinGlass), new ItemStack(HMItem.dustRefined, 1, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.glowStone), new ItemStack(Item.lightStoneDust, 4), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.redstoneLampIdle), new ItemStack(Item.lightStoneDust, 4), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.brewingStand), new ItemStack(Item.blazeRod, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.sandStone), new ItemStack(Block.sand, 4), CRUSH);
		
		if (Loader.isModLoaded("ObsidianIngots"))
		{
			PROCESS_RECIPES.addHMProcessingRecipe(ItemRetriever.getItem("obsidianDust"), new ItemStack(HMItem.dustRaw, 1, 5), CRUSH);
			
		}
		else
		{
			PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.obsidian), new ItemStack(HMItem.dustRaw, 1, 5), CRUSH);
			
		}
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.glassBottle), new ItemStack(HMItem.dustRefined, 4, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.thinGlass), new ItemStack(HMItem.dustRefined, 1, 2), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.minecartEmpty), new ItemStack(HMItem.dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.minecartPowered), new ItemStack(HMItem.dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.minecartCrate), new ItemStack(HMItem.dustRefined, 5, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.doorSteel), new ItemStack(HMItem.dustRefined, 6, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.bucketEmpty), new ItemStack(HMItem.dustRefined, 3, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.compass), new ItemStack(HMItem.dustRefined, 4, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.pocketSundial), new ItemStack(HMItem.dustRefined, 4, 4), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.cauldron), new ItemStack(HMItem.dustRefined, 7, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.plantRed), new ItemStack(Item.dyePowder, 2, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.plantYellow), new ItemStack(Item.dyePowder, 2, 11), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.emerald), new ItemStack(HMItem.dustRefined, 1, 7), CRUSH);
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
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.netherStar), new ItemStack(HMItem.dustRefined, 3, 8), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.oreIron), new ItemStack(HMItem.dustRaw, 2, 1), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Block.oreGold), new ItemStack(HMItem.dustRaw, 2, 2), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.ingotIron), new ItemStack(HMItem.dustRefined, 1, 3), CRUSH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(Item.ingotGold), new ItemStack(HMItem.dustRefined, 1, 4), CRUSH);
		
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreCopper", new ItemStack(HMItem.dustRaw, 2, 3), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreTin", new ItemStack(HMItem.dustRaw, 2, 4), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreEndium", new ItemStack(HMItem.dustRaw, 2, 6), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("oreCobalt", new ItemStack(HMItem.dustRaw, 2, 7), CRUSH);
		
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotCopper", new ItemStack(HMItem.dustRefined, 1, 5), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotTin", new ItemStack(HMItem.dustRefined, 1, 6), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotEndium", new ItemStack(HMItem.dustRefined, 1, 9), CRUSH);
		PROCESS_RECIPES.addHMFoDProcessingRecipe("ingotCobalt", new ItemStack(HMItem.dustRefined, 1, 10), CRUSH);
		
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 1), new ItemStack(HMItem.dustRefined, 1, 3), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 2), new ItemStack(HMItem.dustRefined, 1, 4), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 3), new ItemStack(HMItem.dustRefined, 1, 5), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 4), new ItemStack(HMItem.dustRefined, 1, 6), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 6), new ItemStack(HMItem.dustRefined, 1, 9), WASH);
		PROCESS_RECIPES.addHMProcessingRecipe(new ItemStack(HMItem.dustRaw, 1, 7), new ItemStack(HMItem.dustRefined, 1, 10), WASH);
		
		PROCESS_RECIPES.addHMForgeRecipe(new ShapelessOreRecipe(new ItemStack(HMItem.ingots, 1, 0), new Object[]{"dustEndium", "dustStar"}));
		
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
			return "http://bit.ly/TFrC6z";
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
			return "https://dl.dropbox.com/u/100525141/HawksMachineryBetav100UEFTBChangelog.txt"; //"https://dl.dropbox.com/u/100525141/HawksMachinery" + VERSION.replace(".", "").replace(" ", "") + "Changelog.txt";
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
			return ModReleaseType.BETA;
		}
		
		@Override
		public ItemStack getIconStack()
		{
			return new ItemStack(HMBlock.crusher);
		}
		
	}
	
}
