
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.Item;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItem extends Item
{
	public static HawksMachinery BASEMOD;
	
	public HawkItem(int id)
	{
		super(id);
		
	}
	
	@Override
	public String getTextureFile()
	{
		return BASEMOD.ITEM_TEXTURE_FILE;
	}
	
}
