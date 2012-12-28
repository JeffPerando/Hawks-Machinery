
package hawksmachinery.common.api.logo;

import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;

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
	
	public HMLogoReadError invoke(EntityLiving robot)
	{
		return HMLogoReadError.FINE;
	}
	
}
