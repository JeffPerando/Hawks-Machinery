package vazkii.um.common.checking;

import vazkii.um.common.UpdateManagerMod;

/**
 * Returns that the mod is updated if the length of the web version isn't larger than the UM version
 * 
 * @author Vazkii
 */
public class CMLengthWise extends CheckingMethod {

	@Override
	public boolean isUpdated(UpdateManagerMod mod, String webVersion) {
		return webVersion.length() < mod.getUMVersion().length();
	}

}
