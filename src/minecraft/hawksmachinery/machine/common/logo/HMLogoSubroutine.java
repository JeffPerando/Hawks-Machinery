
package hawksmachinery.machine.common.logo;

import hawksmachinery.core.common.api.logo.HMLogoError;
import hawksmachinery.core.common.api.logo.IHMRobot;
import hawksmachinery.machine.common.HMEntityRobot;
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
