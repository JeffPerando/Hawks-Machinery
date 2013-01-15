
package hawksmachinery.machine.common.logo;

import hawksmachinery.machine.common.HMEntityRobot;
import hawksmachinery.machine.common.api.HMLogoError;
import hawksmachinery.machine.common.api.IHMRobot;
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
