
package hawksmachinery;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkBlockMiningDrill extends Block
{
	public HawkTileEntityMiningDrill tileEntity;
	
	public HawkBlockMiningDrill(int id)
	{
		super(id, Material.iron);
		setHardness(1.5F);
		setResistance(50.0F);
		GameRegistry.registerBlock(this, HawkItemBlockMiningDrill.class);
		setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@Override
	protected int damageDropped(int metadata)
	{
		if (metadata == 2)
		{
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return 0;
	}
	
	@Override
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		if (metadata <= 2)
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		if (metadata <= 2)
		{
			this.tileEntity = new HawkTileEntityMiningDrill();
			return this.tileEntity;
		}
		
		return null;
	}
	
}
