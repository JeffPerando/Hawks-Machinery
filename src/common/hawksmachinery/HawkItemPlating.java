
package hawksmachinery;

import java.util.List;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemPlating extends Item
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemPlating(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		setTabToDisplayOn(CreativeTabs.tabMaterials);
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		switch (dmg)
		{
			case 0: return 52;
			case 1: return 56;
			case 2: return 53;
			case 3: return 51;
			default: return 0;
		}
	}
	
	@Override
	public String getItemDisplayName(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "Titanium Plating";
			case 1: return "Aluminum Plating";
			case 2: return "Silver Plating";
			case 3: return "Endium Plating";
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
		return item.getItemDamage() == 3;
	}
	
	@Override
	public String getTextureFile()
	{
		return BASEMOD.ITEM_TEXTURE_FILE;
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
