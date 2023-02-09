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
public class ROOM_MOD extends RoomBlueprintIns<ModRoomInstance> {

	private final ModRoomFurnisher furnisher;
	
	/**
	 * @param data helpful init data that we don't need to care too much about. It automates reading of the .txt files
	 * @throws IOException 
	 */
	public ROOM_MOD(RoomInitData data) throws IOException {
		/**
		 * 
		 */
		super(0, data, "MOD_ROOM", data.m.CATS.MAIN_INDUSTRY.misc);
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
		// TODO Auto-generated method stub
		
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
