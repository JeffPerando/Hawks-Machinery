
package hawksmachinery.block;

import hawksmachinery.tileentity.HMTileEntityFireBlock;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockFireBlock extends HMBlockMachine
{
	public HMBlockFireBlock(String name, int id, Material mat)
	{
		super("HMFireBlock", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityFireBlock();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
	{
		if (entity.posY > y)
		{
			world.setBlockMetadataWithNotify(x, y, z, 1);
			
		}
		else
		{
			int direction = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			int newMetadata = 3;
			
			switch (direction)
			{
				case 0: newMetadata = 2; break;
				case 1: newMetadata = 5; break;
				case 2: newMetadata = 3; break;
				case 3: newMetadata = 4; break;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, newMetadata);
			
		}
		
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		switch (world.getBlockMetadata(x, y, z))
		{
			case 1: world.setBlockMetadataWithNotify(x, y, z, 4); break;
			case 2: world.setBlockMetadataWithNotify(x, y, z, 1); break;
			case 5: world.setBlockMetadataWithNotify(x, y, z, 2); break;
			case 3: world.setBlockMetadataWithNotify(x, y, z, 5); break;
			case 4: world.setBlockMetadataWithNotify(x, y, z, 3); break;
			
		}
		
		return true;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
}
