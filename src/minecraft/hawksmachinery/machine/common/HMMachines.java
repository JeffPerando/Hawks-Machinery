
package hawksmachinery.machine.common;

import hawksmachinery.core.common.HMCore;
import hawksmachinery.core.common.api.HMRecipes;
import hawksmachinery.core.common.api.HMRecipes.HMEnumProcessing;
import hawksmachinery.core.common.item.HMItem;
import hawksmachinery.core.common.tileentity.HMTileEntityEndiumChunkloader;
import hawksmachinery.machine.common.block.*;
import hawksmachinery.machine.common.item.*;
import hawksmachinery.machine.common.tileentity.*;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import universalelectricity.prefab.network.ConnectionHandler;
import universalelectricity.prefab.network.PacketManager;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@Mod(modid = "HMMachines", name = "Hawk's Machinery", version = HMCore.VERSION, useMetadata = true, dependencies = "after:HMCore")
@NetworkMod(channels = {"HMMachines"}, clientSideRequired = true, serverSideRequired = false, connectionHandler = ConnectionHandler.class, packetHandler = PacketManager.class)
public class HMMachines implements IVillageTradeHandler, IFuelHandler
{
	@SidedProxy(clientSide = "hawksmachinery.machine.client.HMClientProxyMachine", serverSide = "hawksmachinery.machine.common.HMCommonProxyMachine")
	public static HMCommonProxyMachine PROXY;
	
	@Instance("HMMachines")
	private static HMMachines INSTANCE;
	
	public static Block crusher;
	public static Block washer;
	public static Block fisher;
	public static Block starForge;
	public static Block sinterer;
	
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Obsidian, 6 - Endium, 7 - Cobalt.
	 */
	public static HMItem dustRaw;
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Emerald, 8 - Nether Star, 9 - Endium, 10 - Cobalt.
	 */
	public static HMItem dustRefined;
	public static HMItem blueprints;
	public static HMItem rivets;
	public static Item rivetGun;
	public static Item fishFood;
	public static Item machineMeter;
	public static Item testTube;
	public static Item nanites;
	
	public static HMMachines instance()
	{
		return INSTANCE;
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		PROXY.registerRenderInformation();
		VillagerRegistry.instance().registerVillageTradeHandler(1, this);
		
		NetworkRegistry.instance().registerGuiHandler(this, PROXY);
		NetworkRegistry.instance().registerConnectionHandler(PROXY);
		
		crusher = new HMBlockCrusher(HMCore.instance().PROXY.getBlockID("Crusher", 2550));
		washer = new HMBlockWasher(HMCore.instance().PROXY.getBlockID("Washer", 2552));
		//TODO Reoccupy ID #2554 with the new Endium Teleporter.
		fisher = new HMBlockFisher(HMCore.instance().PROXY.getBlockID("Fisher", 2555));
		starForge = new HMBlockStarForge(HMCore.instance().PROXY.getBlockID("Star Forge", 2557));
		sinterer = new HMBlockSinterer(HMCore.instance().PROXY.getBlockID("Sinterer", 2559));
		//HMBlock.fireBlock = new HMBlockFireBlock(this.MANAGER.getBlockID("Fire Block", 2560));
		
		dustRaw = new HMItemRawDust(HMCore.instance().PROXY.getItemID("Raw Dusts", 24150)).setMaxDmg(8);
		dustRefined = new HMItemRefinedDust(HMCore.instance().PROXY.getItemID("Refined Dusts", 24151)).setMaxDmg(10);
		blueprints = new HMItemBlueprints(HMCore.instance().PROXY.getItemID("Blueprints", 24153)).setMaxDmg(8);
		rivets = new HMItemRivets(HMCore.instance().PROXY.getItemID("Rivets", 24155)).setMaxDmg(6);
		rivetGun = new HMItemRivetGun(HMCore.instance().PROXY.getItemID("Rivet Gun", 24156));
		fishFood = new HMItem(HMCore.instance().PROXY.getItemID("Fish Food", 24158)).setIconIndex(104).setItemName("HMFishFood").setCreativeTab(HMCore.instance().tab);
		machineMeter = new HMItemMeter(HMCore.instance().PROXY.getItemID("Machine Meter", 24159));
		testTube = new HMItem(HMCore.instance().PROXY.getItemID("Test Tube", 24161)).setItemName("HMTestTube").setCreativeTab(HMCore.instance().tab).setIconIndex(72);
		nanites = new HMItemNanite(HMCore.instance().PROXY.getItemID("Nanites", 24162)).setMaxDmg(15);
		
		GameRegistry.registerTileEntity(HMTileEntityCrusher.class, "HMCrusher");
		GameRegistry.registerTileEntity(HMTileEntityWasher.class, "HMWasher");
		GameRegistry.registerTileEntity(HMTileEntityFisher.class, "HMFisher");
		GameRegistry.registerTileEntity(HMTileEntityStarForge.class, "HMStarForge");
		GameRegistry.registerTileEntity(HMTileEntitySinterer.class, "HMSinterer");
		
		GameRegistry.registerBlock(crusher, HMItemBlockMachine.class);
		GameRegistry.registerBlock(washer, HMItemBlockMachine.class);
		GameRegistry.registerBlock(fisher, HMItemBlockMachine.class);
		GameRegistry.registerBlock(starForge, HMItemBlockMachine.class);
		GameRegistry.registerBlock(sinterer, HMItemBlockMachine.class);
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		this.loadRecipes();
		this.loadProcessingRecipes();
		
	}
	
