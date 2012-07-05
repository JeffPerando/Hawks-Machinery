/**
 * 
 */
package hawksmachinery;

import java.util.*;
import net.minecraft.src.*;
import net.minecraft.src.forge.*;

/**
 * @author Elusivehawk
 *
 */
public class HawkProcessingRecipes
{
	private static Map grinderList = new HashMap();
	private static Map metaGrinderList = new HashMap();
	private static boolean isActivated = false;

	public static void initHawksProcessingRecipes()
	{
		isActivated = true;
	}
	
	HawkProcessingRecipes()
	{
		this.addProcessingRecipe(Block.dirt.blockID, new ItemStack(Item.diamond, 1), 1);
	}
	
	
	/*
	 * Adds a processing method.
	 */
    public static void addProcessingRecipe(int input, ItemStack itemstack, int processingType)
    {
    	switch (processingType)
    	{
    		case 1: grinderList.put(Integer.valueOf(input), itemstack);
    	}
    }
    
    
    /*
     * Metadata-sensitive processing function.
     */
	public void addMetaProcessingRecipe(int itemID, int metadata, ItemStack itemstack, int processingType)
	{
		if (processingType <= 16)
		{
			switch(processingType)
			{
				case 1: this.metaGrinderList.put(Arrays.asList(itemID, metadata), itemstack);
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
	
	public static Map getMetaGrindingList()
	{
		return metaGrinderList;
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
