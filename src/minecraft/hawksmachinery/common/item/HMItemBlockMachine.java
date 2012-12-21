
package hawksmachinery.common.item;

import hawksmachinery.common.HawksMachinery;
import net.minecraft.block.Block;
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
		setTextureFile(HawksMachinery.ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return Block.blocksList[this.getBlockID()].getBlockTextureFromSideAndMetadata(0, dmg);
	}
	
	@Override
	public int getMetadata(int meta)
    {
        return meta;
    }
	
}
