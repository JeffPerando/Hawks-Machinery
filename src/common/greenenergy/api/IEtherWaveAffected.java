package greenenergy.api;
/**
 * To be applied to things (Blocks, Items, Entities, TE's) that are affected by ether waves.
 * @author atrain99
 *
 */
public interface IEtherWaveAffected {
	/**
	 * When an ether wave affects this item.
	 */
	public void onWaveAffect();
}
