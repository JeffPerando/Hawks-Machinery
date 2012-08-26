package vazkii.um.common;

import java.awt.Dimension;

import net.minecraft.client.Minecraft;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderItem;

import org.lwjgl.opengl.GL11;

import vazkii.um.client.GuiModList;
import vazkii.um.client.ModReleaseType;
import vazkii.um.client.ModType;
import vazkii.um.common.checking.CheckingMethod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

/**
 * The class meant to be used for Update Manager handlers. By convenience, used as an inner class, but you can do whatever flips your vote.
 * 
 * @author Vazkii
 */
public abstract class UpdateManagerMod {

	/**
	 * The mod associated to this.
	 */
	public BaseMod asocMod;

	/**
	 * @param m The mod to register on this.
	 */
	public UpdateManagerMod(Mod m) {
		this(ModConverter.toBaseMod(m));
	}

	/**
	 * @param m The mod to register on this.
	 */
	public UpdateManagerMod(BaseMod m) {
		if (m == null || !UpdateManager.registerMod(this)) throw new IllegalArgumentException("Failed to register mod.");
		asocMod = m;
	}

	/**
	 * Gets the URL for this mod that's linked to it's webpage.
	 */
	@SideOnly(Side.CLIENT)
	public abstract String getModURL();

	/**
	 * Gets the URL for the mod that gets checked for the version.
	 */
	public abstract String getUpdateURL();

	/**
	 * Gets the type of mod this mod is.
	 * 
	 * @see ModType
	 */
	@SideOnly(Side.CLIENT)
	public ModType getModType() {
		return ModType.UNDEFINED;
	}

	/**
	 * Gets the name of the mod, this is used in the mod list GUI to differentiate your mod from others. If this is missing, it uses the class name.
	 */
	public String getModName() {
		return asocMod.getClass().getSimpleName();
	}

	/**
	 * Gets the URL for the mod's changelog.
	 */
	@SideOnly(Side.CLIENT)
	public String getChangelogURL() {
		return null;
	}

	/**
	 * Gets the URL for a direct download for the mod's most recent version, note that the URL links to a file with another URL which actually links to the download URL, this is to more easilly change the files.
	 */
	@SideOnly(Side.CLIENT)
	public String getDirectDownloadURL() {
		return null;
	}

	/**
	 * Gets the URL for a disclaimer file that gets displayed when a user downloads the mod, the first line of the file gets displayed in big red text. If the file is empty the disclaimer will be skipped and the download will just start.
	 */
	@SideOnly(Side.CLIENT)
	public String getDisclaimerURL() {
		return null;
	}

	/**
	 * Enables automatic downloads, if you set this to true and the Direct Download URL is null, you will recieve a NullPointerException, be wary.
	 */
	@SideOnly(Side.CLIENT)
	public boolean enableAutoDownload() {
		return getDirectDownloadURL() != null && getDisclaimerURL() == null && !UpdateManager.isModUpdated(this) && !UpdateManager.alreadyDownloadedMods.contains(this);
	}

	/**
	 * This is used to get a version independent of the BaseMod.getVersion() method for UM only, if it's missing it uses BaseMod.getVersion() instead or the getRelease() method if it's present.
	 */
	public String getUMVersion() {
		return asocMod.getVersion();
	}

	/**
	 * Gets the Checking Method for this mod.
	 * 
	 * @see CheckingMethod
	 */
	public CheckingMethod getCheckingMethod() {
		return CheckingMethod.NUMERICAL;
	}

	/**
	 * Used to disable checks for this mod, this can be useful for beta builds and the like.
	 */
	public boolean disableChecks() {
		return FMLCommonHandler.instance().getSide().isClient() ? getModType().debugOnly() && !UpdateManager.isDebug || !getReleaseType().hasChecks() : false;
	}

	/**
	 * Used to make a mod only check for updates if being ran in debug mode.
	 * 
	 * @see <a href="http://help.eclipse.org/helios/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftasks-debug-launch.htm">Eclipse debug Help page</a>
	 */
	public boolean srcOnly() {
		return false;
	}

	/**
	 * Gets the Release Type of the mod
	 * 
	 * @see ModReleaseType
	 */
	@SideOnly(Side.CLIENT)
	public ModReleaseType getReleaseType() {
		return ModReleaseType.RELEASED;
	}

	/**
	 * Used to add notes to the update manager inteface.
	 * 
	 * @return An array of note lines to write in the interface when this mod is chosen.
	 */
	@SideOnly(Side.CLIENT)
	public String[] addNotes() {
		return null;
	}

	/**
	 * If you have notes enabled (addNotes() isn't null), you can change the headliner for the notes, this can be used for example, to deliver a newsletter trough the notes, or something of the like.
	 * 
	 * @return The Headliner for the notes section.
	 */
	@SideOnly(Side.CLIENT)
	public String getNotesHeadliner() {
		return "Notes:";
	}

	@SideOnly(Side.CLIENT)
	public ItemStack getIconStack() {
		return null;
	}

	/**
	 * Renders an Icon representing this mod into the GUI. Defaults to rendering the ItemStack defined by getIconStack(), if that is null, does nothing.
	 * 
	 * @param x The x coord to render on screen.
	 * @param y The y coord to render on screen.
	 * @param modList The GUI where this is going to be rendered.
	 * @return A Dimension representing the size of this icon, for ofsetting the mod name and other text.
	 */
	@SideOnly(Side.CLIENT)
	public Dimension renderIcon(int x, int y, GuiModList modList) {
		ItemStack icon = getIconStack();

		x /= 2;
		y /= 2;
		x -= 2;

		if (icon == null) return new Dimension();

		boolean block = icon.getItem() instanceof ItemBlock;

		GL11.glPushMatrix();
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		if (!block) RenderHelper.enableStandardItemLighting();
		Minecraft mc = ModLoader.getMinecraftInstance();
		RenderItem renderItem = new RenderItem();
		renderItem.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, icon, x, y);
		if (!block) RenderHelper.disableStandardItemLighting();
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();

		return new Dimension(32, 32);
	}

	/**
	 * Gets the name for the special button for this mod.
	 * 
	 * @return The string displayed in the button, null if you don't want a special button.
	 */
	public String getSpecialButtonName() {
		return null;
	}

	/**
	 * What happens when the special button is clicked? Special buttons are enabled by setting getSpecialButtonName() to something other than null.
	 */
	public void onSpecialButtonClicked() {}

	/**
	 * This method is used to check for the correct object to check for when version checking, be that a Integer or a String. Deprecated because of the removal of releases.
	 * 
	 * @see Integer
	 * @see String
	 */
	@Deprecated
	public final Object getObjectToCheck() {
		return getUMVersion();
	}

}
