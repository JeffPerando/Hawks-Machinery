
package hawksmachinery.blocks;

import hawksmachinery.HawksMachinery;
import hawksmachinery.tileentity.HawkTileEntityCrusher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import universalelectricity.basiccomponents.BlockBasicMachine;
import universalelectricity.prefab.BlockMachine;
import net.minecraft.src.*;
import net.minecraftforge.common.ForgeDirection;

/**
 *
 * Just the block for the Grinder.
 *
 * @author Elusivehawk
 */
public class HawkBlockCrusher extends HawkBlockMachine
{
	public HawkTileEntityCrusher tileEntity;
	
	public HawkBlockCrusher(int id)
	{
		super("HMCrusher", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		setCreativeTab(CreativeTabs.tabDecorations);
		
	}
	
	@Override
	protected int damageDropped(int metadata)
	{
		return 0;
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			player.openGui(BASEMOD.instance(), 0, world, x, y, z);
			return true;
		}
		
		return true;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer par5EntityPlayer)
	{
		switch(world.getBlockMetadata(x, y, z))
		{
			case 2: world.setBlockMetadataWithNotify(x, y, z, 4); break;
			case 5: world.setBlockMetadataWithNotify(x, y, z, 2); break;
			case 0: world.setBlockMetadataWithNotify(x, y, z, 5); break;
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
		this.tileEntity = new HawkTileEntityCrusher();
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
		return !(side.ordinal() == 1 || side.ordinal() == world.getBlockMetadata(x, y, z));
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
}
