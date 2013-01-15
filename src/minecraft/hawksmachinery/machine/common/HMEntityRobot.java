
package hawksmachinery.machine.common;

import java.util.ArrayList;
import java.util.HashMap;
import hawksmachinery.machine.common.api.IHMLogoInterpreter;
import hawksmachinery.machine.common.api.IHMLogoWord;
import hawksmachinery.machine.common.api.IHMRobot;
import hawksmachinery.machine.common.logo.HMLogoInterpreter;
import hawksmachinery.machine.common.logo.HMLogoWordDictionary;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMEntityRobot extends EntityLiving implements IHMRobot
{
	private HMLogoWordDictionary dictionary = new HMLogoWordDictionary();
	private HashMap<String, Integer> integers = new HashMap<String, Integer>();
	private IHMLogoInterpreter interpreter = new HMLogoInterpreter(this);
	
	public HMEntityRobot(World world)
	{
		super(world);
		
	}
	
	@Override
	public int getMaxHealth()
	{
		return 50;
	}
	
	@Override
    protected boolean canDespawn()
    {
    	return false;
    }
	
	@Override
	public IHMLogoWord getHandlerForWord(String word)
	{
		return this.dictionary.getHandlerForWord(word);
	}
	
	@Override
	public IHMLogoInterpreter getInterpreter()
	{
		return this.interpreter;
	}
	
	@Override
	public void setInterpreter(IHMLogoInterpreter interpreter)
	{
		this.interpreter = interpreter;
		
	}
	
	@Override
	public int getInteger(String intName)
	{
		return this.integers.get(intName);
	}
	
	@Override
	public void setInteger(String intName, int integer)
	{
		this.integers.put(intName, integer);
		
	}
	
}
