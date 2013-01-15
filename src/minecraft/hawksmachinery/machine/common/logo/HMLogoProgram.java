
package hawksmachinery.machine.common.logo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoProgram
{
	public ArrayList<HMLogoWordObject> program;
	public HashMap<String, Integer> integers = new HashMap<String, Integer>();
	public HashMap<String, Boolean> booleans = new HashMap<String, Boolean>();
	
	public HMLogoProgram(ArrayList<HMLogoWordObject> program)
	{
		this.program = program;
		
	}
	
}
