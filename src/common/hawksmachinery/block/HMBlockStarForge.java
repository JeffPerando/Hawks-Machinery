
package hawksmachinery.block;

import hawksmachinery.tileentity.HMTileEntityStarForge;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockStarForge extends HMBlockMachine
{
	public HMBlockStarForge(int id)
	{
		super("HMStarForge", id, Material.iron);
		setHardness(5.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		setCreativeTab(null);//TODO Reactivate.
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (super.onMachineActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
		{
			return false;
		}
		
		if (!world.isRemote)
		{
			player.openGui(BASEMOD.instance(), 4, world, x, y, z);
			
		}
		
		return true;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
	{
		((HMTileEntityStarForge)world.getBlockTileEntity(x, y, z)).onCreate(new Vector3(x, y, z));
		
	}
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		((HMTileEntityStarForge)world.getBlockTileEntity(x, y, z)).onDestroy(world.getBlockTileEntity(x, y, z), false);
		super.breakBlock(world, x, y, z, par5, par6);
		
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return 106;
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
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new HMTileEntityStarForge();
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		for (int x2 = -1; x2 < 2; ++x2)
		{
			for (int z2 = -1; z2 < 2; ++z2)
			{
				if (!super.canPlaceBlockAt(world, x + x2, y, z + z2))
				{
					return false;
				}
				
			}
			
		}
		
		return true;
	}
	
}
