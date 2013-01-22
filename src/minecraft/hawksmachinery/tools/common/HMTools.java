
package hawksmachinery.tools.common;

import hawksmachinery.core.common.HMCore;
import hawksmachinery.tools.common.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
@Mod(modid = "HMTools", name = "Hawk's Tools", version = HMCore.VERSION, useMetadata = true, dependencies = "after:HMCore")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class HMTools
{
	@Instance("HMTools")
	private static HMTools INSTANCE;
	
	public static Item cobaltBone;
	
	public static Item enderMirrorPlayer, enderMirrorSpawn;
	
	public static Item electricSwordOff, electricSwordOn;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		cobaltBone = new HMItemWolfTamer(HMCore.PROXY.getItemID("Cobalt Bone", 24160)).setItemName("HMCobaltBone").setCreativeTab(HMCore.instance().tab).setIconIndex(105);
		enderMirrorPlayer = new HMItemEnderMirrorPlayer(HMCore.PROXY.getItemID("Ender Mirror Player", 24163));
		enderMirrorSpawn = new HMItemEnderMirrorSpawn(HMCore.PROXY.getItemID("Ender Mirror Spawn", 24164));
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		this.loadRecipes();
		
	}
	
	public void loadRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(cobaltBone), new Object[]{" C ", "CBC", " C ", 'C', "dustCobalt", 'B', Item.bone}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enderMirrorPlayer), new Object[]{"SCS", "CEC", "SCS", 'S', Item.silk, 'C', "ingotCobalt", 'E', Item.enderPearl}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(enderMirrorSpawn), new Object[]{"BCB", "CEC", "BCB", 'B', Block.bed, 'C', "ingotCobalt", 'E', Item.enderPearl}));
		
	}
	
}
