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
public class CommandUMIntSetting extends CommandBase {

	String name;
	String configTag;
	String displayMsg;

	public CommandUMIntSetting(String name, String configTag, String displayMsg) {
		this.name = name;
		this.configTag = configTag;
		this.displayMsg = displayMsg;
	}

	@Override
	public String getCommandName() {
		return "um-" + name;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {

		MinecraftServer server = ModLoader.getMinecraftServerInstance();
		ServerConfigurationManager manager = UpdateManager.getServerConfig(server);

		if (var1.canCommandSenderUseCommand(getCommandName()) || !Settings.getBoolean("opOnly")) {
			int newInt = -1;
			int oldInt = Settings.getInt(configTag);
			try {
				newInt = Integer.parseInt(var2[0]);
			} catch (NumberFormatException e) {
				UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�cInvalid Number!");
			}
			if (newInt < 0) UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), "�cInvalid Number!");

			Settings.setInt(configTag, newInt);
			UpdateManager.sendChatMessageToPlayer(manager.getPlayerForUsername(var1.getCommandSenderName()), String.format(displayMsg, oldInt, newInt));
		}

	}
}
