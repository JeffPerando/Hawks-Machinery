
package hawksmachinery.tools.common;

import hawksmachinery.core.common.HMCore;
import hawksmachinery.tools.common.item.HMItemWolfTamer;
import net.minecraft.item.Item;
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
@Mod(modid = "HMTools", name = "Hawk's Tools", version = HMCore.VERSION, useMetadata = true, dependencies = "after:HMCore")
@NetworkMod(channels = {"HMTools"}, clientSideRequired = true, serverSideRequired = false)
public class HMTools
{
	@Instance("HMTools")
	private static HMTools INSTANCE;
	
	public static Item cobaltBone;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		cobaltBone = new HMItemWolfTamer(HMCore.instance().MANAGER.getItemID("Cobalt Bone", 24160)).setItemName("HMCobaltBone").setCreativeTab(HMCore.instance().tab).setIconIndex(105);
		
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
