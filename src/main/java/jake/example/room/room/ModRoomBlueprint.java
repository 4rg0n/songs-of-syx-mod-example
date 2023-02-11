package jake.example.room.room;

import java.io.IOException;

import settlement.path.finder.SFinderFindable;
import settlement.room.main.RoomBlueprintIns;
import settlement.room.main.furnisher.Furnisher;
import settlement.room.main.util.RoomInitData;
import snake2d.util.file.FileGetter;
import snake2d.util.file.FilePutter;

/**
 * The main class regarding this room. Only thing that's actually needed. When implementing the class, one will notice one also 
 * needs a "furnisher" and a "roomInstance" for it to work.
 */
public class ModRoomBlueprint extends RoomBlueprintIns<ModRoomInstance> {

	final static String JSON_KEY = "MOD_ROOM";

	private final ModRoomFurnisher furnisher;
	
	/**
	 * @param data helpful init data that we don't need to care too much about. It automates reading of the .txt files
	 * @throws IOException when reading the room sprite fails
	 */
	public ModRoomBlueprint(RoomInitData data) throws IOException {
		super(0, data, JSON_KEY, data.m.CATS.MAIN_INDUSTRY.misc);
		furnisher = new ModRoomFurnisher(this, data);
	}

	@Override
	protected void saveP(FilePutter f) {
	}

	@Override
	protected void loadP(FileGetter f) throws IOException {

	}

	@Override
	protected void clearP() {
	}

	@Override
	public SFinderFindable service(int tx, int ty) {
		return null;
	}

	@Override
	public Furnisher constructor() {
		return furnisher;
	}

	@Override
	protected void update(float ds) {
	}
}