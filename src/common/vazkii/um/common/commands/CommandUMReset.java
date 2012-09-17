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
public class CommandUMReset extends CommandBase {

	@Override
	public String getCommandName() {
		return "um-reset";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		MinecraftServer server = ModLoader.getMinecraftServerInstance();
		ServerConfigurationManager manager = UpdateManager.getServerConfig(server);

		if (var1.canCommandSenderUseCommand(getCommandName()) || !Settings.getBoolean("opOnly")) {
			Settings.setBoolean("loginCheck", true);
			Settings.setBoolean("opOnly", true);
			Settings.setInt("checkDelay", 900);
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "§9Settings reset!");
		}
	}

}
