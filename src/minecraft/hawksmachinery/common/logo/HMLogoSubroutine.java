
package hawksmachinery.common.logo;

import hawksmachinery.common.HMEntityRobot;
import hawksmachinery.common.api.logo.HMLogoError;
import hawksmachinery.common.api.logo.IHMRobot;
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
