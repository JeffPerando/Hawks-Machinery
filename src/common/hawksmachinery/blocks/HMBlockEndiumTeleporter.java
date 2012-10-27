
package hawksmachinery.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.HawksMachinery;
import hawksmachinery.api.HMTeleportationHelper;
import hawksmachinery.items.HMItemBlockEndium;
import hawksmachinery.tileentity.HMTileEntityTeleporterSender;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockEndiumTeleporter extends BlockContainer
{
	public static HawksMachinery BASEMOD;
	public HMTileEntityTeleporterSender tileEntity;
	
	public HMBlockEndiumTeleporter(int id)
	{
		super(id, Material.iron);
		setHardness(5.0F);
		setResistance(100.0F);
		setTextureFile(BASEMOD.BLOCK_TEXTURE_FILE);
		setStepSound(Block.soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 3);
		GameRegistry.registerBlock(this, HMItemBlockEndium.class);
		
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (side)
		{
			case 0: return 115;
			case 1: return (meta == 0) ? 112 : 113;
			default: return 114;
		}
		
	}
	
	@Override
	public boolean hasTileEntity(int meta)
	{
		return meta == 0;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		this.tileEntity = new HMTileEntityTeleporterSender();
		return this.tileEntity;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float idk2, float idk3, float idk4)
	{
		if (!world.isRemote)
		{
			player.openGui(BASEMOD.instance(), 2, world, x, y, z);
		}
		
		return true;
		
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockTileEntity(x, y, z) != null)
		{
			if (this.tileEntity.isReadyToTeleport())
			{
				this.tileEntity.teleportEntity(entity);
				
			}
			
		}
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return null;
	}
	
}
