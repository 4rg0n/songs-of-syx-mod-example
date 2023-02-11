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
 */
@NoArgsConstructor
public final class ModRoomScript implements SCRIPT {

	private final INFO info = new INFO("room script", "more rooms");

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
	public SCRIPT_INSTANCE initAfterGameCreated() {
		return new WeatherEclipseScript();
	}
}