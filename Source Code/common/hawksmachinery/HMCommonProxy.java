
package hawksmachinery;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import universalelectricity.extend.CommonProxy;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCommonProxy extends CommonProxy
{
	@Override
	public void preInit() {}
	
	@Override
	public void init()
	{
		GameRegistry.registerTileEntity(HawkTileEntityGrinder.class, "Grinder");
		GameRegistry.registerTileEntity(HawkTileEntityPad.class, "Pad");
		
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			return new HawkContainerGrinder(player.inventory, ((HawkTileEntityGrinder)tileEntity));
        }
		
		return null;
	}
	
	public World getWorld()
	{
		return null;
	}
	
}
