
package hawksmachinery.common.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemStackHelper
{
	private static HMItemStackHelper instance = new HMItemStackHelper();
	
	public ItemStack addEnchantments(ItemStack item, Enchantment[] enchants, int[] levels)
	{
		for (int counter = 0; counter < enchants.length; ++counter)
		{
			item.addEnchantment(enchants[counter], levels[counter]);
			
		}
		
		return item;
	}
	
	public ItemStack tagItem(ItemStack item, String[] tagNames, Object[] data)
	{
		if (item.stackTagCompound == null) item.stackTagCompound = new NBTTagCompound();
		
		for (int counter = 0; counter < tagNames.length; ++counter)
		{
			if (data[counter] instanceof Integer) item.stackTagCompound.setInteger(tagNames[counter], (Integer)data[counter]);
			
		}
		
		return item;
	}
	
	public static HMItemStackHelper instance()
	{
		return instance;
	}
	
}
