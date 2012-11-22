
package hawksmachinery.block;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.api.HMTeleportationHelper;
import hawksmachinery.item.HMItemBlockTeleporter;
import hawksmachinery.tileentity.HMTileEntityTeleporter;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockEndiumTeleporter extends HMBlockMachine
{
	public HMBlockEndiumTeleporter(int id)
	{
		super(null, id, Material.iron);
		setHardness(5.0F);
		setResistance(100.0F);
		setStepSound(Block.soundMetalFootstep);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 3);
		GameRegistry.registerBlock(this, HMItemBlockTeleporter.class);
		
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (side)
		{
			case 0: return meta == 0 ? 67 : 243;
			case 1: return meta == 0 ? 64 : 65;
			default: return 66;
		}
		
	}
	
	/*
	@Override
	public boolean hasTileEntity(int meta)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityTeleporter();
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (!world.isRemote && !super.onMachineActivated(world, x, y, z, player))
		{
			player.openGui(BASEMOD.instance(), 2, world, x, y, z);
		}
		
		return true;
		
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		if (entity != null)
		{
			((HMTileEntityTeleporter)world.getBlockTileEntity(x, y, z)).tryTeleportEntity(entity);
			
		}
		
	}
	*/
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return side != ForgeDirection.UP;
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list)
	{
		/*
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		*/
		
	}
	
	/*
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		ForgeChunkManager.releaseTicket(((HMTileEntityTeleporter)world.getBlockTileEntity(x, y, z)).heldChunk);
		HMTeleportationHelper.instance().removeCoords(((HMTileEntityTeleporter)world.getBlockTileEntity(x, y, z)).coords);
		super.breakBlock(world, x, y, z, par5, par6);
		
	}
	*/
	
}
