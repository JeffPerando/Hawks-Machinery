
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
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
public class HawkItemPlating extends HawkItem
{
	public static String[] en_USNames = {"Titanium", "Aluminum", "Silver", "Endium"};
	public static String[] en_PTNames = {"Better 'n Iron", "Cheap Bullion", "Silver Bullion", "Metal 'o the Tallone"};
	
	public HawkItemPlating(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		
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
	public EnumRarity getRarity(ItemStack item)
	{
		if (item.getItemDamage() == 3)
		{
			return EnumRarity.uncommon;
		}
		
		return EnumRarity.common;
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() == 3;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter = 0; counter <= 3; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return en_USNames[item.getItemDamage()].toLowerCase() + "Plating";
	}
	
	public void addCreativeTab()
	{
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
}
