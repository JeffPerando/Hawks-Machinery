
package net.minecraft.src.hawksmachinery;

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
	private static Map grinderRecipes = new HashMap();
	private static Map grinderRecipesAPI = new HashMap();
	private static Map grinderExplosives = new HashMap();
	private static Map grinderExplosivesAPI = new HashMap();
	
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
    		case 1: grinderRecipes.put(Arrays.asList(input, 0), output);
    	}
    }
    
    /**
     * Metadata-sensitive processing function.
     */
	public static void addHawkMetaProcessingRecipe(int itemID, int inputMetadata, ItemStack output, int processingType)
	{
		switch(processingType)
		{
			case 1: grinderRecipes.put(Arrays.asList(itemID, inputMetadata), output);
		}
	}
	
	/**
	 * Adds an explosive item that explodes a machine when processed in a certain way.
	 * 
	 * @param explosive The item/block that explodes once processed.
	 * @param processingType Determines what machine explodes when processing said item/block.
	 */
	public static void addHawkExplosive(int explosive, int processingType)
	{
		switch (processingType)
		{
			case 1: grinderExplosives.putAll((Map) Arrays.asList(explosive, 0));
		}
	}
	/**
	 * Same as above, but metadata sensitive in order to facilitate ICBM support.
	 * 
	 * @param explosive
	 * @param explosiveDmg
	 * @param processingType
	 */
	public static void addHawkMetaExplosive(int explosive, int explosiveDmg, int processingType)
	{
		switch (processingType)
		{
			case 1: grinderExplosives.putAll((Map) Arrays.asList(explosive, explosiveDmg));
		}
	}
	
	public static Map getGrindingList()
	{
		return grinderRecipes;
	}
	
	public static Map getGrindingAPIList()
	{
		return grinderRecipesAPI;
	}
	
	public static Map getGrindingExplosivesList()
	{
		return grinderExplosives;
	}
	
	public static Map getGrindingExplosivesAPIList()
	{
		return grinderExplosivesAPI;
	}
	
	public static ItemStack getGrindingResult(ItemStack item)
	{
        if (item == null)
        {
            return null;
        }
        else
        {
            ItemStack ret = (ItemStack)grinderRecipes.get(Arrays.asList(item.itemID, item.getItemDamage()));
            
            if (ret != null) 
            {
                return ret;
            }
            
            return (ItemStack)grinderRecipes.get(Integer.valueOf(item.itemID));
        }
	}
	
	public static boolean getGrindingExplosive(ItemStack item)
	{
        if (item == null)
        {
            return false;
        }
        else
        {
            ItemStack ret = (ItemStack)grinderExplosives.get(Arrays.asList(item.itemID, item.getItemDamage()));
            ItemStack ret2 = (ItemStack)grinderExplosivesAPI.get(Arrays.asList(item.itemID, item.getItemDamage()));
            
            if (ret != null) 
            {
                return true;
            }
            else
            {
            	if (ret2 != null)
            	{
            		return true;
            	}
            	return false;
            }
        }
	}
}
