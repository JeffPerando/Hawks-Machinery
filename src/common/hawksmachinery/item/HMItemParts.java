
package hawksmachinery.item;

import universalelectricity.prefab.UETab;
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
		setCreativeTab(UETab.INSTANCE);
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 64 + dmg;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "HMPart" + item.getItemDamage();
	}
	
}
