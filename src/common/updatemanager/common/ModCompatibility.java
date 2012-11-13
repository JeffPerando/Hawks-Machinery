package updatemanager.common;

import updatemanager.client.ModType;
import net.minecraft.src.BaseMod;
import net.minecraftforge.common.ForgeVersion;

/**
 * This class is dedicated to add compatibility with other mods.
 * 
 * @author Vazkii, TheWhiteWolves
 */
public final class ModCompatibility {

	static boolean inited = false;

	public ModCompatibility() {
		if (!inited) inited = true;
		new Forge();
	}

	public final class Forge extends UpdateManagerMod {

		public Forge() {
			super(new DummyMod(String.format("%s.%s.%s.%s", ForgeVersion.majorVersion, ForgeVersion.minorVersion, ForgeVersion.revisionVersion, ForgeVersion.buildVersion)));
		}

		@Override
		public String getModURL() {
			return "http://minecraftforge.net/forum/index.php/topic,5.0.html";
		}

		@Override
		public String getUpdateURL() {
			return "https://dl.dropbox.com/u/43671482/Update%20Manager/Forge%20Recommended.txt";
		}

		@Override
		public ModType getModType() {
			return ModType.API;
		}

		@Override
		public String getModName() {
			return "Minecraft Forge";
		}

		@Override
		public String[] addNotes() {
			return new String[] { "The version being checked for is the reccommended version rather", "than the latest version." };
		}

	}

	public static final class DummyMod extends BaseMod {

		String version;

		public DummyMod(String version) {
			this.version = version;
		}

		@Override
		public String getVersion() {
			return version;
		}

		@Override
		public void load() {}

	}

}
