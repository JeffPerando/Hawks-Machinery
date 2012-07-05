
package net.minecraft.src;

import hawksmachinery.*;
/**
 * @author Elusivehawk
 * 
 * @info This is just to handle processor recipes without having to import hawksmachinery.
 */
public class IHawkExternalRecipeHandler
{
	public static void addExternalProcessingRecipe(int input, ItemStack itemstack, int processingType)
	{
		HawkProcessingRecipes.addProcessingRecipe(input, itemstack, processingType);
	}
}
