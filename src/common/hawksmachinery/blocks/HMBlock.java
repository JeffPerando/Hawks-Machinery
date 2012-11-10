
package hawksmachinery.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.HawksMachinery;
import net.minecraft.src.Achievement;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.World;

/**
 * 
 * My personal preferences. Extend this instead of {@link Block} and you'll save yourself some trouble.
 * 
 * @author Elusivehawk
 */
public class HMBlock extends Block
{
	public static Block crusher;
	public static Block washer;
	public static Block endiumChunkloader;
	public static Block endiumOre;
	public static Block endiumTeleporter;
	
	public static HawksMachinery BASEMOD;
	private Achievement onBlockBrokenAch;
	
	public HMBlock(int id, Material mat, int textureID, Achievement onBrokenAch)
	{
		super(id, mat);
		setHardness(1.0F);
		setResistance(5.0F);
		setTextureFile(BASEMOD.BLOCK_TEXTURE_FILE);
		
		if (textureID >= 0)
		{
			this.blockIndexInTexture = textureID;
		}
		
		this.onBlockBrokenAch = onBrokenAch;
		
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
	{
		if (this.onBlockBrokenAch != null)
		{
			player.addStat(this.onBlockBrokenAch, 1);
		}
		
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y, z) != endiumOre.blockID && world.getBlockMetadata(x, y, z) == 0;
	}
	
}
