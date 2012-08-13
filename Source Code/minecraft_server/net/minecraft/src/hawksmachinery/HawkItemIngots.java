
package net.minecraft.src.hawksmachinery;

import java.util.ArrayList;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.oredict.OreDictionary;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemIngots extends Item implements ITextureProvider
{
	public HawkItemIngots(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		
		OreDictionary.registerOre("ingotTitanium", new ItemStack(this, 1, 0));
		OreDictionary.registerOre("ingotAluminum", new ItemStack(this, 1, 1));
		OreDictionary.registerOre("ingotSilver", new ItemStack(this, 1, 2));
		
	}
	
	@Override
	public void addCreativeItems(ArrayList itemList)
	{       
		for (int counter = 0; counter <= 5; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	public String getTextureFile()
	{
		return HawkManager.ITEM_TEXTURE_FILE;
	}
}
