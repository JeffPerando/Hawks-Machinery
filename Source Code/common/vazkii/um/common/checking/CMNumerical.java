package vazkii.um.common.checking;

import vazkii.um.common.UpdateManagerMod;

/**
 * Splits the version in the numbers representing it, and if returns UMVersion >= webVersion.
 * 
 * @author Vazkii
 */
public class CMNumerical extends CheckingMethod {

	String numbers = "0123456789";
	String knownSplitters = "._,-/";

	@Override
	public boolean isUpdated(UpdateManagerMod mod, String webVersion) {

		String[] webVersions = webVersion.split(knownSplitters);
		String[] umVersions = mod.getUMVersion().split(knownSplitters);

		for (int i = 0; i < umVersions.length; i++)
			try {
				int umNumber = Integer.parseInt(umVersions[i]);
				int webNumber = Integer.parseInt(webVersions[i]);

				if (webNumber > umNumber) return false;

			} catch (NumberFormatException e) {} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}

		return true;
	}

}
