
package hawksmachinery;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.src.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItem extends Item
{
	public HawkItem(int id)
    {
	    super(id);
		setTabToDisplayOn(CreativeTabs.tabMisc);
    }
	
	public String getTextureFile()
	{
		return HawkManager.ITEM_TEXTURE_FILE;
	}
}
