
package hawksmachinery.core.common.api.logo;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public interface IHMLogoInterpreter
{
	public HMLogoError runProgram(String[] program, boolean saveTemps);
	
}
