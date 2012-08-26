package vazkii.um.common.checking;

import vazkii.um.common.UpdateManagerMod;

/**
 * Checks if the UM version is equal to the web version.
 * 
 * @author Vazkii
 */
public class CMEquals extends CheckingMethod {

	@Override
	public boolean isUpdated(UpdateManagerMod mod, String webVersion) {
		return mod.getUMVersion().equals(webVersion);
	}

}
