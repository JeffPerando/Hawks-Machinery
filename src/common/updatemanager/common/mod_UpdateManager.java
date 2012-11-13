package updatemanager.common;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.ModLoader;
import net.minecraft.src.Block;

import org.lwjgl.input.Keyboard;

import updatemanager.client.CapeHandler;
import updatemanager.client.GuiModListWithUMButton;
import updatemanager.client.ModReleaseType;
import updatemanager.client.ModType;
import updatemanager.client.UMKeyBinding;
import updatemanager.common.Settings;
import updatemanager.common.ThreadUpdateManager;
import updatemanager.common.UpdateManager;
import updatemanager.common.UpdateManagerMod;
import updatemanager.common.commands.UpdateManagerCommands;
import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

/**
 * @author Vazkii, TheWhiteWolves
 */
@Mod(modid = "UpdateManager_TheWhiteWolves", name = "Mod Update Manager", version = "v3.0")
public class mod_UpdateManager{
	
	public static final String[] langs = new String[]{
		"pt_PT", "es_ES", "fr_FR", "de_DE"
	};
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event) {
		updatemanager.common.Settings.init();

		boolean client = FMLCommonHandler.instance().getSide().isClient();
		
		if(client){
			CapeHandler.initCapes();
			File f = ModLoader.getMinecraftInstance().getAppDir("minecraft/downloadedMods");
			f.mkdirs();
		}
		
		UpdateManager.loadMods();
		UpdateManager.dumpMods();

		if(client)
			new ThreadUpdateManager(ModLoader.getMinecraftInstance());
		else new ThreadUpdateManager(ModLoader.getMinecraftServerInstance());
	}

	@Init
	public void load(FMLInitializationEvent event) {
		new updatemanager.common.ModCompatibility();
		new UpdateHandler(ModConverter.getMod(getClass()));

		try {
			clientLoad();
		} catch (NoSuchMethodError e) {}
		try {
			serverLoad();
		} catch (NoSuchMethodError e) {}
		
		ModLoader.addLocalization("um.updated", "[Mod Update Manager] Your mods are up to date!");
		ModLoader.addLocalization("um.outdated", "[Mod Update Manager] You have outdated mods, open your mod list to see them.");
		ModLoader.addLocalization("um.offline", "[Mod Update Manager] You are offline, couldn't check.");
		ModLoader.addLocalization("um.updated.pt_PT", "[Mod Update Manager] Os teus mods estão atualizados!");
		ModLoader.addLocalization("um.outdated.pt_PT", "[Mod Update Manager] Tens mods desatualizados, abre a tua lista de mods para os ver.");
		ModLoader.addLocalization("um.outdated.pt_PT", "[Mod Update Manager] Estás offline, a verificação não foi possivel.");
		ModLoader.addLocalization("um.updated.es_ES", "[Mod Update Manager] Tus mods están actualizadas!");
		ModLoader.addLocalization("um.outdated.es_ES", "[Mod Update Manager] Tienes mods desactualizados, abrir tu lista de mods para verlos.");
		ModLoader.addLocalization("um.offline.es_ES", "[Mod Update Manager] Estás offline, no pudo comprobar.");
		ModLoader.addLocalization("um.updated.fr_FR", "[Mod Update Manager] Vos mods sont à jours!");
		ModLoader.addLocalization("um.outdated.fr_FR", "[Mod Update Manager] Certains de vos mods ne sont pas à jours, ouvrez votre liste de mods pour les voir.");
		ModLoader.addLocalization("um.offline.fr_FR", "[Mod Update Manager] Vous êtes déconnecté, ne peut pas vérifier.");
		ModLoader.addLocalization("um.updated.ge_GE", "[Mod Update Manager] Überwachte Mods sind aktuell!");
		ModLoader.addLocalization("um.outdated.ge_GE", "[Mod Update Manager] Veraltete Mods gefunden, für Details siehe Liste.");
		ModLoader.addLocalization("um.offline.ge_GE", "Nicht online, Prüfung nicht möglich.");
		ModLoader.addLocalization("um.updated.de_DE", "[Mod Update Manager] Je mods zijn up-to-date!");
		ModLoader.addLocalization("um.outdated.de_DE", "[Mod Update Manager] Je hebt verouderde mods, open je mod lijst om deze te zien.");
		ModLoader.addLocalization("um.offline.de_DE", "[Mod Update Manager] Je bent offline, kon niet controleren.");
	}
	
	@SideOnly(Side.CLIENT)
	public void clientLoad(){
		KeyBindingRegistry.registerKeyBinding(new UMKeyBinding());
	}
	
	@SideOnly(Side.SERVER)
	public void serverLoad(){
		NetworkRegistry.instance().registerConnectionHandler(new NewPlayerNotifier());
		UpdateManagerCommands.init();
	}
	
	public static String localize(String string, String locale){
		if(Arrays.asList(langs).contains(locale))
			return string + "." + locale;
		return string;
	}
	
	public class UpdateHandler extends UpdateManagerMod {

		public UpdateHandler(Mod m) {
			super(m);
		}

		public String getModURL() {
			return "http://www.minecraftforum.net/topic/1541473-142-mod-update-manager-v30/";
		}
		
		public String getUpdateURL() {
			return "https://dl.dropbox.com/u/43671482/Update%20Manager/Update%20Manager%20Version.txt";
		}
		
		public String getUMVersion(){
			return "3.0";
		}
		
		public  ModType getModType(){
			return ModType.SRC;
		}
		
		public String getModName(){
			return "Mod Update Manager";
		}
		
		public String getChangelogURL(){
			return "https://dl.dropbox.com/u/43671482/Update%20Manager/Ingame%20Changelog%20UM2.txt";
		}
		
		public String getDirectDownloadURL(){
			return "https://dl.dropbox.com/u/43671482/Update%20Manager/Direct%20Download.txt";
		}
		
		public String getDisclaimerURL(){
			return "https://dl.dropbox.com/u/43671482/Update%20Manager/Disclaimer.txt";
		}
		
		public String getSpecialButtonName(){
			return "Twitter";
		}
		
		public void onSpecialButtonClicked(){
			UpdateManager.openWebpage("https://twitter.com/jonbomb91");
		}
		
		public ItemStack getIconStack(){
			return new ItemStack(Item.writableBook);
		}
		
		public String[] addNotes(){
			return UpdateManager.isDebug ? null :
			new String[]{
					"Update Manager is a source only mod, which means all",
					"updating of the mod is in the modder's hands, not the user's.",
			};
		}
	}
}
