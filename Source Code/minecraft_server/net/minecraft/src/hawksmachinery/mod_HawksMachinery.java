
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.*;
import net.minecraft.src.basiccomponents.BasicComponents;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.UniversalElectricity;
import net.minecraft.src.universalelectricity.network.PacketManager;
import net.minecraft.src.universalelectricity.ore.OreData;
import net.minecraft.src.universalelectricity.ore.UEOreManager;
import net.minecraft.src.universalelectricity.recipe.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class mod_HawksMachinery extends NetworkMod implements IRecipeReplacementHandler
{
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
	
	public static final PacketManager packetManager = new PacketManager("mod_HawksMachinery"); 
	public static mod_HawksMachinery instance;
	
	@Override
	public void load()
	{
		instance = this;
		UniversalElectricity.registerAddon(this, "0.4.6");
		HawkManager.loadRecipes();
		
		ModLoader.registerTileEntity(HawkTileEntityGrinder.class, "Grinder");
		MinecraftForge.setGuiHandler(this, new HawkManager());
		HawkAchievements.achievementStuff();
	}
	
	@Override
	public void modsLoaded()
	{
		HawkProcessingRecipes.reportRecipes();
	}
	
	@Override
	public String getVersion()
	{
		return "Alpha v1.1d";
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
    public UERecipe onReplaceShapedRecipe(UERecipe recipe)
    {
	    return null;
    }

	@Override
    public UERecipe onReplaceShapelessRecipe(UERecipe recipe)
    {
	    return null;
    }

	@Override
    public UEFurnaceRecipe onReplaceSmeltingRecipe(UEFurnaceRecipe recipe)
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
		if (id == HawkManager.dustRawID && metadata == 0)
		{
			return 1600;
		}
		
		if (id == Item.blazePowder.shiftedIndex)
		{
			return 1200;
		}
		
		if (id == Item.reed.shiftedIndex && !ModLoader.isModLoaded("mod_IC2"))
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
	
	@Override
	public String getPriorities()
	{
		return "require-after:mod_UnversalElectricity;require-after:mod_BasicComponents";
	}
	
}
