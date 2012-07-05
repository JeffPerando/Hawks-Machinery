/**
 * 
 */
package hawksmachinery;

import java.util.*;
import net.minecraft.src.*;
import net.minecraft.src.basiccomponents.BasicComponents;
import net.minecraft.src.forge.*;
import net.minecraft.src.forge.oredict.OreDictionary;

/**
 * @author Elusivehawk
 *
 * @notice PROCESSING TYPES:
 * 1 - GRINDING
 */
public class HawkProcessingRecipes
{
	private static Map grinderList = new HashMap();
	
	public static void loadRecipes()
	{
		ModLoader.addSmelting(mod_HawksMachinery.glassDust.shiftedIndex, new ItemStack(Block.thinGlass));
		
		addProcessingRecipe(Item.coal.shiftedIndex, new ItemStack(mod_HawksMachinery.coalDust, 1), 1);
		addProcessingRecipe(Item.diamond.shiftedIndex, new ItemStack(mod_HawksMachinery.diamondDust, 4), 1);
		addProcessingRecipe(Block.oreGold.blockID, new ItemStack(mod_HawksMachinery.goldDustUnref, 2), 1);
		addProcessingRecipe(Item.enderPearl.shiftedIndex, new ItemStack(mod_HawksMachinery.enderDust, 2), 1);
		addProcessingRecipe(Block.glass.blockID, new ItemStack(mod_HawksMachinery.glassDust, 4), 1);
		addProcessingRecipe(Block.oreIron.blockID, new ItemStack(mod_HawksMachinery.ironDustUnref, 2), 1);
	
	}
	
	/*
	 * Adds a processing method.
	 * 
	 * Args:
	 * 
	 * Input
	 * Output
	 * Processing Type
	 */
    public static void addProcessingRecipe(int input, ItemStack output, int processingType)
    {
    	switch(processingType)
    	{
    		case 1: grinderList.put(Arrays.asList(input, 0), output);
    	}
    }
    
    /*
     * Metadata-sensitive processing function.
     */
	public void addProcessingRecipe(int itemID, int metadata, ItemStack itemstack, int processingType)
	{
		if(processingType <= 16)
		{
			switch(processingType)
			{
				case 1: this.grinderList.put(Arrays.asList(itemID, metadata), itemstack);
			}
		}
		else
		{
			MinecraftForge.killMinecraft("Hawk's Machinery", "Invalid processing type: " + processingType);
		}
	}
	
	public static Map getGrindingList()
	{
		return grinderList;
	}
	
	public static ItemStack getGrindingResult(ItemStack item)
	{
        if (item == null)
        {
            return null;
        }
        else
        {
            ItemStack ret = (ItemStack)grinderList.get(Arrays.asList(item.itemID, item.getItemDamage()));
            
            if (ret != null) 
            {
                return ret;
            }
            
            return (ItemStack)grinderList.get(Integer.valueOf(item.itemID));
        }
	}
}
