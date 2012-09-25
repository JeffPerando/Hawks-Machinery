
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
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
public class HawkItemParts extends HawkItem
{
	public static String[] en_USNames = {"Electric Piston", "Laser", "Circular Saw Blade", "Light Bulb", "Heating Coil", "Electric Magnet"};
	
	public HawkItemParts(int id)
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
			case 0: return 16;
			case 1: return 32;
			case 2: return 48;
			case 3: return 64;
			case 4: return 80;
			case 5: return 96;
			default: return 0;
		}
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter  = 0; counter <= 5; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "electricPiston";
			case 1: return "laser";
			case 2: return "circularSawBlade";
			case 3: return "lightBulb";
			case 4: return "heatingCoil";
			case 5: return "electroMagnet";
			
		}
		
		return null;
	}
	
	public void addCreativeTab()
	{
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
}
