
package hawksmachinery.item;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.ItemBlock;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockWasher extends ItemBlock
{
	public HMItemBlockWasher(int id)
	{
		super(id);
		setTextureFile(HawksMachinery.ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 105;
	}
	
	@Override
	public int getMetadata(int meta)
    {
        return meta;
    }
	
}
