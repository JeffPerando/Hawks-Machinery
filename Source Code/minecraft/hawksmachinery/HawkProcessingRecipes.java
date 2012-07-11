
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
	 * Loads all of the recipes for Hawk's Machinery, including regular crafting recipes.
	 */
	public static void loadRecipes()
	{
		UERecipeManager.addRecipe(new ItemStack(mod_HawksMachinery.blockEmptyMachine, 1), new Object[]{"oxo", "xvx", "oxo", 'o', BasicComponents.ItemSteelIngot, 'x', BasicComponents.ItemSteelPlate, 'v', Item.blazePowder});
		UERecipeManager.addRecipe((new ItemStack(mod_HawksMachinery.blockProcessor, 1)), new Object[]{"xix", "xpx", "xex", 'x', BasicComponents.ItemSteelIngot, 'p', Item.pickaxeSteel, 'e', mod_HawksMachinery.blockEmptyMachine, 'i', new ItemStack(BasicComponents.ItemCircuit, 1, 1)});
		UERecipeManager.addRecipe((new ItemStack(BasicComponents.ItemSteelIngot, 24)), new Object[]{"e", 'e', mod_HawksMachinery.blockEmptyMachine});

		UERecipeManager.addShapelessRecipe((new ItemStack(BasicComponents.ItemSteelClump, 1)), new Object[]{Item.ingotIron, mod_HawksMachinery.coalDust});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.ironDust, 2)), new Object[]{Item.bucketWater, mod_HawksMachinery.ironDustUnref, mod_HawksMachinery.ironDustUnref});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.goldDust, 2)), new Object[]{Item.bucketWater, mod_HawksMachinery.goldDustUnref, mod_HawksMachinery.goldDustUnref});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.copperDust, 2)), new Object[]{Item.bucketWater, mod_HawksMachinery.copperDustUnref, mod_HawksMachinery.copperDustUnref});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.tinDust, 2)), new Object[]{Item.bucketWater, mod_HawksMachinery.tinDustUnref, mod_HawksMachinery.tinDustUnref});
		
		UERecipeManager.addSmelting(mod_HawksMachinery.glassDust, new ItemStack(Block.thinGlass));
		UERecipeManager.addSmelting(mod_HawksMachinery.ironDust, new ItemStack(Item.ingotIron));
		UERecipeManager.addSmelting(mod_HawksMachinery.goldDust, new ItemStack(Item.ingotGold));
		UERecipeManager.addSmelting(mod_HawksMachinery.copperDust, new ItemStack(BasicComponents.ItemCopperIngot));
		UERecipeManager.addSmelting(mod_HawksMachinery.tinDust, new ItemStack(BasicComponents.ItemTinIngot));
		
		addHawkProcessingRecipe(Item.coal.shiftedIndex, new ItemStack(mod_HawksMachinery.coalDust, 1), 1);
		addHawkProcessingRecipe(Item.diamond.shiftedIndex, new ItemStack(mod_HawksMachinery.diamondDust, 4), 1);
		addHawkProcessingRecipe(Block.oreGold.blockID, new ItemStack(mod_HawksMachinery.goldDustUnref, 2), 1);
		addHawkProcessingRecipe(Item.enderPearl.shiftedIndex, new ItemStack(mod_HawksMachinery.enderDust, 2), 1);
		addHawkProcessingRecipe(Block.glass.blockID, new ItemStack(mod_HawksMachinery.glassDust, 4), 1);
		addHawkProcessingRecipe(Block.oreIron.blockID, new ItemStack(mod_HawksMachinery.ironDustUnref, 2), 1);
		addHawkProcessingRecipe(BasicComponents.ItemCopperIngot.shiftedIndex, new ItemStack(mod_HawksMachinery.copperDust), 1);
		addHawkProcessingRecipe(BasicComponents.ItemTinIngot.shiftedIndex, new ItemStack(mod_HawksMachinery.tinDust), 1);
		
		addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.CopperOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.CopperOreID), new ItemStack(mod_HawksMachinery.copperDustUnref, 2), 1);
		addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.TinOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.TinOreID), new ItemStack(mod_HawksMachinery.tinDustUnref, 2), 1);
		
	}
	
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
	public static void addHawkMetaProcessingRecipe(int itemID, int metadata, ItemStack itemstack, int processingType)
	{
		switch(processingType)
		{
			case 1: grinderList.put(Arrays.asList(itemID, metadata), itemstack);
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
