
package hawksmachinery;

import hawksmachinery.tileentity.HawkTileEntityChunkloader;
import hawksmachinery.tileentity.HawkTileEntityCrusher;
import hawksmachinery.tileentity.HawkTileEntityWasher;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCommonProxy implements IGuiHandler, IConnectionHandler
{
	public static HawksMachinery BASEMOD;
	
	public void registerRenderInformation()
	{
		GameRegistry.registerTileEntity(HawkTileEntityCrusher.class, "HMCrusher");
		GameRegistry.registerTileEntity(HawkTileEntityWasher.class, "HMWasher");
		if (BASEMOD.MANAGER.enableChunkloader)
		{
			GameRegistry.registerTileEntity(HawkTileEntityChunkloader.class, "HMChunkloader");
			
		}
		
	}
	
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
			case 0: return new HawkContainerCrusher(player.inventory, ((HawkTileEntityCrusher)tileEntity));
			case 1: return new HawkContainerWasher(player.inventory, ((HawkTileEntityWasher)tileEntity));
		}
		
		return null;
	}
	
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, NetworkManager manager)
	{
		
	}
	
	@Override
	public String connectionReceived(NetLoginHandler netHandler, NetworkManager manager)
	{
		return null;
	}
	
	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, NetworkManager manager)
	{
		
	}
	
	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, NetworkManager manager)
	{
		
	}
	
	@Override
	public void connectionClosed(NetworkManager manager)
	{
		
	}
	
	@Override
	public void clientLoggedIn(NetHandler clientHandler, NetworkManager manager, Packet1Login login)
	{
		
	}
	
}
