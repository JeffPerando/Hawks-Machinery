
package hawksmachinery.items;

import java.util.List;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemRivets extends HMItem
{
	public static String[] en_USNames = {"Copper River", "Bronze Rivet", "Steel Rivet", "Iron Rivet", "Gold Rivet", "Endium Rivet"};
	
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
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter = 0; counter <= 5; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
    
	@Override
	public String getItemNameIS(ItemStack item)
	{
		switch(item.getItemDamage())
		{
			case 0: return "copperRivet";
			case 1: return "bronzeRivet";
			case 2: return "steelRivet";
			case 3: return "ironRivet";
			case 4: return "goldRivet";
			case 5: return "endiumRivet";
		}
		
		return null;
	}
	
}
