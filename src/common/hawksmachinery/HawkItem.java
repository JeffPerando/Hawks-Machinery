
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
	public HawksMachinery BASEMOD;
	
	public HawkItem(int id)
    {
	    super(id);
		setTabToDisplayOn(CreativeTabs.tabMisc);
    }
	
	public String getTextureFile()
	{
		return BASEMOD.ITEM_TEXTURE_FILE;
	}
}
