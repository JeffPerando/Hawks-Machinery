
package hawksmachinery.block;

import java.util.Random;
import hawksmachinery.HawksMachinery;
import hawksmachinery.item.HMItemBlockWasher;
import hawksmachinery.tileentity.HMTileEntityWasher;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.implement.UEDamageSource;
import universalelectricity.prefab.BlockMachine;

/**
 *
 *
 *
 * @author Elusivehawk
 */
public class HMBlockWasher extends HMBlockMachine
{
	public HMBlockWasher(int id)
	{
		super("HMWasher", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(this, HMItemBlockWasher.class);
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public int damageDropped(int metadata)
	{
		return 0;
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (!world.isRemote && !super.onMachineActivated(world, x, y, z, player))
		{
			player.openGui(BASEMOD.instance(), 1, world, x, y, z);
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
		return new HMTileEntityWasher();
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		switch (metadata)
		{
			case 2: switch (side)
					{
						case 1: return 16;
						case 2: return 18;
						default: return 19;
					}
			case 3: switch (side)
					{
						case 1: return 16;
						case 3: return 18;
						default: return 19;
					}
			case 4: switch (side)
					{
						case 1: return 17;
						case 4: return 18;
						default: return 19;
					}
			case 5: switch (side)
					{
						case 1: return 17;
						case 5: return 18;
						default: return 19;
					}
			default: switch (side)
					{
						case 1: return 16;
						case 3: return 18;
						default: return 19;
					}
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (((HMTileEntityWasher)world.getBlockTileEntity(x, y, z)).canWash())
		{
			if (entity instanceof EntityEnderman)
			{
				entity.attackEntityFrom(DamageSource.drown, 1);
				
			}
			else if (entity.isWet())
			{
				entity.attackEntityFrom(UEDamageSource.electrocution, 1);
				
			}
			
		}
		
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
		if (((HMTileEntityWasher)world.getBlockTileEntity(x, y, z)).canWash())
		{
			world.spawnParticle("splash", x + 0.5, y + 1, z + 0.5, 0, 0.1, 0);
			world.spawnParticle("splash", x + 0.5, y + 1, z + 0.5, 0, 0.1, 0);
			
		}
		
	}
	
}
