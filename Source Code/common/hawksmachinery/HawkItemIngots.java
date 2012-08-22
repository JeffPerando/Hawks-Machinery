
package hawksmachinery;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemIngots extends Item
{
	public HawkItemIngots(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		setTabToDisplayOn(CreativeTabs.tabMaterials);
		
		OreDictionary.registerOre("ingotTitanium", new ItemStack(this, 1, 0));
		OreDictionary.registerOre("ingotAluminum", new ItemStack(this, 1, 1));
		OreDictionary.registerOre("ingotSilver", new ItemStack(this, 1, 2));
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		switch (dmg)
		{
			case 0: return 36;
			case 1: return 38;
			case 2: return 39;
			case 3: return 81;
			default: return 0;
		}
	}
	
	@Override
	public String getItemDisplayName(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "Titanium Ingot";
			case 1: return "Aluminum Ingot";
			case 2: return "Silver Ingot";
			case 3: return "Endium Ingot";
			default: return "Look Jay, 0% body fat!";
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		if (item.getItemDamage() == 3)
		{
			return EnumRarity.epic;
		}
		
		return EnumRarity.common;
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 3: return true;
			default: return false;
		}
	}
	
	public String getTextureFile()
	{
		return HawkManager.ITEM_TEXTURE_FILE;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter  = 0; counter <= 3; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
}
