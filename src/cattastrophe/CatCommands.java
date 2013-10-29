package cattastrophe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import cattastrophe.Cattastrophe;

public class CatCommands implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        if (sender.isOp() || sender.hasPermission("cattastrophe.cmd")) {
            if ((cmd.getName().equalsIgnoreCase("cattastrophe") || cmd.getName().equalsIgnoreCase("cat")) && args.length > 0) {
                if (args[0].equalsIgnoreCase("enable")) {
                    Cattastrophe.getInstance().configFile.set("isActive", true);
                    sender.sendMessage("You have enabled Cat-Tastrophe!");
                    try {
                        Cattastrophe.getInstance().configFile.save(Cattastrophe.getInstance().config);
                    } catch(Exception e) { System.out.println("ERROR: Config failed to save."); }
                } else if (args[0].equalsIgnoreCase("disable")) {
                    Cattastrophe.getInstance().configFile.set("isActive", false);
                    sender.sendMessage("You have disabled Cat-Tastrophe!");
                    try {
                        Cattastrophe.getInstance().configFile.save(Cattastrophe.getInstance().config);
                    } catch(Exception e) { System.out.println("ERROR: Config failed to save."); }
                }
                    
            }
        }
        return false;
    }

}
