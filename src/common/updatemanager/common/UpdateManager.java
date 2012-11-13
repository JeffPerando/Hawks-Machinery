package updatemanager.common;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.relauncher.ReflectionHelper;

import updatemanager.client.ModType;
import updatemanager.client.ThreadDownloadMod;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ModLoader;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.ServerConfigurationManager;

/**
 * The main Update Manager class, you shouldn't have to call methods from here.
 * 
 * @author Vazkii, TheWhiteWolves
 */
public class UpdateManager {

	/**
	 * Used to check if the program is being ran in debug mode.
	 * 
	 * @see <a href="http://help.eclipse.org/helios/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftasks-debug-launch.htm">Eclipse debug Help page</a>
	 */
	public static final boolean isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;

	/**
	 * The HashMap that keeps the loaded mods and if they are updated.
	 */
	public static HashMap<UpdateManagerMod, Boolean> loadedModsMap = new HashMap();

	/**
	 * The Set that keeps the mods loaded, mostly used for iterating.
	 */
	public static Set<UpdateManagerMod> loadedModsSet = new LinkedHashSet();

	/**
	 * The mods that were already auto-downloaded.
	 */
	protected static List<UpdateManagerMod> alreadyDownloadedMods = new LinkedList();

	/**
	 * The webpage for the Update Manager forum topic.
	 */
	public static final String umWebpage = "http://bit.ly/modUpdateManager";

	/**
	 * Checks if the client is online.
	 */
	public static final boolean online = isOnline();

	/**
	 * Registers a mod.
	 * 
	 * @return If the mod was sucessfully registered.
	 */
	public static boolean registerMod(UpdateManagerMod mod) {
		return loadedModsSet.add(mod);
	}

	/**
	 * Loads all of the mods and checks if they are updated.
	 */
	public static void loadMods() {
		loadedModsMap.clear();
		knownWebVersions.clear();
		for (UpdateManagerMod m : loadedModsSet)
			loadMod(m);
	}

	private static void loadMod(UpdateManagerMod m) {
		if (!online || m.disableChecks()) {
			loadedModsMap.put(m, true);
			return;
		}

		loadedModsMap.put(m, m.getCheckingMethod().isUpdated(m, getWebVersionFor(m)));

		if (Settings.getBoolean("autoDownload") && m.enableAutoDownload()) {
			System.out.println("[Mod Update Manager] Detected outdated mod " + m.getModName() + ", downloading...");
			new ThreadDownloadMod(m.getDirectDownloadURL(), m);
			alreadyDownloadedMods.add(m);
		}
	}

	public static boolean areModsUpdated() {
		return !loadedModsMap.containsValue(false);
	}

	public static boolean isModUpdated(UpdateManagerMod m) {
		return loadedModsMap.get(m);
	}

