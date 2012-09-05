
package hawksmachinery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


/**
 * @author Elusivehawk
 *
 * @notice PROCESSING TYPES:
 * 1 - GRINDING
 * 2 - WASHING
 */
public class HawkProcessingRecipes
{
	private static Map grinderRecipes = new HashMap();
	private static Map grinderExplosives = new HashMap();
	
	private static Map washerRecipes = new HashMap();
	private static Map washerSecondaries = new HashMap();
	private static Map washerRarities = new HashMap();
	
	/**
	 * Adds a processing recipe.
	 * 
	 * Args:
	 * 
	 * Input
	 * Output
	 * Processing Type
	 */
	public static void addHawkProcessingRecipe(ItemStack input, ItemStack output, int processingType)
	{
		if (input != null && output != null)
		{
			switch (processingType)
			{
				case 1: grinderRecipes.put(Arrays.asList(input.getItem(), 1, input.getItemDamage()), output);
				case 2: washerRecipes.put(Arrays.asList(input.getItem(), 1, input.getItemDamage()), output);
			}
		}
		else
		{
			if (input == null)
			{
				throw new NullPointerException("Hawk's Machinery: Input cannot be null!");
			}
			
			if (output == null)
			{
				throw new NullPointerException("Hawk's Machinery: Output cannot be null!");
			}
		}
	}
	
	public static void addHawkProcessingRecipe(Item input, ItemStack output, int processingType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), output, processingType);
	}
	
	public static void addHawkProcessingRecipe(Item input, Item output, int processingType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), new ItemStack(output, 1, 0), processingType);
	}
	
	public static void addHawkProcessingRecipe(Item input, Block output, int processingType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), new ItemStack(output, 1, 0), processingType);
	}
	
	public static void addHawkProcessingRecipe(Block input, ItemStack output, int processingType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), output, processingType);
	}
	
	public static void addHawkProcessingRecipe(Block input, Item output, int processingType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), new ItemStack(output, 1, 0), processingType);
	}
	
	public static void addHawkProcessingRecipe(Block input, Block output, int processingType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), new ItemStack(output, 1, 0), processingType);
	}
	
	/**
	 * 
	 * Forge Ore Dictionary supported version.
	 * 
	 * @param input
	 * @param output
	 * @param processingType
	 */
	public static void addHawkFoDProcessingRecipe(String input, ItemStack output, int processingType)
	{
		for (ItemStack ore : OreDictionary.getOres(input))
		{
			if (ore != null)
			{
				switch (processingType)
				{
					case 1: grinderRecipes.put(Arrays.asList(ore.getItem(), 1, ore.getItemDamage()), output);
					case 2: washerRecipes.put(Arrays.asList(ore.getItem(), 1, ore.getItemDamage()), output);
				}
			}
		}
	}
	
	/**
	 * Adds an explosive item that explodes a machine when processed in a certain way.
	 * 
	 * @param explosive The item/block that explodes once processed.
	 * @param processingType Determines what machine explodes when processing said item/block.
	 */
	public static void addHawkExplosive(ItemStack input, int processingType)
	{
		if (input != null)
		{
			switch (processingType)
			{
				case 1: grinderExplosives.put(Arrays.asList(input.getItem(), 1, input.getItemDamage()), new Object[]{});
			}
		}
		else
		{
			throw new NullPointerException("Hawl's Machinery: Explosive input cannot be null!");
		}
	}
	
	public static void addHawkWashingSecondary(ItemStack input, Object output, boolean isCommon)
	{
		if (input != null && output != null)
		{
			if (isCommon)
			{
				washerSecondaries.put(Arrays.asList(input.getItem(), 1, input.getItemDamage()), output);
			}
			else
			{
				washerRarities.put(Arrays.asList(input.getItem(), 1, input.getItemDamage()), output);
			}
		}
		else
		{
			if (input == null)
			{
				throw new NullPointerException("Hawk's Machinery: Input cannot be null!");
			}
			
			if (output == null)
			{
				throw new NullPointerException("Hawk's Machinery: Output cannot be null!");
			}
		}
		
	}
	
	public static Map getGrindingList()
	{
		return grinderRecipes;
	}
	
	public static Map getGrindingExplosivesList()
	{
		return grinderExplosives;
	}
	
	public static void reportRecipes()
	{
		System.out.println(getGrindingList().size() + " Hawk's Machinery recipes");
		System.out.println(getGrindingExplosivesList().size() + " Hawk's Machinery explosives");
		
	}
	
	public static ItemStack getGrindingResult(ItemStack item)
	{
		if (item == null)
		{
			return null;
		}
		else
		{
			ItemStack ret = (ItemStack)grinderRecipes.get(Arrays.asList(item.getItem(), 1, item.getItemDamage()));
			
			return ret;
		}
	}
	
	public static ItemStack getWashingResult(ItemStack item)
	{
		if (item == null)
		{
			return null;
		}
		else
		{
			ItemStack ret = (ItemStack)washerRecipes.get(Arrays.asList(item.getItem(), 1, item.getItemDamage()));
			
			if (ret != null) 
			{
				return ret;
			}
			
			return null;
		}
	}
	
	public static ItemStack getWashingSecondaryResult(ItemStack item, Random random)
	{
		int secondaryChance = random.nextInt(100);
		
		if (item == null)
		{
			return null;
		}
		else
		{
			if (secondaryChance < 5)
			{
				ItemStack ret = (ItemStack)washerRarities.get(Arrays.asList(item.getItem(), 1, item.getItemDamage()));
				
				if (ret != null) 
				{
					return ret;
				}
			}
			else if (secondaryChance < 10)
			{
				ItemStack ret = (ItemStack)washerSecondaries.get(Arrays.asList(item.getItem(), 1, item.getItemDamage()));
				
				if (ret != null) 
				{
					return ret;
				}
			}
			
			return null;
		}
	}
	
	public static ItemStack getGrindingExplosive(ItemStack item)
	{
		if (item == null)
		{
			return null;
		}
		else
		{
			ItemStack ret = (ItemStack)grinderExplosives.get(Arrays.asList(item.getItem(), 1, item.getItemDamage()));
			
			if (ret != null) 
			{
				return ret;
			}
			
			return null;
		}
	}
	
}
