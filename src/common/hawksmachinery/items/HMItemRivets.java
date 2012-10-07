
package hawksmachinery.items;

import java.util.List;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import hawksmachinery.interfaces.HMRepairInterfaces.IHMRivet;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemRivets extends HMItem implements IHMRivet
{
	public static String[] en_USNames = {"Copper", "Bronze", "Iron", "Steel", "Gold", "Endium"};
	
	public HMItemRivets(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 67 + dmg;
	}
	
	public int getRepairAmount(ItemStack rivet)
	{
		switch (rivet.getItemDamage())
		{
			case 0: return 2;
			case 1: return 4;
			case 2: return 4;
			case 3: return 5;
			case 4: return 3;
			case 5: return 10;
			default: return 0;
		}
		
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "copperRivet";
			case 1: return "bronzeRivet";
			case 2: return "ironRivet";
			case 3: return "steelRivet";
			case 4: return "goldRivet";
			case 5: return "endiumRivet";
			default: return null;
		}
		
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter = 0; counter <= 5; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
}
