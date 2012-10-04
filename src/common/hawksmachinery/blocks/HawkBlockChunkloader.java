
package hawksmachinery.blocks;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.itemblocks.HawkItemBlockChunkloader;
import hawksmachinery.tileentity.HawkTileEntityChunkloader;
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
import net.minecraftforge.common.ForgeHooks;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkBlockChunkloader extends HawkBlock
{
	public HawkTileEntityChunkloader tileEntity;
	public String tempUsername;
	
	public HawkBlockChunkloader(int id)
	{
		super(id, Material.iron, 5);
		setResistance(1000000000.0F);
		setBlockName("endiumChunkloader");
		setStepSound(Block.soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
	{
		if (entity != null)
		{
			if (entity instanceof EntityPlayer)
			{
				if (this.tileEntity != null)
				{
					this.tileEntity.ownerUsername = ((EntityPlayer)entity).username;
				}
				else
				{
					this.tempUsername = ((EntityPlayer)entity).username;
				}
			}
			
		}
		
	}
	
	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer pinhead, World world, int x, int y, int z)
	{
		return pinhead.username == this.tileEntity.ownerUsername ?  ForgeHooks.blockStrength(this, pinhead, world, x, y, z) : -0.5F;
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		this.tileEntity = new HawkTileEntityChunkloader();
		if (this.tempUsername != null)
		{
			this.tileEntity.ownerUsername = this.tempUsername;
		}
		
		return this.tileEntity;
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer dolt)
	{
		if (dolt != null)
		{
			if (!dolt.capabilities.isCreativeMode)
			{
				if (dolt.username != this.tileEntity.ownerUsername)
				{
					world.setBlockWithNotify(x, y, z, this.blockID);
				}
				
			}
			
		}
		else
		{
			world.setBlockWithNotify(x, y, z, this.blockID);
		}
		
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
	public void registerSelf()
	{
		GameRegistry.registerBlock(this, HawkItemBlockChunkloader.class);
	}
	
}
