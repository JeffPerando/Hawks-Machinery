
package hawksmachinery;

import universalelectricity.UniversalElectricity;
import universalelectricity.network.PacketManager;
import net.minecraft.src.Block;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.ItemSpade;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StepSound;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
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

/**
 * 
 * The mod file for Hawk's Machinery.
 * 
 * @author Elusivehawk
 */
@Mod(modid = "HawksMachinery", name = "Hawk's Machinery", version = "Alpha v1.2.4", dependencies = "after:UniversalElectricity")
@NetworkMod(channels = {"HawksMachinery"}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketManager.class)
public class HawksMachinery
{
	@Instance
	public static HawksMachinery INSTANCE;
	
	@SidedProxy(clientSide = "hawksmachinery.HMClientProxy", serverSide = "hawksmachinery.HMCommonProxy")
	public static HMCommonProxy PROXY;
	
	public static HawkManager MANAGER = new HawkManager();
	
	/**
	 * Note: DO NOT MOVE THIS! I'm serious, I don't want to see any refactor job move this, due to the fact that doing so is A VERY BAD IDEA!
	 */
	public static Block blockGrinder;
	public static Block blockOre;
	public static Block blockMetalStorage;
	
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Titanium, 6 - Aluminum, 7 - Silver, 8- Obsidian.
	 */
	public static Item dustRaw;
	
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Titanium, 8 - Aluminum, 9 - Silver, 10 - Emerald. 
	 */
	public static Item dustRefined;
	public static Item ingots;
	public static Item parts;
	public static Item plating;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		blockGrinder = new HawkBlockGrinder(MANAGER.initProps()).setStepSound(Block.soundMetalFootstep);
		blockOre = new HawkBlockOre(HawkManager.oreID).setStepSound(Block.soundStoneFootstep);
		blockMetalStorage = new HawkBlockMetalStorage(HawkManager.metalStorageID).setStepSound(Block.soundMetalFootstep);
		
		dustRaw = new HawkItemRawDust(HawkManager.dustRawID - 256);
		dustRefined = new HawkItemRefinedDust(HawkManager.dustRefinedID - 256);
		ingots = new HawkItemIngots(HawkManager.ingotsID - 256);
		parts = new HawkItemParts(HawkManager.partsID - 256);
		plating = new HawkItemPlating(HawkManager.platingID - 256);
		
		UniversalElectricity.registerMod(this, "Hawk's Machinery", "0.6.0");
		NetworkRegistry.instance().registerGuiHandler(this, this.PROXY);
		GameRegistry.registerWorldGenerator(new HawkOreGenerator());
		GameRegistry.registerFuelHandler(MANAGER);
		AchievementPage.registerAchievementPage(HawkAchievements.HAWKSPAGE);
		
		DungeonHooks.addDungeonLoot(new ItemStack(ingots, 1, 0), 075, 1, 4);
		DungeonHooks.addDungeonLoot(new ItemStack(ingots, 1, 1), 075, 1, 4);
		DungeonHooks.addDungeonLoot(new ItemStack(ingots, 1, 2), 075, 1, 4);
		
		GameRegistry.registerTileEntity(HawkTileEntityGrinder.class, "HMGrinder");
		GameRegistry.registerTileEntity(HawkTileEntityWasher.class, "HMWasher");
		
		OreDictionary.registerOre("ingotTitanium", new ItemStack(ingots, 1, 0));
		OreDictionary.registerOre("ingotAluminum", new ItemStack(ingots, 1, 1));
		OreDictionary.registerOre("ingotSilver", new ItemStack(ingots, 1, 2));
		OreDictionary.registerOre("ingotEndium", new ItemStack(ingots, 1, 3));
		
		OreDictionary.registerOre("oreTitanium", new ItemStack(blockOre, 1, 0));
		OreDictionary.registerOre("oreAluminum", new ItemStack(blockOre, 1, 1));
		OreDictionary.registerOre("oreBauxium", new ItemStack(blockOre, 1, 1));
		OreDictionary.registerOre("oreSilver", new ItemStack(blockOre, 1, 2));
		OreDictionary.registerOre("oreEndium", new ItemStack(blockOre, 1, 3));
		
		PROXY.preInit();
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		PROXY.init();
		
		HawkManager.loadRecipes();
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		HawkManager.loadProcessingRecipes();
		
	}
	
}
