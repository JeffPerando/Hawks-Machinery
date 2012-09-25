
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

/**
 * 
 * Just some conveniences for me.
 * 
 * @author Elusivehawk
 */
public class HawkItem extends Item
{
	public static HawksMachinery BASEMOD;
	
	public HawkItem(int id)
	{
		super(id);
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		addCreativeTab();
	}
	
	/**
	 * Override this is you would like to change which Creative mode tab this item appears in.
	 */
	public void addCreativeTab()
	{
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
}
