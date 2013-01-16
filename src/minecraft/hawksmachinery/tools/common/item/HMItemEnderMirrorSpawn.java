
package hawksmachinery.tools.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import hawksmachinery.core.common.item.HMItem;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemEnderMirrorSpawn extends HMItem
{
	public HMItemEnderMirrorSpawn(int id)
	{
		super(id);
		setItemName("HMEnderMirrorSpawn");
		setMaxDamage(3);
		setIconIndex(140);
		setMaxStackSize(1);
		
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
		if (player != null)
		{
			player.setItemInUse(item, this.getMaxItemUseDuration(item));
			
		}
		
    	return item;
    }
	
	@Override
	public ItemStack onFoodEaten(ItemStack item, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			if (player.dimension == 0)
			{
				ChunkCoordinates spawnLocation;
				ServerConfigurationManager server = MinecraftServer.getServer().getServerConfigurationManager(MinecraftServer.getServer());
				EntityPlayerMP sentPlayer = server.getPlayerForUsername(player.username);
				
				if (sentPlayer.getBedLocation() != null)
				{
					spawnLocation = player.getBedLocation();
					
				}
				else
				{
					WorldInfo info = world.getWorldInfo();
					
					spawnLocation = new ChunkCoordinates(info.getSpawnX(), info.getSpawnY(), info.getSpawnZ());
					
				}
				
				sentPlayer.fallDistance = 0;
				sentPlayer.playerNetServerHandler.setPlayerLocation(spawnLocation.posX - 0.5, spawnLocation.posY, spawnLocation.posZ - 0.5, sentPlayer.rotationPitch, sentPlayer.rotationYaw);
				item.damageItem(1, player);
				sentPlayer.clearActivePotions();
				world.playSoundEffect(sentPlayer.posX, sentPlayer.posY, sentPlayer.posZ, "mob.endermen.portal", 1.0F, 1.0F);
				
			}
			else
			{
				world.createExplosion(player, player.posX, player.posY, player.posZ, 7.5F, true);
				
			}
			
		}
		
		return item;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack item)
	{
		return 40;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack item)
	{
		return EnumAction.block;
	}
	
}