	/**
	 * Opens a webpage.
	 * @param url The URL of the page to open, it's not an url, since it gets converted to an URI.
	 */
	public static boolean openWebpage(String url) {
		try {
			if (Desktop.isDesktopSupported()) try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static boolean isOnline() {
		try {
			new URL("http://74.125.224.72").openConnection().connect();
			return true;
		} catch (IOException e) {
			return false;
		}

	}

	// HashMap containing known web versions to increase performance.
	private static HashMap<UpdateManagerMod, String> knownWebVersions = new HashMap();

	public static String getWebVersionFor(UpdateManagerMod mod) {
		if (knownWebVersions.containsKey(mod)) return knownWebVersions.get(mod);

		try {
			URL url = new URL(mod.getUpdateURL());
			BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = r.readLine();
			r.close();

			knownWebVersions.put(mod, s);
			return s;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Dumps the mods to the console;
	 */
	public static void dumpMods() {
		if (online && !loadedModsSet.isEmpty()) {
			System.out.println("#######################################################");
			System.out.println("[Mod Update Manager] Dumping mod list.");
			System.out.println();
			for (UpdateManagerMod u : loadedModsSet)
				System.out.println(u.getModName() + " : Client Version = " + u.getUMVersion() + ", Web Version = " + getWebVersionFor(u) + " (" + loadedModsMap.get(u) + ").");
			System.out.println();
			System.out.println("Finished dumping mod list, " + loadedModsSet.size() + " mods dumped.");
			System.out.println("#######################################################");
		}
	}

	/**
	 * Sorts the loaded mods.
	 * 
	 * @param modList The list of mods to sort.
	 * @param modMap The map of mods to check if the mods are updated.
	 * @return A Linked List with the mods sorted propperly.
	 */
	public static LinkedList<UpdateManagerMod> sortMods(Set<UpdateManagerMod> modSet, Map<UpdateManagerMod, Boolean> modMap) {
		LinkedList<UpdateManagerMod> outdatedApis = new LinkedList();
		LinkedList<UpdateManagerMod> outdatedOtherMods = new LinkedList();
		LinkedList<UpdateManagerMod> updatedMods = new LinkedList();
		LinkedList<UpdateManagerMod> nonCheckingMods = new LinkedList();
		LinkedList<UpdateManagerMod> totalMods = new LinkedList();

		for (UpdateManagerMod mod : loadedModsSet) {
			boolean b = loadedModsMap.get(mod).booleanValue();
			if (mod.disableChecks()) nonCheckingMods.add(mod);
			else if (b) updatedMods.add(mod);
			else if (mod.getModType() == ModType.API) outdatedApis.add(mod);
			else outdatedOtherMods.add(mod);
		}

		totalMods.addAll(outdatedApis);
		totalMods.addAll(outdatedOtherMods);
		totalMods.addAll(updatedMods);
		totalMods.addAll(nonCheckingMods);

		return totalMods;
	}
	
	private static List<UpdateManagerMod> getOutdatedMods(){
		List<UpdateManagerMod> returnList = new LinkedList();
		
		for(UpdateManagerMod mod : loadedModsSet)
			if(!loadedModsMap.get(mod))
				returnList.add(mod);
				
		return returnList;
	}

	/**
	 * Util: Get the amount of values of the certain type on a map.
	 * 
	 * @param object The value to search for.
	 * @param map The map to search on.
	 * @return The amount of entries found.
	 */
	public static int getQuantEntries(Object object, Map map) {
		Collection values = map.values();
		int foundEntries = 0;
		for (Object obj : values)
			if (obj.equals(object)) ++foundEntries;

		return foundEntries;
	}

	/**
	 * Util: checks if a thread can be initted, this is used to prevent duplicate threads.
	 */
	public static boolean initThread(Thread thread) {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

		for (Thread t : threadSet)
			if (t.getName().matches(thread.getName())) return false;

		return true;
	}
	
	/**
	 * Util, checks if an alert message can be sent to a player.
	 */
	public static boolean canAlertPlayer(EntityPlayer player, ServerConfigurationManager manager){
		return Settings.getBoolean("opOnly") ? manager.areCommandsAllowed(player.username) : true; 
	}
	
	/**
	 * Send a message to a player on the server. (Client Sided for said player's client)
	 */
	public static void sendChatMessageToPlayer(EntityPlayer player, String msg){
		Packet3Chat chatPacket = new Packet3Chat(msg);
		
		if(player != null)
			((EntityPlayerMP)player).playerNetServerHandler.sendPacketToPlayer(chatPacket);
	}
	
	public static ServerConfigurationManager getServerConfig(MinecraftServer serv){
		return ReflectionHelper.<ServerConfigurationManager, MinecraftServer>getPrivateValue(MinecraftServer.class, serv, 11);
	}
	
	public static void warnUsersOfOutdated(boolean op){
		List<UpdateManagerMod> outdatedList = getOutdatedMods();
		MinecraftServer ms = ModLoader.getMinecraftServerInstance();
		ServerConfigurationManager manager = getServerConfig(ms);
		List<EntityPlayer> players = manager.playerEntityList;
		
		for(EntityPlayer player : players){
			if(manager.areCommandsAllowed(player.username)){
				sendChatMessageToPlayer(player, "[Mod Update Manager] Your server is running outdated mods:");
				for(UpdateManagerMod mod : outdatedList)
					sendChatMessageToPlayer(player, "    - " + mod.getModName());
			}
		}
	}
	
	public static void warnUserOfOutdated(String playerName){
		List<UpdateManagerMod> outdatedList = getOutdatedMods();
		MinecraftServer ms = ModLoader.getMinecraftServerInstance();
		EntityPlayer player = getServerConfig(ms).getPlayerForUsername(playerName);

		sendChatMessageToPlayer(player, "[Mod Update Manager] Your server is running outdated mods:");
			for(UpdateManagerMod mod : outdatedList)
				sendChatMessageToPlayer(player, "    - " + mod.getModName());
	}
	

}
