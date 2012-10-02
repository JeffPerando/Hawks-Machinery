
package hawksmachinery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
	private static Map washerSecondaries = new HashMap();
	private static Map washerRarities = new HashMap();
	
	/**
	 * 
	 * Instead of IDs, processing now uses enums. *NEW* Now found within HawkProcessingRecipes.java!
	 * 
	 * @author Elusivehawk
	 */
	public enum HawkEnumProcessing
	{
		CRUSHING(new HashMap()),
		
		CRUSHING_EXPLOSIVES(new HashMap()),
		
		WASHING(new HashMap()),
		
		SINTERER(new HashMap()),
		
		HM_E2MM(new HashMap());
		
		private Map processingList;
		
		HawkEnumProcessing(Map recipeList)
		{
			this.processingList = recipeList;
			
		}
		
		public Map getRecipeList()
		{
			return this.processingList;
		}
		
	}
	
	/**
	 * 
	 * Adds a processing recipe.
	 * 
	 * @param input The input.
	 * @param output The output.
	 * @param processType What type of processing this recipe is for.
	 */
	public static void addHawkProcessingRecipe(ItemStack input, ItemStack output, HawkEnumProcessing processType)
	{
		if (input != null && output != null && processType != null)
		{
			processType.getRecipeList().put(Arrays.asList(input.getItem(), input.getItemDamage(), input.isItemEnchanted(), input.stackTagCompound != null), output);
		}
		else
		{
			if (processType == null)
			{
				throw new NullPointerException("Hawk's Machinery: Process type cannot be null!");
			}
			
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
	
	public static void addHawkProcessingRecipe(Item input, ItemStack output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input), output, processType);
	}
	
	public static void addHawkProcessingRecipe(Item input, Item output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input), new ItemStack(output), processType);
	}
	
	public static void addHawkProcessingRecipe(Item input, Block output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input), new ItemStack(output), processType);
	}
	
	public static void addHawkProcessingRecipe(Block input, ItemStack output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input), output, processType);
	}
	
	public static void addHawkProcessingRecipe(Block input, Item output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input), new ItemStack(output), processType);
	}
	
	public static void addHawkProcessingRecipe(Block input, Block output, HawkEnumProcessing processType)
	{
		addHawkProcessingRecipe(new ItemStack(input), new ItemStack(output), processType);
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
				addHawkProcessingRecipe(ore, output, processType);
			}
		}
	}
	
	public static void addHawkWashingSecondary(ItemStack input, Object output, boolean isCommon)
	{
		if (input != null && output != null)
		{
			if (isCommon)
			{
				washerSecondaries.put(Arrays.asList(input.getItem(), input.getItemDamage()), output);
			}
			else
			{
				washerRarities.put(Arrays.asList(input.getItem(), input.getItemDamage()), output);
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
	
	public static ItemStack getResult(ItemStack item, HawkEnumProcessing processType)
	{
		if (item == null)
		{
			return null;
		}
		else
		{
			ItemStack ret = (ItemStack)processType.getRecipeList().get(Arrays.asList(item.getItem(), item.getItemDamage(), item.isItemEnchanted(), item.stackTagCompound != null));
			
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
				ItemStack ret = (ItemStack)washerRarities.get(Arrays.asList(item.getItem(), item.getItemDamage()));
				
				if (ret != null) 
				{
					return ret;
				}
			}
			else if (secondaryChance < 10)
			{
				ItemStack ret = (ItemStack)washerSecondaries.get(Arrays.asList(item.getItem(), item.getItemDamage()));
				
				if (ret != null) 
				{
					return ret;
				}
			}
			
			return null;
		}
		
	}
	
}
