package vazkii.um.common.commands;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandBase;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ServerConfigurationManager;
import vazkii.um.common.Settings;
import vazkii.um.common.UpdateManager;

/**
 * @author Vazkii
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

		if (var1.canCommandSenderUseCommand(getCommandName()) || !Settings.getBoolean("opOnly")) {
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�6    Update Manager Commands:");
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�e/um-force: �9Forces an Update Check.");
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�e/um-delay <time>: �9Sets the time between checks.");
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�e/um-op: �9Changes if Update Checks should only be notified to OPs.");
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�e/um-login: �9Changes if warns should be delivered to players logging in.");
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�e/um-reset: �9Resets the Settings.");
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�e/um-disable: �9Disables Update Manager.");
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�e/um-settings: �9Views the current Settings.");
		}
	}
}
