
package hawksmachinery.redstone.common;

import hawksmachinery.core.common.HMCore;
import hawksmachinery.redstone.common.block.HMBlockTileSensor;
import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@Mod(modid = "HMRedstone", name = "Hawk's Redstone", version = HMCore.VERSION, useMetadata = true, dependencies = "after:HMCore")
@NetworkMod(channels = {"HMRedstone"}, clientSideRequired = true, serverSideRequired = false)
public class HMRedstone
{
	@Instance("HMRedstone")
	private static HMRedstone INSTANCE;
	
	public static Block blockDetector;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		blockDetector = new HMBlockTileSensor(HMCore.instance().PROXY.getBlockID("Block Detector", 2560));
		
		GameRegistry.registerBlock(blockDetector);
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		
		
	}
	
}
