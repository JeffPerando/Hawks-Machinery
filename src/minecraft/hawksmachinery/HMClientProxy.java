
package hawksmachinery;

import hawksmachinery.block.HMBlock;
import hawksmachinery.item.HMItem;
import hawksmachinery.tileentity.*;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMClientProxy extends HMCommonProxy
{
	public static final String[] SUPPORTED_LANGS = new String[]{"en_US"};
	
	public void registerRenderInformation()
	{
		super.registerRenderInformation();
		
		MinecraftForgeClient.preloadTexture(HM.BLOCK_TEXTURE_FILE);
		MinecraftForgeClient.preloadTexture(HM.ITEM_TEXTURE_FILE);
		
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityCrusher.class, new HMRenderCrusher());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityWasher.class, new HMRenderWasher());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityStarForge.class, new HMRenderStarForge());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntitySinterer.class, new HMRenderSinterer());
		
		for (String lang : SUPPORTED_LANGS)
		{
			LanguageRegistry.instance().loadLocalization(HM.LANG_PATH + "/" + lang + ".txt", lang, false);
			
		}
		
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
