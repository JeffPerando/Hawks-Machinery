
package hawksmachinery;

import java.util.List;

import net.minecraft.src.*;
import net.minecraft.src.forge.*;

/**
 * @author Elusivehawk
 *
 */
public class HawkItem extends Item implements ITextureProvider
{
	public HawkItem(String name, int id)
    {
	    super(id);
	    this.setItemName(name);
	    this.setMaxDamage(16);
	    ModLoader.addName(this, name);
    }
	
	public String getTextureFile()
	{
		return HawkManager.itemTextureFile;
	}
}
