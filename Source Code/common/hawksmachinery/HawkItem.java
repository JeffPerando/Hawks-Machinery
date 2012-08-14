
package hawksmachinery;

import java.util.List;

import net.minecraft.src.*;

/**
 * @author Elusivehawk
 *
 */
public class HawkItem extends Item
{
	public HawkItem(String name, int id)
    {
	    super(id);
	    this.setItemName(name);
	    ModLoader.addName(this, name);
    }
	
	public String getTextureFile()
	{
		return HawkManager.ITEM_TEXTURE_FILE;
	}
}
