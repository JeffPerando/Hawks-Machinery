package updatemanager.client;

import java.util.Arrays;
import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiMainMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

/**
 * @author Vazkii, TheWhiteWolves
 */
public class UMKeyBinding extends KeyHandler {

	public static KeyBinding key = new KeyBinding("Mod List", Keyboard.KEY_M);
	
	public UMKeyBinding() {
		super(new KeyBinding[] { key }, new boolean[] { false });
	}

	@Override
	public String getLabel() {
		return "Mod Update Manager";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		if(types.equals(EnumSet.of(TickType.CLIENT)) && kb.keyCode == key.keyCode && (mc.currentScreen == null || mc.currentScreen instanceof GuiMainMenu || mc.currentScreen instanceof cpw.mods.fml.client.GuiModList))
			ModLoader.openGUI(ModLoader.getMinecraftInstance().thePlayer, new GuiModListWithUMButton(ModLoader.getMinecraftInstance().currentScreen));
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
