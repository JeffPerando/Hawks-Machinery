package vazkii.um.common;

import java.io.File;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import vazkii.um.client.CapeHandler;
import vazkii.um.client.ModType;
import vazkii.um.client.UMKeyBinding;
import vazkii.um.common.commands.UpdateManagerCommands;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * @author Vazkii
 */
@Mod(modid = "UpdateManager-Vazkii", name = "Mod Update Manager", version = "by Vazkii. Version 2.3") public class mod_UpdateManager {

	public static final String[] langs = new String[] { "pt_PT", "es_ES", "fr_FR", "de_DE" };

	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event) {
		vazkii.um.common.Settings.init();

		boolean client = FMLCommonHandler.instance().getSide().isClient();

		if (client) {
			CapeHandler.initCapes();
			FMLClientHandler.instance().getClient();
			File f = Minecraft.getAppDir("minecraft/downloadedMods");
			f.mkdirs();
		}

		UpdateManager.loadMods();
		UpdateManager.dumpMods();

		if (client) new ThreadUpdateManager(FMLClientHandler.instance().getClient());
		else new ThreadUpdateManager(FMLCommonHandler.instance().getMinecraftServerInstance());
	}

	@Init
	public void load(FMLInitializationEvent event) {
		new vazkii.um.common.ModCompatibility();
		new UpdateHandler(ModConverter.getMod(getClass()));

		try {
			clientLoad();
		} catch (NoSuchMethodError e) {}
		try {
			serverLoad();
		} catch (NoSuchMethodError e) {}

		ModLoader.addLocalization("um.updated", "�9[Mod Update Manager] �aYour mods are up to date!");
		ModLoader.addLocalization("um.outdated", "�9[Mod Update Manager]�c You have outdated mods, open your mod list to see them.");
		ModLoader.addLocalization("um.offline", "�9[Mod Update Manager] �aYou are offline, couldn't check.");
		ModLoader.addLocalization("um.updated.pt_PT", "�9[Mod Update Manager] �aOs teus mods est�o atualizados!");
		ModLoader.addLocalization("um.outdated.pt_PT", "�9[Mod Update Manager] �cTens mods desatualizados, abre a tua lista de mods para os ver.");
		ModLoader.addLocalization("um.outdated.pt_PT", "�9[Mod Update Manager] �aEst�s offline, a verifica��o n�o foi possivel.");
		ModLoader.addLocalization("um.updated.es_ES", "�9[Mod Update Manager] �aTus mods est�n actualizadas!");
		ModLoader.addLocalization("um.outdated.es_ES", "�9[Mod Update Manager] �cTienes mods desactualizados, abrir tu lista de mods para verlos.");
		ModLoader.addLocalization("um.offline.es_ES", "�9[Mod Update Manager] �aEst�s offline, no pudo comprobar.");
		ModLoader.addLocalization("um.updated.fr_FR", "�9[Mod Update Manager] �a Vos mods sont � jours!");
		ModLoader.addLocalization("um.outdated.fr_FR", "�9[Mod Update Manager] �cCertains de vos mods ne sont pas � jours, ouvrez votre liste de mods pour les voir.");
		ModLoader.addLocalization("um.offline.fr_FR", "�9[Mod Update Manager] �cVous �tes d�connect�, ne peut pas v�rifier.");
		ModLoader.addLocalization("um.updated.ge_GE", "�9[Mod Update Manager] �a�berwachte Mods sind aktuell!");
		ModLoader.addLocalization("um.outdated.ge_GE", "�9[Mod Update Manager] �cVeraltete Mods gefunden, f�r Details siehe Liste.");
		ModLoader.addLocalization("um.offline.ge_GE", "Nicht online, Pr�fung nicht m�glich.");
		ModLoader.addLocalization("um.updated.de_DE", "�9[Mod Update Manager] �aJe mods zijn up-to-date!");
		ModLoader.addLocalization("um.outdated.de_DE", "�9[Mod Update Manager] �cJe hebt verouderde mods, open je mod lijst om deze te zien.");
		ModLoader.addLocalization("um.offline.de_DE", "�9[Mod Update Manager] �cJe bent offline, kon niet controleren.");
	}

	@SideOnly(Side.CLIENT)
	public void clientLoad() {
		KeyBindingRegistry.registerKeyBinding(new UMKeyBinding());
	}

	@SideOnly(Side.SERVER)
	public void serverLoad() {
		NetworkRegistry.instance().registerConnectionHandler(new NewPlayerNotifier());
		UpdateManagerCommands.init();
	}

	public static String localize(String string, String locale) {
		if (Arrays.asList(langs).contains(locale)) return string + "." + locale;
		return string;
	}

	public class UpdateHandler extends UpdateManagerMod {

		public UpdateHandler(Mod m) {
			super(m);
		}

		@Override
		public String getModURL() {
			return "http://www.minecraftforum.net/topic/1243564-any-version-mod-update-manager-last-updated-22512/";
		}

		@Override
		public String getUpdateURL() {
			return "http://dl.dropbox.com/u/34938401/Update%20Manager/Update%20Manager%20Version.txt";
		}

		@Override
		public String getUMVersion() {
			return "2.3";
		}

		@Override
		public ModType getModType() {
			return ModType.SRC;
		}

		@Override
		public String getModName() {
			return "Mod Update Manager";
		}

		@Override
		public String getChangelogURL() {
			return "http://dl.dropbox.com/u/34938401/Update%20Manager/Ingame%20Changelog%20UM2.txt";
		}

		@Override
		public String getDirectDownloadURL() {
			return "http://dl.dropbox.com/u/34938401/Update%20Manager/Direct%20Download.txt";
		}

		@Override
		public String getDisclaimerURL() {
			return "http://dl.dropbox.com/u/34938401/Update%20Manager/Disclaimer.txt";
		}

		@Override
		public String getSpecialButtonName() {
			return "Twitter";
		}

		@Override
		public void onSpecialButtonClicked() {
			UpdateManager.openWebpage("https://twitter.com/Vazkii");
		}

		@Override
		public ItemStack getIconStack() {
			return new ItemStack(Item.writableBook);
		}

		@Override
		public String[] addNotes() {
			return UpdateManager.isDebug ? null : new String[] { "Update Manager is a source only mod, which means all", "updating of the mod is in the modder's hands, not the user's.", };
		}
	}
}
