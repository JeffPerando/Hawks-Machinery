
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
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkProcessingRecipes
{
	private static Map grinderRecipes = new HashMap();
	private static Map grinderExplosives = new HashMap();
	
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
	public static void addHawkProcessingRecipe(ItemStack input, ItemStack output, HawkEnumProcessing processType)
	{
		if (input != null && output != null && processType != null)
		{
			processType.getRecipeList().put(Arrays.asList(input.getItem(), 1, input.getItemDamage()), output);
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
			
			if (processType == null)
			{
				throw new NullPointerException("Hawk's Machinery: Process type cannot be null!");
			}
			
		}
	}
	
	public static void addHawkProcessingRecipe(Item input, ItemStack output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), output, processType);
	}
	
	public static void addHawkProcessingRecipe(Item input, Item output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), new ItemStack(output, 1, 0), processType);
	}
	
	public static void addHawkProcessingRecipe(Item input, Block output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), new ItemStack(output, 1, 0), processType);
	}
	
	public static void addHawkProcessingRecipe(Block input, ItemStack output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), output, processType);
	}
	
	public static void addHawkProcessingRecipe(Block input, Item output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), new ItemStack(output, 1, 0), processType);
	}
	
	public static void addHawkProcessingRecipe(Block input, Block output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input, 1, 0), new ItemStack(output, 1, 0), processType);
	}
	
	/**
	 * 
	 * Forge Ore Dictionary supported version.
	 * 
	 * @param input
	 * @param output
	 * @param processingType
	 */
	public static void addHawkFoDProcessingRecipe(String input, ItemStack output, HawkEnumProcessing processType)
	{
		for (ItemStack ore : OreDictionary.getOres(input))
		{
			if (ore != null)
			{
				processType.getRecipeList().put(Arrays.asList(ore.getItem(), 1, ore.getItemDamage()), output);
			}
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
	
	public static ItemStack getProcessingResult(ItemStack item, HawkEnumProcessing processType)
	{
		if (item == null)
		{
			return null;
		}
		else
		{
			ItemStack ret = (ItemStack)processType.getRecipeList().get(Arrays.asList(item.getItem(), 1, item.getItemDamage()));
			
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
	
}
