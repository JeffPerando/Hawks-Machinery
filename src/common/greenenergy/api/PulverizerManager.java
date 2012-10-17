package greenenergy.api;

import greenenergy.GreenTechnology;
import greenenergy.biomass.TilePulverizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PulverizerManager
{	
	public static HashMap<List, List> recipes = new HashMap();
	/**
	 * 
	 * Adds a processing recipe.
	 * 
	 * @param input The input.
	 * @param data The output itemStack.
	 */
	public static void addRecipe(ItemStack input, ItemStack data)
	{
		if(input == null || data == null){
			throw new NullPointerException("Input/Data cannot be null, you idiot!");
		}
		recipes.put(Arrays.asList(input.getItem(), input.stackSize, input.getItemDamage(), input.isItemEnchanted(), input.stackTagCompound != null), Arrays.asList(data.getItem(), data.stackSize, data.getItemDamage(), data.isItemEnchanted(), data.stackTagCompound != null));
	}
	public static void addRecipe(ItemStack input){
		addRecipe(input, new ItemStack(GreenTechnology.bioPellet, 1, 0));
	}
	public static void addRecipe(ItemStack input, int timeLasting){
		addRecipe(input, new ItemStack(GreenTechnology.bioPellet, amountOfBiomass(timeLasting), 0));
	}
	/**
	 * @param thing The thing you want to see if it has a recipe
	 * @param type The type of recipe you wanna check for
	 * @return If biomass recipe: the biovalue. If custom recipe: the output. If unknown, an object associated with the recipe.
	 */
	public static List getResult(ItemStack input)
	{
		if (input == null || !recipes.containsKey(Arrays.asList(input.getItem(), input.stackSize, input.getItemDamage(), input.isItemEnchanted(), input.stackTagCompound != null)))
		{
			return null;
		}
		else
		{
			return recipes.get(Arrays.asList(input.getItem(),input.stackSize, input.getItemDamage(), input.isItemEnchanted(), input.stackTagCompound != null));	
		}
	}
	public static ItemStack getOutput(ItemStack input){
		if(isRecipe(input)){
			List l = getResult(input);
			ItemStack i = new ItemStack((Item)l.get(0), (Integer) l.get(1));
			i.setItemDamage((Integer) l.get(1));
			return i;
		}
		return null;
	}
	/**
	 * Is this a recipe?
	 * @param input ItemStack you want to check
	 * @return True if it is, false if not.
	 */
	public static boolean isRecipe(ItemStack input){
		return recipes.containsKey(Arrays.asList(input.getItem(), input.stackSize, input.getItemDamage(), input.isItemEnchanted(), input.stackTagCompound != null));
	}
	/**
	 * Utility Function
	 * @param itemLastingTime How long you want 1 of this item to last in a pulverizer. In ticks.
	 * @return How many you need to get 1 biomass clump.
	 */
	public static int amountOfBiomass(double itemLastingTime){
		return (int) Math.ceil(TilePulverizer.pulverizingTimeRequired/itemLastingTime);
	}
	
}
