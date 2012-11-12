
package hawksmachinery.block;

import java.util.Random;
import hawksmachinery.HawksMachinery;
import hawksmachinery.tileentity.HMTileEntityCrusher;
import hawksmachinery.tileentity.HMTileEntityMachine;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import universalelectricity.prefab.BlockMachine;
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
	public HMTileEntityMachine tileEntity;
	
	public HMBlockCrusher(int id)
	{
		super("HMCrusher", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		setCreativeTab(CreativeTabs.tabDecorations);
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (!world.isRemote && !super.onMachineActivated(world, x, y, z, player))
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
		this.tileEntity = new HMTileEntityCrusher();
		return this.tileEntity;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		switch (metadata)
		{
			case 2: switch (side)
					{
						case 1: return 0;
						case 2: return 2;
						default: return 3;
						
					}
			case 3: switch (side)
					{
						case 1: return 0;
						case 3: return 2;
						default: return 3;
						
					}
			case 4: switch (side)
					{
						case 1: return 1;
						case 4: return 2;
						default: return 3;
						
					}
			case 5: switch (side)
					{
						case 1: return 1;
						case 5: return 2;
						default: return 3;
						
					}
			default: switch (side)
					{
						case 1: return 0;
						case 3: return 2;
						default: return 3;
						
					}
			
		}
		
	}
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return !(side.ordinal() == 1 && side.ordinal() == world.getBlockMetadata(x, y, z));
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
}
