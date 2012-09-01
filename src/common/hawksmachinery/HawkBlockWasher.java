
package hawksmachinery;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import universalelectricity.extend.BlockMachine;

/**
 *
 *
 *
 * @author Elusivehawk
 */
public class HawkBlockWasher extends BlockMachine
{
	public static HawksMachinery BASEMOD;
	public HawkTileEntityWasher tileEntity;
	
	public HawkBlockWasher(int id)
	{
		super("Washer", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		GameRegistry.registerBlock(this);
		setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@Override
	public int damageDropped(int metadata)
	{
		return 0;
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (!world.isRemote)
		{
			player.openGui(BASEMOD.getModInstance(), 1, world, x, y, z);
			return true;
		}
		
		return true;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player)
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
		return BASEMOD.BLOCK_TEXTURE_FILE;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		this.tileEntity = new HawkTileEntityWasher();
		return this.tileEntity;
	}
	
	
	
	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		if (side != 0 && side != world.getBlockMetadata(x, y, z))
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityEnderman)
		{
			entity.attackEntityFrom(DamageSource.drown, 1);
		}
		
		entity.extinguish();
	}

}
