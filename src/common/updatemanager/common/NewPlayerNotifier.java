package updatemanager.common;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.ServerConfigurationManager;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author Vazkii, TheWhiteWolves
 */
public class NewPlayerNotifier implements IConnectionHandler {

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		MinecraftServer server = ModLoader.getMinecraftServerInstance();
		ServerConfigurationManager configManager = UpdateManager.getServerConfig(server);
		
		if(!Settings.getBoolean("opOnly") || configManager.areCommandsAllowed(((EntityPlayer)player).username)) {
			
			server.logger.log(Level.INFO, "Warning");
    		UpdateManager.sendChatMessageToPlayer((EntityPlayer)player, "Welcome back! Use /um to see Update Manager Commands.");

    	if(UpdateManager.canAlertPlayer((EntityPlayer)player, configManager) && !UpdateManager.areModsUpdated())
    		UpdateManager.warnUserOfOutdated(((EntityPlayer)player).username);
		}
	}

	@Override public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) { return null; };
	@Override public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {}
	@Override public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {}
	@Override public void connectionClosed(INetworkManager manager) {}
	@Override public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {}
}
