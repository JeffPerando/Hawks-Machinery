
package hawksmachinery.item;

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
public class HMItemParts extends HMItem
{
	public HMItemParts(int id)
	{
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabs.tabMaterials);
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 64 + dmg;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "part" + item.getItemDamage();
	}
	
}
