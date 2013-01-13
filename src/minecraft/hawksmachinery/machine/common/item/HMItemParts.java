
package hawksmachinery.machine.common.item;

import hawksmachinery.core.common.item.HMItem;
import net.minecraft.item.ItemStack;

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
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 64 + dmg;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "item.HMPart" + item.getItemDamage();
	}
	
}
