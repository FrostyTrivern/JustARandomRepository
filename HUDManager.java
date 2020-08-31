package PClient.gui.hud;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

import PClient.Event.EventManager;
import PClient.Event.EventTarget;
import PClient.mods.impl.ModHelloWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.Render;

public class HUDManager<RenderEvent> {
	
	private HUDManager() {}
	
	private static HUDManager instance = null;
	
	public static HUDManager getInstance() {
		
		if(instance != null) {
			return instance;
		}
		
		instance = new HUDManager();
		EventManager.register(instance);
		
		return instance;
		
	}
	
	private Set<IRender> registeredRenderers = Sets.newHashSet();
    private Minecraft mc = Minecraft.getMinecraft();
    
    public void register(IRender... renderers)  {
    	for(IRender render : renderers) {
    		this.registeredRenderers.add(render);
    	}
    }
    
    public void unregister(IRender... renderers)  {
    	for(IRender render : renderers) {
    		this.registeredRenderers.remove(render);
    	}
    }
    
    public Collection<IRender> getRegisteredRenderers() {
    	return Sets.newHashSet(registeredRenderers);
    }
    
    public void openConfigScreen() {
    	mc.displayGuiScreen(new HUDConfigScreen(this));
    }
    
    @EventTarget
    public void onRender(RenderEvent e) {
    	if(mc.currentScreen == null || mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiChat) {
    		for(IRender renderer : registeredRenderers) {
    			callRenderer(renderer);
    			System.out.println("Hola!");
    		}
    	}
    }

	private void callRenderer(IRender renderer) {
		if(!renderer.isEnabled() ) {
			return;
		}
		
		ScreenPosition pos = renderer.load();
		
		if(pos == null) {
			pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
		}
		
		renderer.render(pos);
	}
    
	
}
