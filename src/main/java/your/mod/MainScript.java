package your.mod;


import lombok.NoArgsConstructor;
import script.SCRIPT;
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
	private final INFO info = new INFO("Example Mod", "Description goes here");

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
	public void initBeforeGameCreated() {
		System.out.println("[EXAMPLE MOD] initBeforeGameCreated");
	}

	/**
	 * Called after the game is created, but before the initiation
	 */
	@Override
	public void initBeforeGameInited() {
		System.out.println("[EXAMPLE MOD] initBeforeGameInited");
	}

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
		System.out.println("[EXAMPLE MOD] createInstance");
		return new InstanceScript();
	}
}