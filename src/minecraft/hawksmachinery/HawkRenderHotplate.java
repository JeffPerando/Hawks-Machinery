
package hawksmachinery;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkRenderHotplate implements ISimpleBlockRenderingHandler
{
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		renderer.overrideBlockTexture = 29;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.overrideBlockTexture = -1;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		
		return true;
	}
	
	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}
	
	@Deprecated
	@Override
	public int getRenderId()
	{
		return 0;
	}
	
}
