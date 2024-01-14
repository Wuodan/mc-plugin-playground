package ch.notyourbiz.minecraft.plugin.mcpluginplayground.service;

import com.hakan.basicdi.annotations.Autowired;
import com.hakan.basicdi.annotations.PostConstruct;
import com.hakan.basicdi.annotations.Service;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

@Service
public class McPluginPlaygroundService {
    private static final Logger log = Bukkit.getLogger();
    private final String serviceMessage;

    @Autowired
    public McPluginPlaygroundService() {
        serviceMessage = "Hello McPluginPlaygroundService!";
    }

    @PostConstruct
    public void init() {
        log.info("McPluginPlaygroundService is initialized!");
        log.info(serviceMessage);
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(message);
        log.info(message);
        log.info(serviceMessage);
    }
}
