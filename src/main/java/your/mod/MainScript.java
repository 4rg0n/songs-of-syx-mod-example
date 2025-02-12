package your.mod;

import java.nio.file.Path;

import lombok.NoArgsConstructor;
import script.SCRIPT;
import settlement.room.main.util.RoomInitData;
import util.info.INFO;

/**
 * Entry point for the mod.
 * Contains some basic information about the mod.
 * Used to set up your mod.
 *
 * See {@link SCRIPT} for some documentation.
 */
@NoArgsConstructor
@SuppressWarnings("unused") // used by the game via reflection
public final class MainScript implements SCRIPT {

	/**
	 * This info will be displayed when starting a new game and choosing a script
	 */
	private final INFO info = new INFO("Example Mod", "Adds a sun eclipse, which changes the light color.");

	@Override
	public CharSequence name() {
		return info.name;
	}

	@Override
	public CharSequence desc() {
		return info.desc;
	}

	/**
	 * Called before an actual game is started or loaded
	 */
	@Override
	public void initBeforeGameCreated() {}


	/**
	 * @return whether mod shall be selectable when starting a new game
	 */
	@Override
	public boolean isSelectable() {
		return SCRIPT.super.isSelectable();
	}

	/**
	 * @return whether mod shall be loaded into existing saves or not
	 */
	@Override
	public boolean forceInit() {
		return SCRIPT.super.forceInit();
	}

	/**
	 * This actually creates the "instance" of your script.
	 */
	@Override
	public SCRIPT_INSTANCE createInstance() {
		return new InstanceScript();
	}
}