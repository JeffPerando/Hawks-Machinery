
package hawksmachinery.machine.common.logo.words;

import hawksmachinery.machine.common.api.HMLogoError;
import hawksmachinery.machine.common.api.IHMLogoWord;
import hawksmachinery.machine.common.api.IHMRobot;

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
