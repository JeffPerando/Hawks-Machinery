
package net.minecraft.src.hawksmachinery.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.src.ItemStack;

/**
 * Handles all API operations; Part of the API redistributable; DO NOT MODIFY!
 * 
 * @author Elusivehawk
 *
 * @version 1.0
 * 
 * @howtouse Just call addHawkProcessingRecipe or addHawkMetaProcessingRecipe.
 * 
 */
public class HMAPICore
{
	private static Map grinderRecipesAPI = new HashMap();
	private static Map grinderExplosivesAPI = new HashMap();
	
	/**
	 * Adds a processing recipe.
	 * 
	 * Args:
	 * 
	 * Input,
	 * Output,
	 * Processing Type.
	 */
    public void addHawkProcessingRecipe(int input, ItemStack output, int processingType)
    {
    	switch(processingType)
    	{
    		case 1: grinderRecipesAPI.put(Arrays.asList(input, 0), output);
    	}
    }
    
    /**
     * Metadata-sensitive processing function.
     */
	public void addHawkMetaProcessingRecipe(int input, int inputMetadata, ItemStack output, int processingType)
	{
		switch(processingType)
		{
			case 1: grinderRecipesAPI.put(Arrays.asList(input, inputMetadata), output);
		}
	}
	
	public static void addHawkExplosive(int explosive, int processingType)
	{
		switch (processingType)
		{
			case 1: grinderExplosivesAPI.putAll((Map) Arrays.asList(explosive, 0));
		}
	}
	
	public static void addHawkMetaExplosive(int explosive, int explosiveDmg, int processingType)
	{
		switch (processingType)
		{
			case 1: grinderExplosivesAPI.putAll((Map) Arrays.asList(explosive, explosiveDmg));
		}
	}
    
    public static Map getGrinderAPIRecipes()
    {
		return grinderRecipesAPI;	
    }
    
    public static Map getGrinderAPIExplosives()
    {
    	return grinderExplosivesAPI;
    }
}
