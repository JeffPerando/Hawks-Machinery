
package hawksmachinery.common.logo.words;

import hawksmachinery.common.api.logo.HMLogoError;
import hawksmachinery.common.api.logo.IHMLogoWord;
import hawksmachinery.common.api.logo.IHMRobot;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoWordIf implements IHMLogoWord
{
	@Override
	public String getWord()
	{
		return "if";
	}
	
	@Override
	public String getShortWord()
	{
		return null;
	}
	
	@Override
	public boolean supportsNesting()
	{
		return true;
	}
	
	@Override
	public int argCount()
	{
		return 0;
	}
	
	@Override
	public boolean isValidArgument(String arg)
	{
		return Integer.valueOf(arg) > 0;
	}
	
	@Override
	public HMLogoError activateWord(String[] args, IHMRobot robot)
	{
		return null;
	}
	
}
