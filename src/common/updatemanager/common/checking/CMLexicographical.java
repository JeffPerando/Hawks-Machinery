package updatemanager.common.checking;

import updatemanager.common.UpdateManagerMod;

/**
 * Compares the versions lexicographically.
 * 
 * @author AtomicStryker, TheWhiteWolves
 */
public class CMLexicographical extends CheckingMethod {

	@Override
	public boolean isUpdated(UpdateManagerMod mod, String webVersion) {
		boolean newer = false;
		
		for (int i = 0; i < Math.min(mod.getUMVersion().length(), webVersion.length()); i++) {
			int comparedchar = webVersion.substring(i, i + 1).compareTo(mod.getUMVersion().substring(i, i + 1));

			// case: web version is higher, return false immediatly
			if (comparedchar > 0)
				return false;

			// case: local version is not only equal but higher in a digit
			if (comparedchar < 0)
				newer = true;
		}

		// if a web version is LONGER and the local version was equal up to it's
		// end, the web version must be newer
		if (webVersion.length() > mod.getUMVersion().length() && !newer) return false;

		return true;
	}

}
