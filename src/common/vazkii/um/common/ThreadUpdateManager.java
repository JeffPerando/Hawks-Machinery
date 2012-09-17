package vazkii.um.common;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

/**
 * @author Vazkii
 */
public class ThreadUpdateManager extends Thread {

	Minecraft mc;
	MinecraftServer ms;

	boolean firstRun;

	boolean firstTickUpdate;

	public ThreadUpdateManager(Object object) {
		if (object instanceof MinecraftServer) ms = (MinecraftServer)object;
		else mc = (Minecraft)object;

		setName("Update Manager Thread");
		firstRun = true;
		setDaemon(true);
		if (UpdateManager.initThread(this)) start();
		else try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (mc != null ? mc.running : true) {
				int sleepTime = Settings.getInt("checkDelay");

				if (sleepTime <= 0) {
					System.out.println("[Mod Update Manager] Killing Thread");
					finalize();
				}

				if (mc != null) while (mc.thePlayer == null || mc.theWorld == null || !mc.isIntegratedServerRunning() && !Settings.getBoolean("smpEnable"))
					sleep(1000);

				try {
					clientRun();
				} catch (NoSuchMethodError e) {}
				try {
					serverRun();
				} catch (NoSuchMethodError e) {}

				firstRun = false;
				sleep(sleepTime * 1000);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@SideOnly(Side.CLIENT)
	public void clientRun() throws Throwable {

		System.out.println("[Mod Update Manager] Thread executed check.");
		String lang = mc.gameSettings.language;
		if (firstRun) if (UpdateManager.online) {
			if (firstTickUpdate = UpdateManager.areModsUpdated()) mc.thePlayer.addChatMessage(UpdateManager.areModsUpdated() ? mod_UpdateManager.localize("um.updated", lang) : mod_UpdateManager.localize("um.outdated", lang));
			else mc.thePlayer.addChatMessage(mod_UpdateManager.localize("um.outdated", lang));
		}
		else mc.thePlayer.addChatMessage(mod_UpdateManager.localize("um.offline", lang));
		else {
			UpdateManager.loadMods();
			if (!UpdateManager.areModsUpdated() && firstTickUpdate) mc.thePlayer.addChatMessage(mod_UpdateManager.localize("um.outdated", lang));
		}
	}

	@SideOnly(Side.SERVER)
	public void serverRun() throws Throwable {
		if (UpdateManager.online) {
			UpdateManager.loadMods();
			if (!UpdateManager.areModsUpdated()) {
				ms.logWarningMessage("[Mod Update Manager] Detected Outdated Mods.");
				UpdateManager.warnUsersOfOutdated(true);
			}
		}
	}
}
