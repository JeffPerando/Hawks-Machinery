
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

/**
 * 
 * My personal preferences. Extend this instead of {@link Item} and you'll save yourself some trouble.
 * 
 * @author Elusivehawk
 */
public class HMItem extends Item
{
	public static HawksMachinery BASEMOD;
	
	public HMItem(int id)
	{
		super(id);
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		
	}
	
}
