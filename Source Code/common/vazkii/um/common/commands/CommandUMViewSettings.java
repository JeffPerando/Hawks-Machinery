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
public class CommandUMViewSettings extends CommandBase {

	@Override
	public String getCommandName() {
		return "um-settings";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		MinecraftServer server = ModLoader.getMinecraftServerInstance();
		ServerConfigurationManager manager = UpdateManager.getServerConfig(server);

		if (var1.canCommandSenderUseCommand(getCommandName())) UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "§bDelay Time: " + Settings.getInt("checkDelay") + " | Ops Only: " + Settings.getBoolean("opOnly") + " |  Login Warn: " + Settings.getBoolean("loginCheck") + ".");
	}

}
