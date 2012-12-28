
package hawksmachinery.common.api.logo;

import net.minecraft.entity.EntityLiving;

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
	 * Invoked when the return value of {@link getWord()} is used.
	 * 
	 * @param args The arguments passed alongside the word.
	 * @param robot The robot the word is being invoked on.
	 */
	public void activateWord(String[] args, EntityLiving robot);
	
}
