
package hawksmachinery.common.block;

import hawksmachinery.common.api.IHMTechnicalMultiBlock;
import hawksmachinery.common.tileentity.HMTileEntityStarForgeTechnical;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockStarForgeTechnical extends BlockContainer
{
	public HMBlockStarForgeTechnical(int id)
	{
		super(id, Material.iron);
		setBlockName("HMStarForgeTechnical");
		setHardness(5.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityStarForgeTechnical();
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		return ((IHMTechnicalMultiBlock)world.getBlockTileEntity(x, y, z)).getVector().getBlock().getPickBlock(target, world, x, y, z);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		return ((IHMTechnicalMultiBlock)world.getBlockTileEntity(x, y, z)).getVector().getBlock().onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		((IHMTechnicalMultiBlock)world.getBlockTileEntity(x, y, z)).getVector().getBlock().breakBlock(world, x, y, z, par5, par6);
		
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z)
	{
		for (int newX = x - 1; newX < 2; ++newX)
		{
			for (int newZ = x - 1; newZ < 2; ++newZ)
			{
				if (world.getBlockId(x, y, z) == HMBlock.starForge.blockID)
				{
					Block.blocksList[world.getBlockId(x, y, z)].onBlockDestroyedByExplosion(world, newX, y, newZ);
					
				}
				
			}
			
		}
		
	}
	
}
