package ch.notyourbiz.minecraft.plugin.mcpluginplayground.command;

import ch.notyourbiz.minecraft.plugin.mcpluginplayground.FartHandler;
import ch.notyourbiz.minecraft.plugin.mcpluginplayground.service.McPluginPlaygroundService;
import com.hakan.basicdi.annotations.Autowired;
import com.hakan.spinjection.command.annotations.Command;
import com.hakan.spinjection.command.annotations.Executor;
import com.hakan.spinjection.command.annotations.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static ch.notyourbiz.minecraft.plugin.mcpluginplayground.McPluginPlaygroundPlugin.fartCommand;

@Command(
        name = "fart",
        description = "Fart on demand",
        usage = "/fart"
)
public class FartCommand {

    private final McPluginPlaygroundService myService;

    @Autowired
    public FartCommand(McPluginPlaygroundService myService) {
        this.myService = myService;
    }

    @Subcommand(
            permission = "fartInject.use",
            permissionMessage = "You don't have permission to use this command!"
    )
    public void execute(@NotNull @Executor CommandSender sender) {
        if (!fartCommand) {
            return;
        }
        if (sender instanceof Player player) {
            FartHandler.fart(player);
            myService.sendMessage(player, "You farted!");
        }
    }
}
