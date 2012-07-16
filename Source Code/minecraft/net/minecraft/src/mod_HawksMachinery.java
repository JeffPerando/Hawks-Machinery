
package net.minecraft.src;

import hawksmachinery.*;
import net.minecraft.src.basiccomponents.BasicComponents;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.UniversalElectricity;
import net.minecraft.src.universalelectricity.network.PacketManager;
import net.minecraft.src.universalelectricity.recipe.*;

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

	public static final PacketManager packetManager = new PacketManager("mod_HawksMachinery"); 
	
	public static mod_HawksMachinery instance;

	@Override
	public void load()
	{
		instance = this;
		
		UniversalElectricity.registerAddon(this, "0.4.5");
		
		HawkManager.loadRecipes();
		
		preloadHawksTextures();

		ModLoader.registerTileEntity(HawkTileEntityGrinder.class, "Grinder");
		
		MinecraftForge.setGuiHandler(this, this);
	}

	@Override
	public String getVersion()
	{
		return "Alpha v1.0a";
	}
	
	public String getName()
	{
		return "Hawk's Machinery";
	}
	
	private String clientOrServer()
	{
		return "Client";
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
	
	@Override
	public boolean clientSideRequired()
	{
		return true;
	}
	
	@Override
	public boolean serverSideRequired()
	{
		return false;
	}
}
