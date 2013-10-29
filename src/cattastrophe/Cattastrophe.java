package cattastrophe;

import java.io.File;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import net.minecraft.server.v1_6_R3.EntityTypes;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Cattastrophe extends JavaPlugin {
    private static Cattastrophe instance;
    public static Logger LOG;
    private final CatCommands cmdExecutor = new CatCommands();
    
    public File config; 
    public FileConfiguration configFile;
    public int explosionSizeM;
    public int explosionSizeC;
    public int explosionSizeP;
    public int meowSpawns;
    public int catsSpawns;
    public int catsRange;
    public int tickDelayM;
    public int tickDelayC;
    
    private boolean isActive;
    
    private ChatListener chatListener = new ChatListener();
    
    @Override
    public void onEnable()
    {
        instance = this;
        LOG = getLogger();

        
        config = new File(instance.getDataFolder(), "config.yml");
        if (!config.exists()){
            try { 
                config.getParentFile().mkdir();
                config.createNewFile();
                configFile = YamlConfiguration.loadConfiguration(config);
                configFile.set("isActive", true);
                configFile.set("MeowExplosionSize", 3);
                configFile.set("CatsExplosionSize", 5);
                configFile.set("SpawnedByMeow", 10);
                configFile.set("TickDelayMeow", 20);
                configFile.set("SpawnedByCats", 5);
                configFile.set("TickDelayCats", 20);
                configFile.set("CatsRange", 5);
                configFile.save(config);
            } catch (Exception e ) {System.out.print("Config creation failed, please fix permissions!");}
        } else {
            configFile = YamlConfiguration.loadConfiguration(config);
        }
        isActive = configFile.getBoolean("isActive");
        explosionSizeM = configFile.getInt("MeowExplosionSize");
        explosionSizeC = configFile.getInt("CatsExplosionSize");
        explosionSizeP = 10;
        meowSpawns = configFile.getInt("SpawnedByMeow");
        tickDelayM = configFile.getInt("TickDelayMeow");
        catsSpawns = configFile.getInt("SpawnedByCats");
        tickDelayC = configFile.getInt("TickDelayCats");
        catsRange = configFile.getInt("CatsRange");
        
        getCommand("cattastrophe").setExecutor(cmdExecutor);
        getCommand("cat").setExecutor(cmdExecutor);
        
        getServer().getPluginManager().registerEvents(chatListener, this);
    }
    
    public static Cattastrophe getInstance() {
        return instance;
    }

    public boolean getIsActive() {
        // TODO Auto-generated method stub
        return configFile.getBoolean("isActive");
    }
}
