package jake.example.room;

import java.io.IOException;

import jake.example.room.room.ModRoomBlueprint;
import lombok.NoArgsConstructor;
import script.SCRIPT;
import settlement.room.main.RoomBlueprint;
import settlement.room.main.RoomCreator;
import settlement.room.main.util.RoomInitData;
import util.info.INFO;

/**
 * Entry point for the mod.
 * See {@link SCRIPT} for some documentation.
 *
 * Adds the new room to the game and initializes the mod {@link script.SCRIPT.SCRIPT_INSTANCE}.
 */
@NoArgsConstructor
@SuppressWarnings("unused") // used by the game via reflection
public final class MainScript implements SCRIPT {

	/**
	 * This info will be displayed when starting a new game and choosing a script
	 */
	private final INFO info = new INFO("Example Mod", "Adds a 1x1 room and a sun eclipse, which changes the light color.");

	@Override
	public CharSequence name() {
		return info.name;
	}

	@Override
	public CharSequence desc() {
		return info.desc;
	}

	@Override
	public void initBeforeGameCreated() {
		// register the room blueprint within the game
		new RoomCreator() {
			@Override
			public RoomBlueprint createBlueprint(RoomInitData init) throws IOException {
				return new ModRoomBlueprint(init);
			}
		};
	}

	@Override
	public SCRIPT_INSTANCE createInstance() {
		return new InstanceScript();
	}
}