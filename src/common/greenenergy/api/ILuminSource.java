package greenenergy.api;

public interface ILuminSource extends IEtherWaveAffected{
	
	/**
	 * @return Max rate of lumin push per tick
	 */
	public int pushRatePerTick();
	
	/**
	 * @return Maximum internal lumin capacity (if any)
	 */
	public int getMaxLuminCapacity();
	
}
