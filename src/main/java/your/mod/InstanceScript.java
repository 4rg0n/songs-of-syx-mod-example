package your.mod;

import java.io.IOException;

import script.SCRIPT;
import snake2d.MButt;
import snake2d.Renderer;
import snake2d.util.datatypes.COORDINATE;
import snake2d.util.file.*;
import util.gui.misc.GBox;
import view.keyboard.KEYS;

/**
 * Represents one instance of the script.
 * The game will use this as entry point for most of its loops
 * like the rendering or update. You can hook into these loops and events here.
 *
 * See {@link SCRIPT.SCRIPT_INSTANCE} for some documentation
 */
final class InstanceScript implements SCRIPT.SCRIPT_INSTANCE {

	private final Eclipse eclipse = new Eclipse();

	/**
	 * Called whenever the game saves
	 *
	 * @param file to write your save data into
	 */
	@Override
	public void save(FilePutter file) {
		file.i(eclipse.getDay());
	}

	/**
	 * Called whenever the game loads a save file
	 *
	 * @param file to read your saved data from
	 * @throws IOException when reading the save file fails
	 */
	@Override
	public void load(FileGetter file) throws IOException {
		eclipse.setDay(file.i());
	}

	/**
	 * This is where the actual eclipse is happening.
	 * The update method will be called multiple times a second by the game.
	 *
	 * @param ds how many seconds of in-game time that has passed since the previous update
	 */
	@Override
	public void update(double ds) {
		eclipse.update();
	}

	/**
	 * Called whenever the mouse is hovering something
	 *
	 * @param mouseTimer how long the mouse hovered already in ms
	 * @param text the hover box, which would or could be shown
	 */
	@Override
	public void hoverTimer(double mouseTimer, GBox text) {}

	/**
	 * Called multiple times when the game renders
	 *
	 * @param renderer used for rendering something
	 * @param ds timestamp of the current render process
	 */
	@Override
	public void render(Renderer renderer, float ds) {}

	/**
	 * Called whenever a keyboard key was pushed
	 *
	 * @param key pushed key
	 */
	@Override
	public void keyPush(KEYS key) {}

	/**
	 * Called whenever a mouse button was clicked
	 *
	 * @param button clicked mouse button
	 */
	@Override
	public void mouseClick(MButt button) {}

	/**
	 * Called whenever the mouse hovers something
	 *
	 * @param mCoo mouse coordinates x and y
	 * @param mouseHasMoved whether the mouse has moved since last call
	 */
	@Override
	public void hover(COORDINATE mCoo, boolean mouseHasMoved) {}

	/**
	 * @return whether the game shall crash when it receives an error from the mod while loading the save game
	 */
	@Override
	public boolean handleBrokenSavedState() {
		return SCRIPT.SCRIPT_INSTANCE.super.handleBrokenSavedState();
	}
}