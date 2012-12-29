
package hawksmachinery.common.api.logo;

import hawksmachinery.common.HMEntityRobot;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoSubroutine
{
	public final String name;
	public ArrayList<String> words = new ArrayList<String>();
	public ArrayList<String[]> args = new ArrayList<String[]>();
	
	public HMLogoSubroutine(String name)
	{
		this.name = name;
		
	}
	
	public HMLogoError invoke(IHMRobot robot)
	{
		return null;//TODO
	}
	
}
