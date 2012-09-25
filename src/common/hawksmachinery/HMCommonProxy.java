
package hawksmachinery;

import hawksmachinery.tileentity.HawkTileEntityGrinder;
import hawksmachinery.tileentity.HawkTileEntityWasher;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCommonProxy implements IGuiHandler
{
	public void registerRenderInformation(){}
	
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
			case 1: return new HawkContainerWasher(player.inventory, ((HawkTileEntityWasher)tileEntity));
		}
		
		return null;
	}
	
	public void chatToSSPPlayer(String message){}
	
}
