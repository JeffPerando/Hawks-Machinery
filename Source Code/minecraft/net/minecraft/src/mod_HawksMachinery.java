
package net.minecraft.src;

import hawksmachinery.*;
import net.minecraft.src.basiccomponents.BasicComponents;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.UniversalElectricity;
import net.minecraft.src.universalelectricity.recipe.IRecipeReplacementHandler;
import net.minecraft.src.universalelectricity.recipe.UEFurnaceRecipe;
import net.minecraft.src.universalelectricity.recipe.UERecipe;
import net.minecraft.src.universalelectricity.recipe.UERecipeManager;

/**
 * @author Elusivehawk
 *
 */
public class mod_HawksMachinery extends NetworkMod implements IGuiHandler, IRecipeReplacementHandler
{
	public static Block blockProcessor = new HawkBlockGrinder("Grinder", HawkManager.initProps(), Material.wood);
	public static Block blockEmptyMachine = new HawkBlock("Empty Machine Block", HawkManager.machineBlockID, Material.iron, 1, 1, 1, 1, 1, 1).setHardness(1.0F).setResistance(1.0F);

	public static Item coalDust = (new HawkItem("Coal Dust", HawkManager.dust1ID)).setIconCoord(1, 0);
	public static Item diamondDust = (new HawkItem("Diamond Dust", HawkManager.dust2ID)).setIconCoord(10, 0);
	public static Item goldDustUnref = (new HawkItem("Unrefined Gold Dust", HawkManager.dust3ID)).setIconCoord(10, 2);
	public static Item enderDust = (new HawkItem("Ender Dust", HawkManager.dust4ID)).setIconCoord(11, 0);
	public static Item glassDust = (new HawkItem("Glass Dust", HawkManager.dust5ID)).setIconCoord(12, 0);
	public static Item ironDustUnref = (new HawkItem("Unrefined Iron Dust", HawkManager.dust6ID)).setIconCoord(2, 1);
	public static Item copperDustUnref = (new HawkItem("Unrefined Copper Dust", HawkManager.dust7ID)).setIconCoord(12, 2);
	public static Item tinDustUnref = (new HawkItem("Unrefined Tin Dust", HawkManager.dust8ID)).setIconCoord(11, 2);
	public static Item ironDust = (new HawkItem("Iron Dust", HawkManager.dust9ID)).setIconCoord(2, 0);
	public static Item goldDust = (new HawkItem("Gold Dust", HawkManager.dust10ID)).setIconCoord(10, 1);
	public static Item copperDust = (new HawkItem("Copper Dust", HawkManager.dust11ID)).setIconCoord(12, 1);
	public static Item tinDust = (new HawkItem("Tin Dust", HawkManager.dust12ID)).setIconCoord(11, 1);

	
	public static mod_HawksMachinery instance;

	@Override
	public void load()
	{
		instance = this;
		
		UniversalElectricity.registerAddon(this, "0.4.2");
		
		HawkProcessingRecipes.loadRecipes();
		
		checkRequiredModsExistence();
		
		preloadHawksTextures();

		ModLoader.registerTileEntity(HawkTileEntityGrinder.class, "Grinder");
		
		MinecraftForge.setGuiHandler(this, this);
	}

	@Override
	public String getVersion()
	{
		return "Alpha v0.2";
	}
	
	public String getName()
	{
		return "Hawk's Machinery";
	}
	
	@Override
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
		MinecraftForgeClient.preloadTexture(HawkManager.blockTextureFile);
		MinecraftForgeClient.preloadTexture(HawkManager.itemTextureFile);
	}

	@Override
    public Object[] onReplaceShapedRecipe(UERecipe recipe)
    {
	    return null;
    }

	@Override
    public Object[] onReplaceShapelessRecipe(UERecipe recipe)
    {
	    return null;
    }

	@Override
    public ItemStack onReplaceSmeltingRecipe(UEFurnaceRecipe recipe)
    {
	    return null;
    }
}
