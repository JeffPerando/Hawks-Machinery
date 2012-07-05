/**
 * 
 */
package net.minecraft.src;

import hawksmachinery.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.src.forge.*;
import net.minecraft.src.forge.oredict.*;
import net.minecraft.src.universalelectricity.*;
import net.minecraft.src.universalelectricity.components.*;

/**
 * @author Elusivehawk
 *
 */
public class mod_HawksMachinery extends NetworkMod implements IGuiHandler
{
	
	public static Block blockProcessor = (new HawkBlockProcessor("Grinder", IHawkMiscHandler.initProps(), Material.wood)).setHardness(0.5F).setResistance(1.0F);

	public static Item dust1 = (new HawkItem("Coal Dust", IHawkMiscHandler.dust1ID)).setIconCoord(0, 1);
	public static Item dust2 = (new HawkItem("Diamond Dust", IHawkMiscHandler.dust2ID)).setIconCoord(9, 1);
	public static Item dust3 = (new HawkItem("Gold Dust", IHawkMiscHandler.dust3ID)).setIconCoord(2, 1);
	public static Item dust4 = (new HawkItem("Ender Dust", IHawkMiscHandler.dust4ID)).setIconCoord(10, 1);
	public static Item dust5 = (new HawkItem("Glass Dust", IHawkMiscHandler.dust5ID)).setIconCoord(11, 1);
	
	public static mod_HawksMachinery instance;

	public void load()
	{
		instance = this;
		
		HawkProcessingRecipes.initHawksProcessingRecipes();
		checkRequiredModsExistence();
		preloadHawksTextures();
		
		ModLoader.registerBlock(blockProcessor);

		ModLoader.registerTileEntity(HawkTileEntityGrinder.class, "Grinder", new UEBlockRenderer());
		
		MinecraftForge.setGuiHandler(this, this);
	}

	public String getVersion()
	{
		return "v0.1, built with MC v1.2.5, Forge #152 and UE v3.1";
	}
	
	public String getName()
	{
		return "Hawk's Machinery";
	}
	
	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			return new HawkGUIGrinder(player.inventory, ((HawkTileEntityGrinder)tileEntity));
        }
		return null;
	}
	
	/*
	 * Checks for required mods, and certain mods that sure as hell aren't compatible.
	 */
	private static void checkRequiredModsExistence()
	{
		if (ModLoader.isModLoaded("mod_BTB") == true) //In case BTB loads before BTW.
		{
			throw new RuntimeException("Better Then BuildCraft doesn't supply the hooks Hawk's Machinery requires...");
		}
		
		if (ModLoader.isModLoaded("mod_FCBTW") == true) //Checks to see if some poor sap thought it would be intelligent to install this mod with Better Than Wolves.
		{
			throw new RuntimeException("...What are you thinking?");
		}
		
		if (ModLoader.isModLoaded("mod_MinecraftForge") == false) //Checks for Minecraft Forge's existence.
		{
			throw new RuntimeException("Hawk's Machinery: You forgot to install Minecraft Forge, which is for the record, REQUIRED.");
		}
		
		if (ModLoader.isModLoaded("mod_UniversalElectricity") == false) //Ditto as above, but with Universal Electricity.
		{
			MinecraftForge.killMinecraft("Hawk's Machinery", "You forgot to install Universal Electricity.");
		}
	}
	
	private static void preloadHawksTextures()
	{
		MinecraftForgeClient.preloadTexture("/hawksmachinery/blocks.png");
		MinecraftForgeClient.preloadTexture("/hawksmachinery/items.png");
	}
}
