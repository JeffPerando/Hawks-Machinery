package vazkii.um.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

/**
 * @author Vazkii
 */
public final class Settings {

	static File settingsFile;

	static NBTTagCompound settingsCompound;
	static boolean inited;

	public static void init() {
		if (inited) return;
		inited = true;

		settingsFile = isServer() ? new File("updateManagerSettingsServer.dat") : new File(Minecraft.getAppDir("minecraft"), "updateManagerSettings.dat");
		try {
			if (!settingsFile.exists()) {
				settingsFile.createNewFile();
				settingsCompound = new NBTTagCompound();

				try {
					initClientSettings();
				} catch (NoSuchMethodError e) {}

				try {
					initServerSettings();
				} catch (NoSuchMethodError e) {}

				CompressedStreamTools.writeCompressed(settingsCompound, new FileOutputStream(settingsFile));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				settingsCompound = CompressedStreamTools.readCompressed(new FileInputStream(settingsFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isServer() {
		try {
			ModLoader.getMinecraftInstance();
			return false;
		} catch (Throwable t) {
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	public static void initClientSettings() {
		System.out.println("Initing Client Settings");
		setBoolean("autoDownload", false);
		setBoolean("smpEnable", false);
		setInt("checkDelay", 900);
	}

	@SideOnly(Side.SERVER)
	public static void initServerSettings() {
		System.out.println("Initing Server Settings");
		setBoolean("loginCheck", true);
		setBoolean("opOnly", true);
		setInt("checkDelay", 900);
	}

	/**
	 * Valid Booleans: autoDownload, smpEnable
	 */
	public static boolean getBoolean(String b) {
		if (settingsCompound == null || !settingsCompound.hasKey(b)) return false;
		else return settingsCompound.getBoolean(b);
	}

	/**
	 * Valid Integers: checkDelay
	 */
	public static int getInt(String i) {
		if (settingsCompound == null || !settingsCompound.hasKey(i)) return 0;
		else return settingsCompound.getInteger(i);
	}

	/**
	 * Valid Booleans: autoDownload, smpEnable
	 */
	public static void setBoolean(String s, boolean b) {
		if (settingsCompound != null) settingsCompound.setBoolean(s, b);
		try {
			CompressedStreamTools.writeCompressed(settingsCompound, new FileOutputStream(settingsFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Valid Integers: checkDelay
	 */
	public static void setInt(String s, int i) {
		if (settingsCompound != null) settingsCompound.setInteger(s, i);
		try {
			CompressedStreamTools.writeCompressed(settingsCompound, new FileOutputStream(settingsFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
