
package hawksmachinery.core.common.block;

import hawksmachinery.core.common.HMCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;

/**
 * 
 * My personal preferences. Extend this instead of {@link Block} and you'll save yourself some trouble.
 * 
 * @author Elusivehawk
 */
public class HMBlock extends Block
{
	private Achievement onBlockBrokenAch;
	
	public HMBlock(int id, Material mat, int textureID, Achievement onBrokenAch)
	{
		super(id, mat);
		setHardness(1.0F);
		setResistance(5.0F);
		setTextureFile(HMCore.BLOCK_TEXTURE_FILE);
		
		if (textureID >= 0)
		{
			this.blockIndexInTexture = textureID;
		}
		
		this.onBlockBrokenAch = onBrokenAch;
		
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
	{
		if (this.onBlockBrokenAch != null) player.addStat(this.onBlockBrokenAch, 1);
		
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
}
