
package hawksmachinery.core.common.api.logo;

import hawksmachinery.machine.common.HMEntityRobot;

/**
 * 
 * Implement this in order to make a new word for the LOGO emulator.
 * 
 * @author Elusivehawk
 */
public interface IHMLogoWord
{
	/**
	 * 
	 * The word being added.
	 * 
	 * @return The new word.
	 */
	public String getWord();
	
	/**
	 * 
	 * A shorter version of the word being added. (Optional)
	 * 
	 * @return The shorter version of {@link getWord()}.
	 */
	public String getShortWord();
	
	/**
	 * 
	 * @return Whether or not the return value of {@link getWord()} supports nesting.
	 */
	public boolean supportsNesting();
	
	/**
	 * 
	 * @return The number of arguments needed by this word.
	 */
	public int argCount();
	
	/**
	 * 
	 * Notice: Not called if {@link argCount()} returns 0.
	 * 
	 * @param arg The argument in question.
	 * @return Whether or not arg is valid.
	 */
	public boolean isValidArgument(String arg);
	
	/**
	 * 
	 * Invoked when the return value of {@link getWord()} is used.
	 * 
	 * @param args The arguments passed alongside the word.
	 * @param robot The robot the word is being invoked on.
	 */
	public HMLogoError activateWord(String[] args, IHMRobot robot);
	
}
