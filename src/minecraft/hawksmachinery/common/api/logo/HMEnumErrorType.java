
package hawksmachinery.common.api.logo;

import net.minecraft.util.StatCollector;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public enum HMEnumErrorType
{
	UNFINISHED_NEST("HMLogo.error.incompleteNest.name"),
	IMPROPER_NEST("HMLogo.error.stupidNest.name"),
	MISSING_NEST("HMLogo.error.nullNest.name"),
	UNKNOWN_WORD("HMLogo.error.word.name"),
	INVALID_ARGS("HMLogo.error.args.name"),
	MISSING_ARGS("HMLogo.error.argsMissing.name"),
	INVALID_SYNTAX("HMLogo.error.syntax.name"),
	FINE(null);
	
	private String errorName;
	
	HMEnumErrorType(String error)
	{
		errorName = error;
	}
	
	public String getErrorMessage()
	{
		return StatCollector.translateToLocal(this.errorName);
	}
	
	public String formatError(String word, String[] args)
	{
		String startOfNotice = this.getErrorMessage();
		
		switch (this.ordinal())
		{
			case 3: return startOfNotice + ": " + word; //Unknown word
			case 4: return startOfNotice + args[0]; //Invalid args
			case 5: return startOfNotice + ": " + word;//Missing args
			default: return null;
		}
		
	}
	
}
