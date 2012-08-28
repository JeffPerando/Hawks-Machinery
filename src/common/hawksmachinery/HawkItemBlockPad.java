
package hawksmachinery;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * 
 * For achievement purposes.
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockPad extends ItemBlock
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemBlockPad(int id)
	{
		super(id);
	}
	
	@Override
	public int getMetadata(int metadata)
	{
		return metadata;
	}
	
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		
	}
	
}
