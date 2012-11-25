
package hawksmachinery;

import hawksmachinery.container.*;
import hawksmachinery.tileentity.*;
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
	
	public void addVanillaLangHandlers(){}
	
	public void registerRenderInformation()
	{
		GameRegistry.registerTileEntity(HMTileEntityCrusher.class, "HMCrusher");
		GameRegistry.registerTileEntity(HMTileEntityWasher.class, "HMWasher");
		GameRegistry.registerTileEntity(HMTileEntityEndiumChunkloader.class, "HMChunkloader");
		GameRegistry.registerTileEntity(HMTileEntityTeleporter.class, "HMTeleporter");
		GameRegistry.registerTileEntity(HMTileEntityFisher.class, "HMFisher");
		GameRegistry.registerTileEntity(HMTileEntityStarForge.class, "HMStarForge");
		GameRegistry.registerTileEntity(HMTileEntityFireBlock.class, "HMFireBlock");
		
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
			case 3: return new HMContainerFisher(player.inventory, (HMTileEntityFisher)tileEntity);
			case 4: return new HMContainerStarForge(player.inventory, ((HMTileEntityStarForge)tileEntity));
			default: return null;
			
		}
		
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
