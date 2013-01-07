
package hawksmachinery.client;

import hawksmachinery.client.gui.*;
import hawksmachinery.client.render.*;
import hawksmachinery.common.HMCommonProxy;
import hawksmachinery.common.HawksMachinery;
import hawksmachinery.common.tileentity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@SideOnly(Side.CLIENT)
public class HMClientProxy extends HMCommonProxy
{
	public static final String[] SUPPORTED_LANGS = new String[]{"en_US"};
	public final int HM_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
	
	public void registerRenderInformation()
	{
		super.registerRenderInformation();
		
		MinecraftForgeClient.preloadTexture(HawksMachinery.instance().BLOCK_TEXTURE_FILE);
		MinecraftForgeClient.preloadTexture(HawksMachinery.instance().ITEM_TEXTURE_FILE);
		
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityCrusher.class, new HMRenderCrusher());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityWasher.class, new HMRenderWasher());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityStarForge.class, new HMRenderStarForge());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntitySinterer.class, new HMRenderSinterer());
		
		RenderingRegistry.registerBlockHandler(new HMMachineInvRenderer());
		
		for (String lang : SUPPORTED_LANGS)
		{
			LanguageRegistry.instance().loadLocalization(HawksMachinery.instance().LANG_PATH + "/" + lang + ".txt", lang, false);
			
		}
		
	}
	
	public int getHMRenderID()
	{
		return this.HM_RENDER_ID;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			switch (ID)
			{
				case 0: return new HMGUICrusher(player.inventory, ((HMTileEntityCrusher)tileEntity));
				case 1: return new HMGUIWasher(player.inventory, ((HMTileEntityWasher)tileEntity));
				//case 2: return new HMGUIEndiumTeleporter(player.inventory, ((HMTileEntityTeleporter)tileEntity));
				case 3: return new HMGUIFisher(player.inventory, (HMTileEntityFisher)tileEntity);
				case 4: return new HMGUIStarForge(player.inventory, (HMTileEntityStarForge)tileEntity);
				case 5: return new HMGUISinterer(player.inventory, (HMTileEntitySinterer)tileEntity);
				default: return null;
				
			}
			
        }
		
		return null;
	}
	
}
