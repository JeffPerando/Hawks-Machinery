
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
	public void init() {}
	
	@Override
	public void postInit() {}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		switch (id)
		{
			case 0: return new HawkContainerGrinder(player.inventory, ((HawkTileEntityGrinder)tileEntity));
			//case 1: return new HawkContainerWasher(player.inventory, ((HawkTileEntityWasher)tileEntity));
		}
		
		return null;
	}
	
	public World getWorld()
	{
		return null;
	}
	
}
