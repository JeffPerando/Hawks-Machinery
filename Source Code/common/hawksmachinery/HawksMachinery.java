
package hawksmachinery;

import hawksmachinery.padAPI.HawkPadAPICore;
import universalelectricity.UniversalElectricity;
import universalelectricity.network.PacketManager;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.AchievementPage;
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
 * The main file for Hawk's Machinery.
 * 
 * @author Elusivehawk
 */
@Mod(modid = "HawksMachinery", name = "Hawk's Machinery", version = "Alpha v1.2 Prerelease 2", dependencies = "after:UniversalElectricity")
@NetworkMod(channels = { "HawksMachinery" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketManager.class)
public class HawksMachinery
{
	@Instance
	public static HawksMachinery INSTANCE;
	
	@SidedProxy(clientSide = "hawksmachinery.HMClientProxy", serverSide = "hawksmachinery.HMCommonProxy")
	public static HMCommonProxy PROXY;
	
	public static HawkPadManager PAD_MANAGER = new HawkPadManager();
	
	/**
	 * Note: DO NOT MOVE THIS! I'm serious, I don't want to see any refactor job move this, due to the fact that doing so is A VERY BAD IDEA!
	 */
	public static Block blockGrinder = new HawkBlockGrinder(HawkManager.initProps());
	public static Block blockOre = new HawkBlockOre(HawkManager.oreID);
	public static Block blockMetalStorage = new HawkBlockMetalStorage(HawkManager.metalStorageID);
	
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Obsidian, 6 - Titanium, 7 - Aluminum, 8 - Silver.
	 */
	public static Item dustRaw = new HawkItemRawDust(HawkManager.dustRawID);
	
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Titanium, 8 - Aluminum, 9 - Silver, 10 - Emerald. 
	 */
	public static Item dustRefined = new HawkItemRefinedDust(HawkManager.dustRefinedID);
	public static Item ingots = new HawkItemIngots(HawkManager.ingotsID);
	public static Item parts = new HawkItemParts(HawkManager.partsID);
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		UniversalElectricity.registerMod(this, "Hawk's Machinery", UniversalElectricity.VERSION);
		NetworkRegistry.instance().registerGuiHandler(this, this.PROXY);
		
		//To whoever decided to make "static" and "this" incompatible: Bazinga, punk!
		GameRegistry.registerWorldGenerator(new HawkOreGenerator());
		
		HawkPadAPICore.registerEffectHandler(PAD_MANAGER);
		HawkPadAPICore.registerElectricityHandler(PAD_MANAGER);
		HawkPadAPICore.registerTextureHandler(PAD_MANAGER);
		
		AchievementPage.registerAchievementPage(HawkAchievements.HAWKSPAGE);
		
		PROXY.preInit();
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		PROXY.init();
		
		OreDictionary.registerOre("ingotTitanium", new ItemStack(ingots, 1, 0));
		OreDictionary.registerOre("ingotAluminum", new ItemStack(ingots, 1, 1));
		OreDictionary.registerOre("ingotSilver", new ItemStack(ingots, 1, 2));
		OreDictionary.registerOre("ingotEndium", new ItemStack(ingots, 1, 3));
		
		OreDictionary.registerOre("oreTitanium", new ItemStack(blockOre, 1, 0));
		OreDictionary.registerOre("oreAluminum", new ItemStack(blockOre, 1, 1));
		OreDictionary.registerOre("oreBauxium", new ItemStack(blockOre, 1, 1));
		OreDictionary.registerOre("oreSilver", new ItemStack(blockOre, 1, 2));
		OreDictionary.registerOre("oreEndium", new ItemStack(blockOre, 1, 3));
		
		HawkManager.loadRecipes();
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		
	}
}
