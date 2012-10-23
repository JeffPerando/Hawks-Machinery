
package hawksmachinery.blocks;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.items.HMItemBlockEndium;
import hawksmachinery.tileentity.HMTileEntityEndiumChunkloader;
import net.minecraft.src.Block;
import net.minecraft.src.Chunk;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPig;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeHooks;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockEndiumChunkloader extends HMBlock
{
	public HMTileEntityEndiumChunkloader tileEntity;
	
	public HMBlockEndiumChunkloader(int id)
	{
		super(id, Material.iron, 96, null);
		setResistance(1000000000.0F);
		setBlockName("endiumChunkloader");
		setStepSound(Block.soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(this, HMItemBlockEndium.class);
		
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		this.tileEntity = new HMTileEntityEndiumChunkloader();
		return this.tileEntity;
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (world.isBlockIndirectlyGettingPowered(x, y + 1, z))
		{
			Chunk chunkInside = world.getChunkFromBlockCoords(x, z);
			int xPosMax = chunkInside.xPosition << 4;
			int zPosMax = chunkInside.zPosition << 4;
			int xPosMin = xPosMax + 16;
			int zPosMin = zPosMax + 16;
			
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 1.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 2.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 3.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 4.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMax + 1.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMax + 2.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMax + 3.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMax + 4.5, 0, 0, 0);
			
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 1.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 2.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 3.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 4.5, y + 1, zPosMax + 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMax + 1.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMax + 2.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMax + 3.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMax + 4.5, 0, 0, 0);
			
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 1.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 2.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 3.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 4.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMin - 1.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMin - 2.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMin - 3.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMax + 0.5, y + 1, zPosMin - 4.5, 0, 0, 0);
			
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 1.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 2.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 3.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 4.5, y + 1, zPosMin - 0.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMin - 1.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMin - 2.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMin - 3.5, 0, 0, 0);
			world.spawnParticle("portal", xPosMin - 0.5, y + 1, zPosMin - 4.5, 0, 0, 0);
			
		}
		
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		return 15;
	}
	
	@Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
	{
		ForgeChunkManager.releaseTicket(this.tileEntity.heldChunk);
		
	}
	
}
