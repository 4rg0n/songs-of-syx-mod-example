package jake.example.room;

import java.io.IOException;

import game.time.TIME;
import init.paths.PATHS;
import script.SCRIPT;
import settlement.main.SETT;
import snake2d.MButt;
import snake2d.Renderer;
import snake2d.util.datatypes.COORDINATE;
import snake2d.util.file.*;
import snake2d.util.misc.ACTION;
import snake2d.util.rnd.RND;
import util.gui.misc.GBox;
import view.interrupter.IDebugPanel;
import view.keyboard.KEYS;
import view.main.MessageText;

/**
 * Represents one instance of the script.
 * See {@link SCRIPT.SCRIPT_INSTANCE} for some documentation
 *
 * This implementation has actually nothing to do with the room itself.
 * It's a randomly occurring eclipse, which changes the weather light color.
 */
final class WeatherEclipseScript implements SCRIPT.SCRIPT_INSTANCE {

	private int day = 16 + RND.rInt(16 * 10);
	private boolean hasMess;
	
	public WeatherEclipseScript() {
		IDebugPanel.add("Script Eclipse", () -> day = TIME.days().bitsSinceStart() + 1);
	}
	
	
	@Override
	public void save(FilePutter file) {
		file.i(day);
	}

	@Override
	public void load(FileGetter file) throws IOException {
		day = file.i();
	}

	@Override
	public void update(double ds) {
		if (TIME.days().bitsSinceStart() == day && TIME.light().dayIs()) {
			if (!hasMess) {
				Json messageJson = new Json(PATHS.SCRIPT().text.get("EXAMPLE"))
						.json("ECLIPSE_MESSAGE");
				new MessageText(messageJson).send();

				hasMess = true;
			}

			double partOfCircular = TIME.light().partOfCircular();
			if (partOfCircular > 0.5) {
				partOfCircular = Math.sqrt((partOfCircular - 0.5) * 2);
				double red = 1.0 - 0.75 * partOfCircular;
				double green = 1.0 - 0.85 * partOfCircular;

				SETT.WEATHER().lightColor().set(red, green, green);
			}
		} else {
			if (TIME.days().bitsSinceStart() == day + 1) {
				day = TIME.days().bitsSinceStart() + 16 + RND.rInt(16 * 10);
			}
				
			hasMess = false;
		}
	}
	
	@Override
	public void hoverTimer(double mouseTimer, GBox text) {
	}

	@Override
	public void render(Renderer r, float ds) {
	}

	@Override
	public void keyPush(KEYS key) {
	}

	@Override
	public void mouseClick(MButt button) {
	}

	@Override
	public void hover(COORDINATE mCoo, boolean mouseHasMoved) {
	}
}