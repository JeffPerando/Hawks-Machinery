package updatemanager.common;

import net.minecraft.src.BaseMod;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.modloader.BaseModProxy;

/**
 * This class converts {@link Mod}s to {@link BaseMod}s, it can also get the {@link Mod} annotation from a class.
 * 
 * @author Vazkii, TheWhiteWolves
 */
public class ModConverter {

	/**
	 * Converts a {@link Mod} to {@link BaseMod}. This is used in Update Manager internals.
	 * @param mod The Mod to convert. 
	 * @return a BaseMod with the version of the Mod.
	 */
	public static BaseMod toBaseMod(final Mod mod) {
		return new BaseMod() {

			@Override
			public String getVersion() {
				return mod.version();
			}

			@Override
			public void load() {}
		};
	}
	
	/**
	 * Gets the {@link Mod} annotation out of a class.
	 * @param clazz The class to get the annotation from.
	 * @return The Mod annotation in that class.
	 */
	public static Mod getMod(Class clazz){
		return (Mod) clazz.getAnnotation(Mod.class);
	}
	
	/**
	 * Converts any object to a instance of the {@link Mod} annotation.
	 * @param object The object to convert.
	 * @return A BaseMod with the version of the Mod, null if the instance passed doesn't have the mod annotation.
	 */
	public static BaseMod toBaseMod(Object object){
		Mod mod = getMod(object.getClass());
		return mod == null ? null : toBaseMod(mod);
	}
}
