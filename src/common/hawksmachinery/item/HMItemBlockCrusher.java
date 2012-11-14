
package hawksmachinery.item;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockCrusher extends ItemBlock
{
	public HMItemBlockCrusher(int id)
	{
		super(id);
		setTextureFile(HawksMachinery.ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 104;
	}
	
	@Override
	public int getMetadata(int meta)
    {
        return meta;
    }
	
}
