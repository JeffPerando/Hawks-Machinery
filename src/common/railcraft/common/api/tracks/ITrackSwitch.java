package railcraft.common.api.tracks;

public interface ITrackSwitch extends ITrackInstance, ITrackReversable
{

    public boolean isSwitched();

    public void setSwitched(boolean switched);

    public boolean getDirection();
}
