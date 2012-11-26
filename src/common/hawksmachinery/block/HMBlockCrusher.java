
package hawksmachinery.block;

import java.util.Random;
import hawksmachinery.item.HMItemBlockCrusher;
import hawksmachinery.tileentity.HMTileEntityCrusher;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.*;
import net.minecraftforge.common.ForgeDirection;

/**
 *
 * Just the block for the Crusher.
 *
 * @author Elusivehawk
 */
public class HMBlockCrusher extends HMBlockMachine
{
	public HMBlockCrusher(int id)
	{
		super("HMCrusher", id, Material.iron);
		setHardness(5.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (super.onMachineActivated(world, x, y, z, player))
		{
			return false;
		}
		
		if (!world.isRemote)
		{
			player.openGui(BASEMOD.instance(), 0, world, x, y, z);
			
		}
		
		return true;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		switch (world.getBlockMetadata(x, y, z))
		{
			case 2: world.setBlockMetadataWithNotify(x, y, z, 4); break;
			case 5: world.setBlockMetadataWithNotify(x, y, z, 2); break;
			case 3: world.setBlockMetadataWithNotify(x, y, z, 5); break;
			case 4: world.setBlockMetadataWithNotify(x, y, z, 3); break;
			
		}
		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
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
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityCrusher();
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return 104;
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
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (((HMTileEntityCrusher)world.getBlockTileEntity(x, y, z)).canCrush())
		{
			switch (world.getBlockMetadata(x, y, z))
			{
				case 2: world.spawnParticle("smoke", x + 0.5, y + 1, z + 0.8, 0, 0.1, 0); break;
				case 3: world.spawnParticle("smoke", x + 0.5, y + 1, z + 0.2, 0, 0.1, 0); break;
				case 4: world.spawnParticle("smoke", x + 0.8, y + 1, z + 0.5, 0, 0.1, 0); break;
				case 5: world.spawnParticle("smoke", x + 0.2, y + 1, z + 0.5, 0, 0.1, 0); break;
				
			}
			
		}
		
	}
	
}
