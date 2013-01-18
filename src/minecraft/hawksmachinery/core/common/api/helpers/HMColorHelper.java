
package hawksmachinery.core.common.api.helpers;

import java.awt.Color;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMColorHelper
{
	private static HMColorHelper INSTANCE = new HMColorHelper();
	
	public Color readColorFromNBT(NBTTagCompound NBTTag)
	{
		return new Color(NBTTag.getInteger("Red"), NBTTag.getInteger("Green"), NBTTag.getInteger("Blue"));
	}
	
	public NBTTagCompound writeColortoNBT(NBTTagCompound NBTTag, Color color)
	{
		NBTTag.setInteger("Red", color.getRed());
		NBTTag.setInteger("Green", color.getGreen());
		NBTTag.setInteger("Blue", color.getBlue());
		return NBTTag;
	}
	
	public static HMColorHelper instance()
	{
		return INSTANCE;
	}
	
}
