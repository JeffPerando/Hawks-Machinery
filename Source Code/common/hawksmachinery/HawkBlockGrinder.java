
package hawksmachinery;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import universalelectricity.extend.BlockMachine;
import net.minecraft.src.*;
import net.minecraftforge.common.ForgeDirection;

/**
 *
 * Just the block for the Grinder.
 *
 * @author Elusivehawk
 */
public class HawkBlockGrinder extends BlockMachine
{
	public HawkTileEntityGrinder tileEntity;
	
	public HawkBlockGrinder(int id)
	{
		super("Grinder", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		GameRegistry.registerBlock(this, HawkItemBlockGrinder.class);
		setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@Override
	protected int damageDropped(int metadata)
	{
		return 0;
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (!world.isRemote)
		{
			player.openGui(HawkManager.getModInstance(), 0, world, x, y, z);
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
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		this.tileEntity = new HawkTileEntityGrinder(3);
		return this.tileEntity;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		switch (metadata)
		{
			case 2: switch (side)
					{
						case 1: return 5;
						case 2: return 6;
						default: return 7;
					}
			case 3: switch (side)
					{
						case 1: return 5;
						case 3: return 6;
						default: return 7;
					}
			case 4: switch (side)
					{
						case 1: return 23;
						case 4: return 6;
						default: return 7;
					}
			case 5: switch (side)
					{
						case 1: return 23;
						case 5: return 6;
						default: return 7;
					}
			default: switch (side)
					{
						case 1: return 5;
						case 3: return 6;
						default: return 7;
					}
		}		
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityItem)
		{
			int id = ((EntityItem)entity).item.itemID;
			int meta = ((EntityItem)entity).item.getItemDamage();
			
			if (this.tileEntity.containingItems[2] == null)
			{
				this.tileEntity.containingItems[2] = ((EntityItem)entity).item;
				entity.setDead();
			}
			else
			{
				if (this.tileEntity.containingItems[2] == new ItemStack(id, this.tileEntity.containingItems[2].stackSize, meta))
				{
					this.tileEntity.containingItems[2].stackSize = (this.tileEntity.containingItems[2].stackSize + ((EntityItem)entity).item.stackSize);
					entity.setDead();
				}
			}
		}
	}
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		if (side.ordinal() == 0 || side.ordinal() == world.getBlockMetadata(x, y, z))
		{
			return false;
		}
		
		return true;
	}
	
}
