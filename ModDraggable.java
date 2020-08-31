package PClient.mods;

import PClient.gui.hud.IRender;
import PClient.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRender {
	
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}
	
	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}

}
