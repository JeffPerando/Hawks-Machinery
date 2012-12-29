
package hawksmachinery.common.api.logo;

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
	
	public int getInteger(String intName);
	
	public void setInteger(String intName, int integer);
	
}
