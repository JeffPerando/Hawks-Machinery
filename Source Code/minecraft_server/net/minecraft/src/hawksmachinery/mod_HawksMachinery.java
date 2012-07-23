
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.*;
import net.minecraft.server.MinecraftServer;
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
	public static Block blockGrinder = new HawkBlockGrinder("Grinder", HawkManager.initProps(), Material.wood);
	public static Block blockEmptyMachine = new HawkBlock("Empty Machine Block", HawkManager.machineBlockID, Material.iron, 1, 1, 1, 1, 1, 1);

	/**
	 * Dusts in metadata form, finally! From 0 to 13: Coal, Diamond, Unref Gold, Ender, Glass, Unref Iron, Unref Copper, Unref Tin, Iron, Gold, Copper, Tin, Obsidian, Emerald.
	 */
	public static Item dust = (new HawkItemDust(HawkManager.dustID));
	

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
		return "Alpha v1.1";
	}
	
	public String getName()
	{
		return "Hawk's Machinery";
	}
	
	private String clientOrServer()
	{
		return "Server";
	}
	
	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			return new HawkContainerGrinder(player.inventory, ((HawkTileEntityGrinder)tileEntity));
        }
		return null;
	}
	
	/**
	 * The server apparently doesn't load textures.
	 */
	@Deprecated
	private static void preloadHawksTextures()
	{
		//MinecraftForgeClient.preloadTexture(HawkManager.blockTextureFile);
		//MinecraftForgeClient.preloadTexture(HawkManager.itemTextureFile);
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
	
	@Override
    public int addFuel(int id, int metadata)
    {
		if (id == HawkManager.dustID && metadata == 0)
		{
			return 1600;
		}
		
		if (id == Item.blazePowder.shiftedIndex)
		{
			return 1200;
		}
		
		if (id == Item.reed.shiftedIndex)
		{
			return 50;
		}
		
		if (id == Item.wheat.shiftedIndex)
		{
			return 300;
		}
		
		if (id == Item.bow.shiftedIndex)
		{
			return 300;
		}
		
		if (id == Item.fishingRod.shiftedIndex)
		{
			return 300;
		}
		
		if (id == Item.bowlEmpty.shiftedIndex)
		{
			return 200;
		}
		
		if (id == Item.boat.shiftedIndex)
		{
			return 1500;
		}
		
		if (id == Item.paper.shiftedIndex)
		{
			return 50;
		}
		
		if (id == Item.map.shiftedIndex)
		{
			return 400;
		}
		
		if (id == Item.sign.shiftedIndex)
		{
			return 1900;
		}
		
		if (id == Item.doorWood.shiftedIndex)
		{
			return 1800;
		}
		
		if (id == Block.chest.blockID)
		{
			return 2400;
		}
		
		return 0;
    }
	
}
