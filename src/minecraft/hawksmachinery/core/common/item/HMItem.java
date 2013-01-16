
package hawksmachinery.core.common.item;

import hawksmachinery.core.common.HMCore;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * 
 * My personal preferences. Extend this instead of {@link Item} and you'll save yourself some trouble.
 * 
 * @author Elusivehawk
 */
public class HMItem extends Item
{
	private int maxDmg;
	
	public HMItem(int id)
	{
		super(id);
		setTextureFile(HMCore.ITEM_TEXTURE_FILE);
		setCreativeTab(HMCore.instance().tab);
		
	}
	
	public HMItem setMaxDmg(int dmg)
	{
		this.maxDmg = dmg;
		return this;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		if (this.hasSubtypes)
		{
			for (int counter = 0; counter <= this.maxDmg; ++counter)
			{
				if (this.getItemNameIS(new ItemStack(this, 1, counter)) != null)
				{
					itemList.add(new ItemStack(this, 1, counter));
					
				}
				
			}
			
		}
		else
		{
			itemList.add(new ItemStack(this));
			
		}
		
	}
	
}
