
package hawksmachinery.client.render;

import java.util.HashMap;
import org.lwjgl.opengl.GL11;
import hawksmachinery.client.model.*;
import hawksmachinery.common.HawksMachinery;
import hawksmachinery.common.block.HMBlock;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * 
 * Renders all machines in block form in the Player's inventory.
 * 
 * @author Elusivehawk
 */
public class HMMachineInvRenderer implements ISimpleBlockRenderingHandler
{
	public HMModelCrusher crusherModel = new HMModelCrusher();
	public HMModelWasher washerModel = new HMModelWasher();
	public HMModelStarForge starForgeModel = new HMModelStarForge();
	public HMModelSinterer sintererModel = new HMModelSinterer();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		GL11.glPushMatrix();
		
		if (block.blockID == HMBlock.crusher.blockID)
		{
			GL11.glBindTexture(3553, FMLClientHandler.instance().getClient().renderEngine.getTexture(HawksMachinery.TEXTURE_PATH + "/Crusher.png"));
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.5F, .8F, 0.5F);
			GL11.glScalef(1F, -1F, -1F);
			this.crusherModel.render(null, 0, 0, 0, 0, 0, 0.0625F);
			
		}
		else if (block.blockID == HMBlock.washer.blockID)
		{
			GL11.glBindTexture(3553, FMLClientHandler.instance().getClient().renderEngine.getTexture(HawksMachinery.TEXTURE_PATH + "/Washer.png"));
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.5F, .8F, 0.5F);
			GL11.glScalef(1F, -1F, -1F);
			this.washerModel.render(null, 0, 0, 0, 0, 0, 0.0625F);
			
		}
		else if (block.blockID == HMBlock.starForge.blockID)
		{
			GL11.glBindTexture(3553, FMLClientHandler.instance().getClient().renderEngine.getTexture(HawksMachinery.TEXTURE_PATH + "/StarForge.png"));
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.5F, .8F, 0.5F);
			GL11.glScalef(1F, -1F, -1F);
			this.starForgeModel.render(null, 0, 0, 0, 0, 0, 0.0625F);
			
		}
		else if (block.blockID == HMBlock.sinterer.blockID)
		{
			GL11.glBindTexture(3553, FMLClientHandler.instance().getClient().renderEngine.getTexture(HawksMachinery.TEXTURE_PATH + "/Sinterer.png"));
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.5F, .8F, 0.5F);
			GL11.glScalef(1F, -1F, -1F);
			this.sintererModel.render(null, 0, 0, 0, 0, 0, 0.0625F);
			
		}
		
		GL11.glPopMatrix();
		
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}
	
	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}
	
	@Override
	public int getRenderId()
	{
		return HawksMachinery.PROXY.getHMRenderID();
	}
	
}
