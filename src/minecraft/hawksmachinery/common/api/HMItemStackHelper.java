
package hawksmachinery.common.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * Helps ease the annoyance of having to NBTTag {@link ItemStack}s.
 * 
 * @author Elusivehawk
 */
public class HMItemStackHelper
{
	private static HMItemStackHelper INSTANCE = new HMItemStackHelper();
	
	public ItemStack enchantItem(ItemStack item, Enchantment[] enchants, int[] levels)
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
			if (data[counter] instanceof String) item.stackTagCompound.setString(tagNames[counter], (String)data[counter]);
			else if (data[counter] instanceof Integer) item.stackTagCompound.setInteger(tagNames[counter], (Integer)data[counter]);
			else if (data[counter] instanceof Float) item.stackTagCompound.setFloat(tagNames[counter], (Float)data[counter]);
			else if (data[counter] instanceof Double) item.stackTagCompound.setDouble(tagNames[counter], (Double)data[counter]);
			else if (data[counter] instanceof Long) item.stackTagCompound.setLong(tagNames[counter], (Long)data[counter]);
			else if (data[counter] instanceof Short) item.stackTagCompound.setShort(tagNames[counter], (Short)data[counter]);
			else if (data[counter] instanceof Byte) item.stackTagCompound.setByte(tagNames[counter], (Byte)data[counter]);
			else if (data[counter] instanceof Boolean) item.stackTagCompound.setBoolean(tagNames[counter], (Boolean)data[counter]);
			else if (data[counter] instanceof NBTTagCompound) item.stackTagCompound.setCompoundTag(tagNames[counter], (NBTTagCompound)data[counter]);
			else if (data[counter] instanceof NBTBase) item.stackTagCompound.setTag(tagNames[counter], (NBTBase)data[counter]);
			
		}
		
		return item;
	}
	
	public static HMItemStackHelper instance()
	{
		return INSTANCE;
	}
	
}
