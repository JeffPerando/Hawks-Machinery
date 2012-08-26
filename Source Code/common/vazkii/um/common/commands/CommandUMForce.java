package vazkii.um.common.commands;

import java.util.logging.Level;

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
public class CommandUMForce extends CommandBase {

	@Override
	public String getCommandName() {
		return "um-force";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		MinecraftServer server = ModLoader.getMinecraftServerInstance();
		ServerConfigurationManager manager = UpdateManager.getServerConfig(server);

		if (var1.canCommandSenderUseCommand(getCommandName()) || !Settings.getBoolean("opOnly")) {
			UpdateManager.loadMods();
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "§9Update Check has been forced!");
			MinecraftServer.logger.log(Level.INFO, "[Mod Update Manager] Update Check has been forced!");

			if (!UpdateManager.areModsUpdated()) UpdateManager.warnUserOfOutdated(var1.getCommandSenderName());
		}

	}

}
