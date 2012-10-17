package greenenergy.api;

import net.minecraft.src.ItemStack;

/**
 * Apply to items that can store Lumins
 * @author atrain99
 *
 */
public interface ILuminItem extends IEtherWaveAffected{
	 	public double onRecieveLumins(double lumins);
	    
	    public double tryToUseLumins(double luminReq);
	    
	    public boolean canDischarge();
	    
	    public boolean canCharge();
	    
	    public double getTransferRate();
	    
	    public int getLuminCapacity();
	    
	    public int getLuminsStored();
	
}
