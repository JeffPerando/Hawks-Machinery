
package hawksmachinery;

import java.util.*;
import net.minecraft.src.*;
import net.minecraft.src.basiccomponents.BasicComponents;
import net.minecraft.src.forge.*;
import net.minecraft.src.forge.oredict.OreDictionary;
import net.minecraft.src.universalelectricity.ore.UEOreManager;
import net.minecraft.src.universalelectricity.recipe.UERecipeManager;

/**
 * @author Elusivehawk
 *
 * @notice PROCESSING TYPES:
 * 1 - GRINDING
 */
public class HawkProcessingRecipes
{
	private static Map grinderList = new HashMap();
	
	/**
	 * Adds a processing method.
	 * 
	 * Args:
	 * 
	 * Input
	 * Output
	 * Processing Type
	 */
    public static void addHawkProcessingRecipe(int input, ItemStack output, int processingType)
    {
    	switch(processingType)
    	{
    		case 1: grinderList.put(Arrays.asList(input, 0), output);
    	}
    }
    
    /**
     * Metadata-sensitive processing function.
     */
	public static void addHawkMetaProcessingRecipe(int itemID, int inputMetadata, ItemStack output, int processingType)
	{
		switch(processingType)
		{
			case 1: grinderList.put(Arrays.asList(itemID, inputMetadata), output);
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
