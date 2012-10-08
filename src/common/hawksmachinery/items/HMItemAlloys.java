
package hawksmachinery.items;

import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemAlloys extends HMItem
{
	public static String[] en_USNames = {"Endium"};
	
	public HMItemAlloys(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		
	}
	
	public int getIconFromDamage(int dmg)
	{
		return 2;
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return "endiumAlloy";//TODO Make more alloys.
	}
	
}
