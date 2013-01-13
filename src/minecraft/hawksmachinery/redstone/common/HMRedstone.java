
package hawksmachinery.redstone.common;

import hawksmachinery.core.common.HMCore;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

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
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
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
