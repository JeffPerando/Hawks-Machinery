
package hawksmachinery.items;

import java.util.List;
import net.minecraft.src.CreativeTabs;
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
		//this.en_USNames = new String[]{"Endium", "Cobalt"};
		
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
		return null; //en_USNames[item.getItemDamage()].toLowerCase() + "Plating";
	}
	
}