	/**
	 * Loads all of the vMC recipes for Hawk's Machinery.
	 */
	public void loadRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(crusher), new Object[]{"IPI", "IEI", "ICI", 'I', "ingotCobalt", 'P', Item.pickaxeSteel, 'E', new ItemStack(HMCore.parts, 1, 6), 'C', Item.ingotGold}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(washer), new Object[]{"iBi", "iWi", "IEI", 'i', "ingotCobalt", 'B', Item.bucketEmpty, 'I', Block.blockSteel, 'W', Block.cloth, 'E', new ItemStack(HMCore.parts, 1, 6)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(((HMItemRivetGun)rivetGun).getUncharged(), new Object[]{"CLC", "IBI", "IPI", 'C', "ingotCobalt", 'I', Item.ingotIron, 'L', Block.lever, 'B', Item.ingotGold, 'P', Block.pistonBase}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fisher), new Object[]{"CEC", "CFC", "CHC", 'C', "ingotCobalt", 'E', new ItemStack(HMCore.parts, 1, 6), 'F', Item.fishingRod, 'H', Block.chest}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sinterer), new Object[]{"CHC", "CFC", "CHC", 'C', "ingotCobalt", 'F', Block.stoneOvenIdle, 'H', new ItemStack(HMCore.parts, 1, 4)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(starForge), new Object[]{" N ", "CSC", "GGG", 'N', Item.netherStar, 'C', new ItemStack(HMCore.plating, 1, 1), 'S', sinterer, 'G', Item.ingotGold}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineMeter), new Object[]{"BCg", "ECs", "GsI", 'B', Item.blazeRod, 'C', "ingotCobalt", 'g', Item.goldNugget, 'E', Item.eyeOfEnder, 's', Item.silk, 'i', new ItemStack(dustRefined, 1, 3)}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(crusher), new Object[]{"ingotCobalt", "ingotCobalt", Item.pickaxeSteel, new ItemStack(blueprints), new ItemStack(HMCore.parts, 1, 6)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(washer), new Object[]{Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.ingotIron, Item.bucketEmpty, new ItemStack(blueprints, 1, 1), Block.cloth, new ItemStack(HMCore.parts, 1, 6)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(((HMItemRivetGun)rivetGun).getUncharged(), new Object[]{"ingotCobalt", Item.ingotIron, Block.lever, new ItemStack(HMCore.plating, 1, 1), Item.goldNugget, new ItemStack(blueprints, 1, 8), Block.pistonBase}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(fisher), new Object[]{"ingotCobalt", "ingotCobalt", new ItemStack(blueprints, 1, 3), Block.chest, Item.fishingRod, new ItemStack(HMCore.parts, 1, 6)}));
		
		if (HMCore.instance().PROXY.enableChunkloader)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMCore.endiumChunkloader), new Object[]{"ECE", "C@C", "ECE", 'E', "ingotEndium", 'C', new ItemStack(HMCore.plating, 1, 1), '@', Item.eyeOfEnder}));
			GameRegistry.addShapelessRecipe(new ItemStack(HMCore.ingots, 4, 0), HMCore.endiumChunkloader);
			
		}
		
		GameRegistry.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"c", "s", 'c', new ItemStack(dustRaw, 1, 0), 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(Block.glass, 1), new Object[]{"GG", "GG", 'G', new ItemStack(dustRefined, 1, 2)});
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rivets, 8, 0), new Object[]{"CCC", " C ", " B ", 'C', "ingotCopper", 'B', Item.blazeRod}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rivets, 8, 1), new Object[]{"BBB", " B ", " b ", 'B', "ingotBronze", 'b', Item.blazeRod}));
		GameRegistry.addRecipe(new ItemStack(rivets, 8, 2), new Object[]{"III", " I ", " B ", 'I', Item.ingotIron, 'B', Item.blazePowder});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rivets, 8, 3), new Object[]{"SSS", " S ", " B ", 'S', "ingotSteel", 'B', Item.blazeRod}));
		GameRegistry.addRecipe(new ItemStack(rivets, 8, 4), new Object[]{"GGG", " G ", " B ", 'G', Item.ingotGold, 'B', Item.blazePowder});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rivets, 8, 5), new Object[]{"EEE", " E ", " B ", 'E', "ingotEndium", 'B', Item.blazeRod}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rivets, 8, 6), new Object[]{"CCC", " C ", " B ", 'C', "ingotCobalt", 'B', Item.blazeRod}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.blazePowder, Item.gunpowder, new ItemStack(dustRaw, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(fishFood, 4), new Object[]{Item.bread, Item.rottenFlesh});
		
		FurnaceRecipes.smelting().addSmelting(dustRefined.itemID, 2, new ItemStack(Block.thinGlass), 0.0F);
		FurnaceRecipes.smelting().addSmelting(dustRaw.itemID, 8, new ItemStack(Block.obsidian), 0.0F);
		
	}
	
	public void loadProcessingRecipes()
	{
		HMRecipes.addProcessingRecipe(new ItemStack(Item.diamond), new ItemStack(dustRefined, 1, 0), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.enderPearl), new ItemStack(dustRefined, 1, 1), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.glass), new ItemStack(dustRefined, 4, 2), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.blazeRod), new ItemStack(Item.blazePowder, 2), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.bone), new ItemStack(Item.dyePowder, 4, 15), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.stone), new ItemStack(Block.gravel), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.cobblestone), new ItemStack(Block.sand), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.gravel), new ItemStack(Item.flint, 2), HMEnumProcessing.CRUSHING);
		
		HMRecipes.addProcessingRecipe(new ItemStack(Item.eyeOfEnder), new ItemStack(dustRefined, 1, 1), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.pistonBase), new ItemStack(dustRefined, 1, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.pistonStickyBase), new ItemStack(dustRefined, 1, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.dispenser), new ItemStack(Item.bow), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.stoneOvenIdle), new ItemStack(Block.cobblestone, 8), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.thinGlass), new ItemStack(dustRefined, 1, 2), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.glowStone), new ItemStack(Item.lightStoneDust, 4), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.redstoneLampIdle), new ItemStack(Item.lightStoneDust, 4), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.brewingStand), new ItemStack(Item.blazeRod, 1), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.sandStone), new ItemStack(Block.sand, 4), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.obsidian), new ItemStack(dustRaw, 1, 5), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.glassBottle), new ItemStack(dustRefined, 4, 2), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.thinGlass), new ItemStack(dustRefined, 1, 2), HMEnumProcessing.CRUSHING);
		
		HMRecipes.addProcessingRecipe(new ItemStack(Item.minecartEmpty), new ItemStack(dustRefined, 5, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.minecartPowered), new ItemStack(dustRefined, 5, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.minecartCrate), new ItemStack(dustRefined, 5, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.doorSteel), new ItemStack(dustRefined, 6, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.bucketEmpty), new ItemStack(dustRefined, 3, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.compass), new ItemStack(dustRefined, 4, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.pocketSundial), new ItemStack(dustRefined, 4, 4), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.cauldron), new ItemStack(dustRefined, 7, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.plantRed), new ItemStack(Item.dyePowder, 2, 1), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.plantYellow), new ItemStack(Item.dyePowder, 2, 11), HMEnumProcessing.CRUSHING);
		
		HMRecipes.addProcessingRecipe(new ItemStack(Item.emerald), new ItemStack(dustRefined, 1, 7), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.coal, 1, 0), new ItemStack(dustRaw, 1, 0), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.coal, 1, 1), new ItemStack(dustRaw, 1, 0), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 0), new ItemStack(Block.stoneBrick, 1), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 1), new ItemStack(Block.cobblestone), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 2), new ItemStack(Block.cobblestoneMossy), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.stoneBrick, 1, 3), new ItemStack(Block.cobblestone), HMEnumProcessing.CRUSHING);
		
		HMRecipes.addProcessingRecipe(new ItemStack(Item.netherStar), new ItemStack(dustRefined, 3, 8), HMEnumProcessing.CRUSHING);
		
		HMRecipes.addProcessingRecipe(new ItemStack(Block.oreIron), new ItemStack(dustRaw, 2, 1), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Block.oreGold), new ItemStack(dustRaw, 2, 2), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.ingotIron), new ItemStack(dustRefined, 1, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addProcessingRecipe(new ItemStack(Item.ingotGold), new ItemStack(dustRefined, 1, 4), HMEnumProcessing.CRUSHING);
		
		HMRecipes.addFoDProcessingRecipe("oreCopper", new ItemStack(dustRaw, 2, 3), HMEnumProcessing.CRUSHING);
		HMRecipes.addFoDProcessingRecipe("oreTin", new ItemStack(dustRaw, 2, 4), HMEnumProcessing.CRUSHING);
		HMRecipes.addFoDProcessingRecipe("oreEndium", new ItemStack(dustRaw, 2, 6), HMEnumProcessing.CRUSHING);
		HMRecipes.addFoDProcessingRecipe("oreCobalt", new ItemStack(dustRaw, 2, 7), HMEnumProcessing.CRUSHING);
		
		HMRecipes.addFoDProcessingRecipe("ingotCopper", new ItemStack(dustRefined, 1, 5), HMEnumProcessing.CRUSHING);
		HMRecipes.addFoDProcessingRecipe("ingotTin", new ItemStack(dustRefined, 1, 6), HMEnumProcessing.CRUSHING);
		HMRecipes.addFoDProcessingRecipe("ingotEndium", new ItemStack(dustRefined, 1, 9), HMEnumProcessing.CRUSHING);
		HMRecipes.addFoDProcessingRecipe("ingotCobalt", new ItemStack(dustRefined, 1, 10), HMEnumProcessing.CRUSHING);
		
		HMRecipes.addProcessingRecipe(new ItemStack(dustRaw, 1, 1), new ItemStack(dustRefined, 1, 3), HMEnumProcessing.WASHING);
		HMRecipes.addProcessingRecipe(new ItemStack(dustRaw, 1, 2), new ItemStack(dustRefined, 1, 4), HMEnumProcessing.WASHING);
		HMRecipes.addProcessingRecipe(new ItemStack(dustRaw, 1, 3), new ItemStack(dustRefined, 1, 5), HMEnumProcessing.WASHING);
		HMRecipes.addProcessingRecipe(new ItemStack(dustRaw, 1, 4), new ItemStack(dustRefined, 1, 6), HMEnumProcessing.WASHING);
		HMRecipes.addProcessingRecipe(new ItemStack(dustRaw, 1, 6), new ItemStack(dustRefined, 1, 9), HMEnumProcessing.WASHING);
		HMRecipes.addProcessingRecipe(new ItemStack(dustRaw, 1, 7), new ItemStack(dustRefined, 1, 10), HMEnumProcessing.WASHING);
		
		HMRecipes.addForgeRecipe(new ShapelessOreRecipe(new ItemStack(HMCore.ingots, 1, 0), new Object[]{"dustEndium", "dustStar"}));
		
		HMRecipes.addProcessingRecipe(new ItemStack(dustRefined, 1, 3), new ItemStack(Item.ingotIron), HMEnumProcessing.SINTERER);
		HMRecipes.addProcessingRecipe(new ItemStack(dustRefined, 1, 4), new ItemStack(Item.ingotGold), HMEnumProcessing.SINTERER);
		
		if (!OreDictionary.getOres("ingotCopper").isEmpty())
		{
			HMRecipes.addProcessingRecipe(new ItemStack(dustRefined, 1, 5), OreDictionary.getOres("ingotCopper").get(0), HMEnumProcessing.SINTERER);
			
		}
		
		if (!OreDictionary.getOres("ingotTin").isEmpty())
		{
			HMRecipes.addProcessingRecipe(new ItemStack(dustRefined, 1, 6), OreDictionary.getOres("ingotTin").get(0), HMEnumProcessing.SINTERER);
			
		}
		
	}
	
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		for (int emeralds = 10; emeralds <= 58; ++emeralds)
		{
			if (emeralds <= 17)
			{
				recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(blueprints, 1, 7)));
				
			}
			
			if (emeralds >= 24 && emeralds <= 32)
			{
				for (int meta = 0; meta <= 6; ++meta)
				{
					recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(blueprints, 1, meta)));
				}
				
			}
			
			if (emeralds >= 52)
			{
				recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(blueprints, 1, 8)));
			}
			
		}
		
	}
	
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if (fuel.isItemEqual(new ItemStack(dustRaw, 1, 0)))
		{
			return 1600;
		}
		
		return 0;
	}
	
}
