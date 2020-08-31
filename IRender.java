//I Did change every line of code from IRenderer to IRender
package PClient.gui.hud;

public interface IRender extends IRenderConfig {
	
	int getWidth();
	int getHeight();
	
	void render(ScreenPosition pos);
	
	default void renderDummy(ScreenPosition pos) {
		render(pos);
	}
	
	public default boolean isEnabled() {
		return true;
	}

}
