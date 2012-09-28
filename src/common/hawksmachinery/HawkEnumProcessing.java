
package hawksmachinery;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Instead of IDs, processing now uses enums.
 * 
 * @author Elusivehawk
 */
public enum HawkEnumProcessing
{
	CRUSHING(new HashMap()),
	
	CRUSHING_EXPLOSIVES(new HashMap()),
	
	WASHING(new HashMap()),
	
	SINTERER(new HashMap());
	
	private Map processingList;
	
	HawkEnumProcessing(Map recipeList)
	{
		this.processingList = recipeList;
		
	}
	
	public Map getRecipeList()
	{
		return this.processingList;
	}
	
}
