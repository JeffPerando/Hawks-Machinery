
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
	CRUSHING("Crushing", new HashMap()),
	
	CRUSHING_EXPLOSIVES("CrushSplosives", new HashMap()),
	
	WASHING("Washing", new HashMap()),
	
	INDUCTION_FURNACE("Induction Furnace", new HashMap());
	
	private String name;
	private Map processingList;
	
	HawkEnumProcessing(String name, Map recipeList)
	{
		this.name = name;
		this.processingList = recipeList;
		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Map getRecipeList()
	{
		return this.processingList;
	}
	
}
