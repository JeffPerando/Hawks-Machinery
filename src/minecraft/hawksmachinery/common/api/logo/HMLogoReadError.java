
package hawksmachinery.common.api.logo;

import net.minecraft.util.StatCollector;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoReadError //TODO Replace with more detailed enum-based system.
{
	public static final HMLogoReadError UNFINISHED_NEST = new HMLogoReadError("HMLogo.error.incompleteNest.name");
	public static final HMLogoReadError IMPROPER_NEST = new HMLogoReadError("HMLogo.error.stupidNest.name");
	public static final HMLogoReadError MISSING_NEST = new HMLogoReadError("HMLogo.error.nullNest.name");
	public static final HMLogoReadError UNKNOWN_WORD = new HMLogoReadError("HMLogo.error.word.name");
	public static final HMLogoReadError INVALID_ARGS = new HMLogoReadError("HMLogo.error.args.name");
	public static final HMLogoReadError MISSING_ARGS = new HMLogoReadError("HMLogo.error.argsMissing.name");
	public static final HMLogoReadError INVALID_SYNTAX = new HMLogoReadError("HMLogo.error.syntax.name");
	public static final HMLogoReadError FINE = new HMLogoReadError(null);
	
	private final String errorMessage;
	
	public HMLogoReadError(String error)
	{
		this.errorMessage = error;
		
	}
	
	public String getErrorMessage()
	{
		return StatCollector.translateToLocal(this.errorMessage);
	}
	
}
