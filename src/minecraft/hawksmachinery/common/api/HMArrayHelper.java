
package hawksmachinery.common.api;

import java.util.ArrayList;

/**
 * 
 * Helps convert {@link ArrayList} to {@link Object}[], and vice versa.<br><br>
 * I also included a neat method or two for removing a certain class from a list.
 * 
 * @author Elusivehawk
 */
public class HMArrayHelper
{
	private static HMArrayHelper INSTANCE = new HMArrayHelper();
	
	public ArrayList convertBracketsToArray(Object[] brackets)
	{
		return this.convertBracketsToArray(brackets, true);
	}
	
	public ArrayList convertBracketsToArray(Object[] brackets, boolean keepNulls)
	{
		ArrayList list = new ArrayList();
		
		for (Object obj : brackets)
		{
			if (obj != null || keepNulls) list.add(obj);
			
		}
		
		return list;
	}
	
	public Object[] convertArrayToBrackets(ArrayList list)
	{
		Object[] brackets = new Object[list.size()];
		
		for (int counter = 0; counter < list.size(); ++counter)
		{
			brackets[counter] = list.get(counter);
			
		}
		
		return brackets;
	}
	
	public ArrayList removeObjsFromList(ArrayList list, Class toRemove)
	{
		ArrayList list2 = (ArrayList)list.clone();
		
		for (int counter = 0; counter < list.size(); ++counter)
		{
			if (list2.get(counter) != null)
			{
				if (!(list2.get(counter).getClass().equals(toRemove))) list.remove(counter);
				
			}
			
		}
		
		return list2;
	}
	
	public Object[] removeObjsFromBrackets(Object[] brackets, Class toRemove)
	{
		return this.convertArrayToBrackets(this.removeObjsFromList(this.convertBracketsToArray(brackets.clone()), toRemove));
	}
	
	public static HMArrayHelper instance()
	{
		return INSTANCE;
	}
	
}
