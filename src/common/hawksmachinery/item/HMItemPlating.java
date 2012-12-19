
package hawksmachinery.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemPlating extends HMItem
{
	public HMItemPlating(int id)
	{
		super(id);
		setHasSubtypes(true);
		
	}

	@Override
	public int getIconFromDamage(int dmg)
	{
		return 48 + dmg;
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
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "item.HMPlating" + item.getItemDamage();
	}
	
}
