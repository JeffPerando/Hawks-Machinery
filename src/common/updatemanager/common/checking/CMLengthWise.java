package updatemanager.common.checking;

import updatemanager.common.UpdateManagerMod;

/**
 * Returns that the mod is updated if the length of the web version isn't larger than the UM version
 * @author Vazkii, TheWhiteWolves
 */
public class CMLengthWise extends CheckingMethod {

	@Override
	public boolean isUpdated(UpdateManagerMod mod, String webVersion) {
		return webVersion.length() > mod.getUMVersion().length();
	}

}
