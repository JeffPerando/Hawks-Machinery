
package hawksmachinery.core.common.api.logo;

import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public interface IHMRobot
{
	public IHMLogoWord getHandlerForWord(String word);
	
	public IHMLogoInterpreter getInterpreter();
	
	public void setInterpreter(IHMLogoInterpreter interpreter);
	
	public int getInteger(String intName);
	
	public void setInteger(String intName, int integer);
	
}
