
package hawksmachinery.common.item;

import hawksmachinery.common.HawksMachinery;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockMachine extends ItemBlock
{
	public HMItemBlockMachine(int id)
	{
		super(id);
		setTextureFile(HawksMachinery.instance().ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public int getMetadata(int meta)
    {
        return meta;
    }
	
}
