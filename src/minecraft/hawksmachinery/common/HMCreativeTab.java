
package hawksmachinery.common;

import hawksmachinery.common.item.HMItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCreativeTab extends CreativeTabs
{
	public HMCreativeTab(String label)
	{
		super(label);
		
	}
	
	@Override
	public Item getTabIconItem()
	{
		return HMItem.parts;
	}
	
}
