package PClient;

import PClient.Event.EventTarget;
import PClient.gui.hud.HUDManager;
import PClient.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;

public class Client {

    private static final Client INSTANCE = new Client();
    public static final Client getInstance() {
        return INSTANCE;
    }

    private DiscordRP discordRP = new DiscordRP();
    
    private HUDManager hudManager;

    public void init() {
    	SessionChanger.getInstance().setUser("...", "...");
        discordRP.start();
    }
    
    public void start() {
    	hudManager = HUDManager.getInstance();
    	ModInstances.register(hudManager);
    }

    public void shutdown() {
        discordRP.shutdown();
    }

    public DiscordRP getDiscordRP() {
        return discordRP;
    }
    
    @EventTarget
    public void onTick(ClickEvent e) {
    	if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
    		hudManager.openConfigScreen();
    	}
    }

}
