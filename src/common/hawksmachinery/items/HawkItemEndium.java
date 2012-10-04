
package hawksmachinery.items;

import java.util.List;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemEndium extends HawkItem
{
	public HawkItemEndium(int id)
	{
		super(id);
		setCreativeTab(CreativeTabs.tabMaterials);
		setHasSubtypes(true);
		setMaxDamage(0);
		
	}

	@Override
	public int getIconFromDamage(int meta)
	{
		switch (meta)
		{
			case 0: return 35;
			case 1: return 51;
			case 2: return 2;
			default: return 0;
		}
		
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "endiumIngot";
			case 1: return "endiumPlate";
			case 2: return "endiumAlloy";
		}
		
		return null;
		
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return EnumRarity.rare;
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() != 2;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter = 0; counter <= 2; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
}
