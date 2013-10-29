package cattastrophe;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class ChatListener implements Listener {
    
    private Cattastrophe cat = Cattastrophe.getInstance();
    
    private Player[] playerList;
    
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
    	String[] msg = event.getMessage().split("\\s+");
    	
    	for (String string : msg) {//int i = 0 ; i < msg.length ; i++) {
    		if (!Cattastrophe.getInstance().getIsActive()){
            	return;
        	}
        
        	if (!event.getPlayer().hasPermission("cattastrophe.use") && !event.getPlayer().isOp()){
        		return;
        	}
        
        	if (string.equalsIgnoreCase("meow")){
            	playerList = Bukkit.getServer().getOnlinePlayers();
            	for (Player player : playerList) {
                	Location loc = player.getLocation();
                	final World world = player.getWorld();
                
                	for(int x = 0 ; x < Cattastrophe.getInstance().meowSpawns ; x ++){
                    	world.spawnEntity(loc, EntityType.OCELOT);
                	}
                
                	final List<Entity> entities = player.getWorld().getEntities();
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Cat-Tastrophe"), new Runnable(){ public void run(){
                    	for(Entity entity : entities){
                        	if(entity.getType() == EntityType.OCELOT) {
                            	world.createExplosion(entity.getLocation(), Cattastrophe.getInstance().explosionSizeM);
                            	entity.remove();
                        	}
                    	}
                	}}, Cattastrophe.getInstance().tickDelayM);
            	}
            	return;
        	} else if (string.equalsIgnoreCase("purr")){
            	Player player = event.getPlayer();
            	World world = player.getWorld();
            
            	System.out.println("pur command detected!");
            	List<Entity> entities = player.getWorld().getEntities();
            
            	for(Entity entity : entities){
                	if(entity.getType() == EntityType.OCELOT) {
                    	world.createExplosion(entity.getLocation(), Cattastrophe.getInstance().explosionSizeP);
                    	entity.remove();
                	}
            	}
            	return;
        	} else if (string.equalsIgnoreCase("cats!")){
            	final Location loc = event.getPlayer().getLocation();
            	Player player = event.getPlayer();
            	final World world = event.getPlayer().getWorld();
            
            	for(int x = 0 ; x < Cattastrophe.getInstance().catsSpawns ; x ++){
                	world.spawnEntity(loc, EntityType.OCELOT);
            	}
            	final List<Entity> entities = player.getWorld().getEntities();
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Cat-Tastrophe"), new Runnable(){ public void run(){
                	for(Entity entity : entities){
                    	if(entity.getType() == EntityType.OCELOT) {
                        	if (entity.getLocation().distance(loc) <= Cattastrophe.getInstance().catsRange) {
                            	world.createExplosion(entity.getLocation(), Cattastrophe.getInstance().explosionSizeC);
                            	entity.remove();
                        	}
                    	}
                	}
            	}}, Cattastrophe.getInstance().tickDelayC);
            return;
        	}
    	}
    }
}
