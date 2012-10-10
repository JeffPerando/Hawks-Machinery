
package hawksmachinery.items;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemIngots extends HMItem
{
	public static String[] en_USNames = {"Endium"};
	
	public HMItemIngots(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);
		
	}
	
	public int getIconFromDamage(int dmg)
	{
		return 35;
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return "endiumIngot";//TODO Make more ingots.
	}
	
}
