package settlement.room.industry.workshop2;

import settlement.path.finders.SFinderRoomService;
import settlement.room.industry.module.INDUSTRY_HASER;
import settlement.room.industry.module.Industry;
import settlement.room.industry.module.IndustryUtil;
import settlement.room.industry.module.RoomBoost;
import settlement.room.main.BonusExperience.RoomExperienceBonus;
import settlement.room.main.RoomBlueprintIns;
import settlement.room.main.category.RoomCategorySub;
import settlement.room.main.furnisher.Furnisher;
import settlement.room.main.util.RoomInitData;
import snake2d.util.file.FileGetter;
import snake2d.util.file.FilePutter;
import snake2d.util.sets.LIST;
import snake2d.util.sets.LISTE;
import view.sett.ui.room.UIRoomModule;

import java.io.IOException;

public class ROOM_WORKSHOP2 extends RoomBlueprintIns<settlement.room.industry.workshop2.Workshop2Instance> implements INDUSTRY_HASER{

	public final static String type = "WORKSHOP2";
	final settlement.room.industry.workshop2.Job job;
	final settlement.room.industry.workshop2.Constructor constructor;
	final LIST<Industry> indus;
	
	public ROOM_WORKSHOP2(int index, RoomInitData init, String key, RoomCategorySub cat) throws IOException {
		super(index, init, key, cat);
		
		constructor = new settlement.room.industry.workshop2.Constructor(this, init);
		pushBo(init.data(), type, true);

		job = new settlement.room.industry.workshop2.Job(this);

		indus = Industry.createIndustries(this, init, new RoomBoost[] {constructor.efficiency}, bonus());
		new RoomExperienceBonus(this, init.data(), bonus());
		employment().countInputSet();
	}
	
	@Override
	protected void update(double ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SFinderRoomService service(int tx, int ty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void saveP(FilePutter saveFile){
		IndustryUtil.save(saveFile, indus);
	}
	
	@Override
	protected void loadP(FileGetter saveFile) throws IOException{
		IndustryUtil.load(saveFile, indus);
	}
	
	@Override
	protected void clearP() {
		IndustryUtil.clear(indus);
	}
	
	@Override
	public Furnisher constructor() {
		return constructor;
	}

	@Override
	public LIST<Industry> industries() {
		return indus;
	}
	
	@Override
	public void appendView(LISTE<UIRoomModule> mm) {
		
	}

}
