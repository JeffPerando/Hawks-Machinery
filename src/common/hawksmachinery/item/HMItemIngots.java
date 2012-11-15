
package hawksmachinery.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;

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
		setCreativeTab(CreativeTabs.tabMaterials);
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
		return "HMingot" + item.getItemDamage();
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
