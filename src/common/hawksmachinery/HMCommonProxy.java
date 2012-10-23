
package hawksmachinery;

import hawksmachinery.tileentity.HMTileEntityEndiumChunkloader;
import hawksmachinery.tileentity.HMTileEntityCrusher;
import hawksmachinery.tileentity.HMTileEntityWasher;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.INetworkManager;
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
		GameRegistry.registerTileEntity(HMTileEntityCrusher.class, "HMCrusher");
		GameRegistry.registerTileEntity(HMTileEntityWasher.class, "HMWasher");
		if (BASEMOD.MANAGER.enableChunkloader)
		{
			GameRegistry.registerTileEntity(HMTileEntityEndiumChunkloader.class, "HMChunkloader");
			
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
			case 0: return new HMContainerCrusher(player.inventory, ((HMTileEntityCrusher)tileEntity));
			case 1: return new HMContainerWasher(player.inventory, ((HMTileEntityWasher)tileEntity));
			case 2: return new HMContainerTeleporter(player.inventory);
			
		}
		
		return null;
	}
	
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager)
	{
		
	}
	
	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager)
	{
		return null;
	}
	
	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager)
	{
		
	}
	
	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager)
	{
		
	}
	
	@Override
	public void connectionClosed(INetworkManager manager)
	{
		
	}
	
	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login)
	{
		
	}
	
}
