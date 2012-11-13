package updatemanager.common.commands;

import java.util.logging.Level;

import updatemanager.common.Settings;
import updatemanager.common.UpdateManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandBase;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ServerConfigurationManager;

/**
 * @author Vazkii, TheWhiteWolves
 */
public class CommandUM extends CommandBase {

	@Override
	public String getCommandName() {
		return "um";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		MinecraftServer server = ModLoader.getMinecraftServerInstance();
		ServerConfigurationManager manager = UpdateManager.getServerConfig(server);
		
		if(var1.canCommandSenderUseCommand(0, getCommandName()) || !Settings.getBoolean("opOnly")){
    		UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "    Update Manager Commands:");
    		UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "/um-force: Forces an Update Check.");
    		UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "/um-delay <time>: Sets the time between checks.");
    		UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "/um-op: Changes if Update Checks should only be notified to OPs.");
    		UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "/um-login: Changes if warns should be delivered to players logging in.");
    		UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "/um-reset: Resets the Settings.");
    		UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "/um-disable: Disables Update Manager.");
    		UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "/um-settings: Views the current Settings.");
		}
	}
}
