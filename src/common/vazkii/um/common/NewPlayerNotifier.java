package vazkii.um.common;

import java.util.logging.Level;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.ServerConfigurationManager;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author Vazkii
 */
public class NewPlayerNotifier implements IConnectionHandler {

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, NetworkManager manager) {
		MinecraftServer server = ModLoader.getMinecraftServerInstance();
		ServerConfigurationManager configManager = UpdateManager.getServerConfig(server);

		if (!Settings.getBoolean("opOnly") || configManager.areCommandsAllowed(((EntityPlayer) player).username)) {

			MinecraftServer.logger.log(Level.INFO, "Warning");
			UpdateManager.sendChatMessageToPlayer((EntityPlayer) player, "�9Welcome back! Use �e/um�9 to see Update Manager Commands.");

			if (UpdateManager.canAlertPlayer((EntityPlayer) player, configManager) && !UpdateManager.areModsUpdated()) UpdateManager.warnUserOfOutdated(((EntityPlayer) player).username);
		}
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, NetworkManager manager) {
		return null;
	};

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, NetworkManager manager) {}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, NetworkManager manager) {}

	@Override
	public void connectionClosed(NetworkManager manager) {}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, NetworkManager manager, Packet1Login login) {}
}
