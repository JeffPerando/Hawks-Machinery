
package hawksmachinery;

import hawksmachinery.api.HMLanguageCore;
import hawksmachinery.lang.HMLangen_US;
import hawksmachinery.tileentity.*;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMClientProxy extends HMCommonProxy
{
	public static HawksMachinery BASEMOD;
	
	public void addVanillaLangHandlers()
	{
		HMLanguageCore.registerLangHandler(new HMLangen_US(), "en_US");
		
	}
	
	public void registerRenderInformation()
	{
		MinecraftForgeClient.preloadTexture(BASEMOD.BLOCK_TEXTURE_FILE);
		MinecraftForgeClient.preloadTexture(BASEMOD.ITEM_TEXTURE_FILE);
		super.registerRenderInformation();
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityCrusher.class, new HMRenderCrusher());
		HMLanguageCore.addToolTips();
		
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
				case 2: return new HMGUIEndiumTeleporter(player.inventory, ((HMTileEntityTeleporter)tileEntity));
				case 3: return new HMGUIFisher(player.inventory, (HMTileEntityFisher)tileEntity);
				default: return null;
				
			}
        }
		
		return null;
	}
	
}
