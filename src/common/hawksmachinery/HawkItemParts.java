
package hawksmachinery;

import java.util.List;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * 
 * Parts!
 * 
 * @author Elusivehawk
 */
public class HawkItemParts extends Item
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemParts(int id)
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
			case 0: return 16;
			case 1: return 32;
			case 2: return 48;
			case 3: return 64;
			default: return 0;
		}
	}
	
	@Override
	public String getItemDisplayName(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "Electric Piston";
			case 1: return "Laser";
			case 2: return "Circular Saw Blade";
			case 3: return "Light Bulb";
			default: return "Look Jay, 0% body fat!";
		}
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter  = 0; counter <= 3; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	@Override
	public String getTextureFile()
	{
		return BASEMOD.ITEM_TEXTURE_FILE;
	}
	
}
