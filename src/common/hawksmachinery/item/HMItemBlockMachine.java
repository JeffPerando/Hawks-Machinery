
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
