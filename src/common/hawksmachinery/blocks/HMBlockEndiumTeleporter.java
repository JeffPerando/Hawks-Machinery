
package hawksmachinery.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.HawksMachinery;
import hawksmachinery.interfaces.HMTeleportationHelper;
import hawksmachinery.items.HMItemBlockEndium;
import hawksmachinery.tileentity.HMTileEntityTeleporterSender;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.Entity;
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
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 3);
		GameRegistry.registerBlock(this, HMItemBlockEndium.class);
		
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (side)
		{
			case 0: return (meta == 0) ? 112 : 113;
			case 1: return 115;
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
