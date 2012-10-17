package greenenergy.api;
/**
 * Marker interface for drains of power.
 * @author sf
 *
 */
public interface ILuminDrain extends IEtherWaveAffected{
	
	/**
	 * @return The maximum lumin capacity of this drain.
	 */
	public int getMaxCapacity();
	
	/**
	 * @return The amount of lumins this is currently holding
	 */
	public int getCurrentLuminStorage();
	/**
	 * @return The amount of lumins this currently needs to become full.
	 */
	public int luminRequest();
}
