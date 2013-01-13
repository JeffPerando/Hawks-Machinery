
package hawksmachinery.machine.common.item;

import hawksmachinery.core.common.HMCore;
import net.minecraft.item.ItemBlock;

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
		setTextureFile(HMCore.instance().ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public int getMetadata(int meta)
    {
        return meta;
    }
	
}
