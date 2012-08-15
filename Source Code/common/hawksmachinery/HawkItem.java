
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
	public HawkItem(String name, int id)
    {
	    super(id);
	    this.setItemName(name);
	    LanguageRegistry.instance().addNameForObject(this, "en_US", name);
		setTabToDisplayOn(CreativeTabs.tabMisc);
    }
	
	public String getTextureFile()
	{
		return HawkManager.ITEM_TEXTURE_FILE;
	}
}
