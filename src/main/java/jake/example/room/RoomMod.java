package jake.example.room;

import java.io.IOException;

import jake.example.room.room.ROOM_MOD;
import script.SCRIPT;
import settlement.room.main.RoomBlueprint;
import settlement.room.main.RoomCreator;
import settlement.room.main.util.RoomInitData;
import util.info.INFO;

public final class RoomMod implements SCRIPT {

	private final INFO info = new INFO("room script", "more rooms");
	
	public RoomMod(){
		
	}
	
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
		new RoomCreator() {
			
			@Override
			public RoomBlueprint createBlueprint(RoomInitData init) throws IOException {
				return new ROOM_MOD(init);
			}
		};
		
	}

	@Override
	public SCRIPT_INSTANCE initAfterGameCreated() {
		return new Instance();
	}


}
