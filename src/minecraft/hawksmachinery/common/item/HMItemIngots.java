
package hawksmachinery.common.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemIngots extends HMItem
{
	public HMItemIngots(int id)
	{
		super(id);
		setHasSubtypes(true);
		
	}

	@Override
	public int getIconFromDamage(int dmg)
	{
		return 32 + dmg;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "item.HMIngot" + item.getItemDamage();
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() == 0;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return item.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.common;
	}
	
}
