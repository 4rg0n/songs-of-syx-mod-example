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

	/**
	 * Called whenever the game saves
	 *
	 * @param file to write your save data into
	 */
	@Override
	public void save(FilePutter file) {
		System.out.println("[EXAMPLE MOD] Writing save game: " + file.path);
	}

	/**
	 * Called whenever the game loads a save file
	 *
	 * @param file to read your saved data from
	 */
	@Override
	public void load(FileGetter file) {
		System.out.println("[EXAMPLE MOD] Reading save game: " + file.path);
	}

	/**
	 * The update method will be called multiple times a second by the game.
	 *
	 * @param deltaSeconds how many seconds of in-game time that has passed since the previous update
	 */
	@Override
	public void update(double deltaSeconds) {
		// this is spammy
		// System.out.println("[EXAMPLE MOD] update: " + deltaSeconds + " delta seconds");
	}

	/**
	 * Called whenever the mouse is hovering something
	 *
	 * @param mouseTimer how long the mouse hovered already in ms
	 * @param text the hover box, which would or could be shown
	 */
	@Override
	public void hoverTimer(double mouseTimer, GBox text) {
		System.out.println("[EXAMPLE MOD] hover time: " + mouseTimer + " seconds");
	}

	/**
	 * Called multiple times when the game renders
	 *
	 * @param renderer used for rendering something
	 * @param deltaSeconds timestamp of the current render process
	 */
	@Override
	public void render(Renderer renderer, float deltaSeconds) {
		// this is spammy
		// System.out.println("[EXAMPLE MOD] render: " + deltaSeconds + " delta seconds");
	}

	/**
	 * Called whenever a keyboard key was pushed
	 *
	 * @param keys for interacting with the kys
	 */
	@Override
	public void keyPush(KEYS keys) {
		System.out.println("[EXAMPLE MOD] key pushed");
	}

	/**
	 * Called whenever a mouse button was clicked
	 *
	 * @param button clicked mouse button
	 */
	@Override
	public void mouseClick(MButt button) {
		System.out.println("[EXAMPLE MOD] Mouse button clicked: " + button);
	}

	/**
	 * Called whenever the mouse hovers something
	 *
	 * @param mCoo mouse coordinates x and y
	 * @param mouseHasMoved whether the mouse has moved since last call
	 */
	@Override
	public void hover(COORDINATE mCoo, boolean mouseHasMoved) {
		System.out.println("[EXAMPLE MOD] hovering over x:" + mCoo.x() + " y:" + mCoo.y());
	}

	/**
	 * @return whether the game shall crash when it receives an error from the mod while loading the save game
	 */
	@Override
	public boolean handleBrokenSavedState() {
		return SCRIPT.SCRIPT_INSTANCE.super.handleBrokenSavedState();
	}
}