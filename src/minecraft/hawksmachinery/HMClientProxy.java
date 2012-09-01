
package hawksmachinery;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
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
	
	@Override
	public void preInit()
	{
		//Preload textures
		MinecraftForgeClient.preloadTexture(BASEMOD.BLOCK_TEXTURE_FILE);
		MinecraftForgeClient.preloadTexture(BASEMOD.ITEM_TEXTURE_FILE);
	}
	
	@Override
	public void init()
	{
		HawkLanguageCore.addToolTips();
		
	}
	
	@Override
	public void postInit() {}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			switch (ID)
			{
				case 0: return new HawkGUIGrinder(player.inventory, ((HawkTileEntityGrinder)tileEntity));
				case 1: return new HawkGUIWasher(player.inventory, ((HawkTileEntityWasher)tileEntity));
				
			}
        }
		
		return null;
	}
	
	@Override
	public World getWorld()
	{
		return ModLoader.getMinecraftInstance().theWorld;
	}
}
