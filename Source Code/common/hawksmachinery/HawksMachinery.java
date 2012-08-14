
package hawksmachinery;

import universalelectricity.UniversalElectricity;
import universalelectricity.network.PacketManager;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
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
 * 
 * 
 * @author Elusivehawk
 */
@Mod(modid = "HawksMachinery", name = "Hawk's Machinery", version = "Alpha v1.2")
@NetworkMod(channels = { "HawksMachinery" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketManager.class)
public class HawksMachinery
{
	@Instance
	public static HawksMachinery instance;
	
	@SidedProxy(clientSide = "hawksmachinery.HMClientProxy", serverSide = "hawksmachinery.HMCommonProxy")
	public static HMCommonProxy proxy;
	
	/**
	 * Note: DO NOT MOVE THIS! I'm serious, I don't want to see any refactor job move this, due to the fact that doing so is A VERY BAD IDEA!
	 */
	public static Block blockGrinder = new HawkBlockGrinder(HawkManager.initProps(), Material.iron);
	public static Block blockEmptyMachine = new HawkBlockMachine(HawkManager.machineBlockID, Material.iron);
	public static Block blockOre = new HawkBlockOre(HawkManager.oreID);
	public static Block blockMetalStorage = new HawkBlockMetalStorage(HawkManager.metalStorageID);
	
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Obsidian.
	 */
	public static Item dustRaw = new HawkItemRawDust(HawkManager.dustRawID);
	
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Emerald. 
	 */
	public static Item dustRefined = new HawkItemRefinedDust(HawkManager.dustRefinedID);
	public static Item ingots = new HawkItemIngots(HawkManager.ingotsID);
	
	private static String getName()
	{
		return "Hawk's Machinery";
	}
	
	private static String getVersion()
	{
		return "Alpha v1.2";
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
		
		UniversalElectricity.registerMod(this, getName(), "0.5.0");
		NetworkRegistry.instance().registerGuiHandler(this, this.proxy);
		
		proxy.preInit();
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.init();
		
		GameRegistry.registerBlock(blockGrinder);
		GameRegistry.registerBlock(blockEmptyMachine);
		GameRegistry.registerBlock(blockOre);
		GameRegistry.registerBlock(blockMetalStorage);
		
		ModLoader.registerTileEntity(HawkTileEntityGrinder.class, "Grinder");
		
		OreDictionary.registerOre("ingotTitanium", new ItemStack(ingots, 1, 0));
		OreDictionary.registerOre("ingotAluminum", new ItemStack(ingots, 1, 1));
		OreDictionary.registerOre("ingotSilver", new ItemStack(ingots, 1, 2));
		
		HawkManager.loadRecipes();
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		
	}
}
