package updatemanager.common.checking;

import updatemanager.common.UpdateManagerMod;

/**
 * Abstract class for checking methods to check if the mods are updated.
 * 
 * @author Vazkii, TheWhiteWolves
 */
public abstract class CheckingMethod {

	/**
	 * Checks if the UM version is equal to the web version.
	 */
	public static final CheckingMethod EQUALS = new CMEquals();
	
	/**
	 * Compares the versions lexicographically.
	 * @author AtomicStyker
	 */
	public static final CheckingMethod LEXICOGRAPHICAL = new CMLexicographical();
	
	/**
	 * Splits the version in the numbers representing it, and if returns UMVersion >= webVersion.
	 */
	public static final CheckingMethod NUMERICAL = new CMNumerical();
	
	/**
	 * Returns that the mod is updated if the length of the web version isn't larger than the UM version.
	 */
	public static final CheckingMethod LENGTH_WISE = new CMLengthWise();
	
	/**
	 * Checks if the mod is updated.
	 * @param mod The mod to check.
	 * @param webVersion The web version for that mod.
	 * @return true if the mod is updated.
	 */
	public abstract boolean isUpdated(UpdateManagerMod mod, String webVersion);
	
}
