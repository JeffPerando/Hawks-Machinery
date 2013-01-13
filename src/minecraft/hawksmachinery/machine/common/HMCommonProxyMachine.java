
package hawksmachinery.machine.common;

import hawksmachinery.core.common.block.HMBlock;
import hawksmachinery.core.common.item.HMItemBlockEndium;
import hawksmachinery.core.common.item.HMItemBlockMetalStorage;
import hawksmachinery.core.common.item.HMItemBlockOre;
import hawksmachinery.core.common.tileentity.HMTileEntityMulti;
import hawksmachinery.machine.common.container.*;
import hawksmachinery.machine.common.item.*;
import hawksmachinery.machine.common.tileentity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCommonProxyMachine implements IGuiHandler, IConnectionHandler
{
	public void registerRenderInformation(){}
	
	public int getHMRenderID()
	{
		return 0;
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
			case 0: return new HMContainerCrusher(player.inventory, (HMTileEntityCrusher)tileEntity);
			case 1: return new HMContainerWasher(player.inventory, (HMTileEntityWasher)tileEntity);
			//case 2: return new HMContainerTeleporter(player.inventory);
			case 3: return new HMContainerFisher(player.inventory, (HMTileEntityFisher)tileEntity);
			case 4: return new HMContainerStarForge(player.inventory, (HMTileEntityStarForge)tileEntity);
			case 5: return new HMContainerSinterer(player.inventory, (HMTileEntitySinterer)tileEntity);
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
