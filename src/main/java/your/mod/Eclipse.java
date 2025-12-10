package your.mod;

import game.time.TIME;
import lombok.Getter;
import lombok.Setter;
import settlement.main.SETT;
import snake2d.util.rnd.RND;
import view.interrupter.IDebugPanel;
import view.ui.message.MessageText;

/**
 * Example mod, which adds a color effect over your settlement.
 * You can delete this class if you don't need it.
 */
public class Eclipse {
    @Setter
    @Getter
    private int day = 16 + RND.rInt(16 * 10);
    private boolean hasMess;

    public Eclipse() {
        // This adds an entry to the in game debug panel.
        // It can be enabled by enabling "Debug Mode" in the games launcher settings.
        IDebugPanel.add("Script Eclipse", () -> day = TIME.days().bitsSinceStart() + 1);
    }

    /**
     * Has to be called by the game update loop
     */
    public void update() {
        if (TIME.days().bitsSinceStart() == day && TIME.light().dayIs()) {
            if (!hasMess) {
                new MessageText("Eclipse incoming :)").send();

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
}
