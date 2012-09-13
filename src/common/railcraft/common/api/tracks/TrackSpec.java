package railcraft.common.api.tracks;

import java.util.HashMap;
import java.util.Map;

/**
 * Each type of Track has a single instance of TrackSpec
 * that corresponds with it.
 *
 * Each Track block in the world has a ITrackInstance that
 * corresponds with it.
 *
 * Take note of the difference (similar to block classes and tile entities classes).
 *
 * TrackSpecs must be registered with the TrackRegistry.
 *
 * @see TrackRegistry
 * @see ITrackInstance
 *
 * @author CovertJaguar <railcraft.wikispaces.com>
  */
public final class TrackSpec
{

    private final Map<String, String> nameMap = new HashMap<String, String>();
    private final String tag;
    private final String textureFile;
    private final int trackId;
    private final int textureId;
    private final Class<? extends ITrackInstance> instanceClass;

    /**
     * Defines a new track spec.
     *
     * @param trackId A unique identifier for the track type. 0-512 are reserved for Railcraft. Capped at Integer.MAX_VALUE
     * @param tag A unique internal string identifier (ex. "track.speed.transition")
     * @param textureFile See ITextureProvider
     * @param textureId The texture index used by the track's item
     * @param instanceClass The ITrackInstance class that corresponds to this TrackSpec
     * @see ITextureProvider
     */
    public TrackSpec(int trackId, String tag, String textureFile, int textureId, Class<? extends ITrackInstance> instanceClass)
    {
        this.trackId = trackId;
        this.tag = tag;
        this.textureFile = textureFile;
        this.textureId = textureId;
        this.instanceClass = instanceClass;
    }

    public String getTrackTag()
    {
        return tag;
    }

    public int getTrackId()
    {
        return trackId;
    }

    public ITrackInstance createInstanceFromSpec()
    {
        try {
            return (ITrackInstance)instanceClass.newInstance();
        } catch (InstantiationException ex) {
            throw new RuntimeException("Improper Track Instance Constructor");
        } catch (IllegalAccessException ex) {
        }
        return null;
    }

    public String getTextureFile()
    {
        return textureFile;
    }

    public int getTextureIndex()
    {
        return textureId;
    }
}
