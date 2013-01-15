
package hawksmachinery.machine.common.api;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoError
{
	public final HMEnumErrorType error;
	public final String word;
	public final String[] failedArgs;
	
	public HMLogoError(HMEnumErrorType errorType, String attemptedWord, String[] args)
	{
		error = errorType;
		word = attemptedWord;
		failedArgs = args;
		
	}
	
	public String getErrorMessage()
	{
		return this.error.formatError(this.word, this.failedArgs);
	}
	
}
