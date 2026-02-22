# Reading and understanding the Songs of Syx Source Code

## Which engine is Songs of Syx written in?

Songs of Syx uses a custom engine, especially tinkered for its purpose.
The engine is not a general purpose city builder engine, but more a Songs of Syx engine :)
It's based on [LWJGL 3.x](https://www.lwjgl.org/) and the game code refers to it as `Snake2D`.

## What programming language is Songs of Syx written in?

Songs of Syx was written primarily in Java 1.8, but it runs with Java 21.
It is shipped with its own Java Runtime Environment (JRE) found in the game installation directory under `jre/`.

## How can I read and browse the source code of Songs of Syx?

You can either follow the [Setup IntelliJ IDEA](../../doc/howto/intellij_setup.md) guide or look into the game installation directory under `info/SongsOfSyx-sources.jar`.
This is a `zip` archive, which you can extract and browse.

## Important packages

* `game`: core game logic code
* `game.boosting`: logic for the "boosters" used in config files
* `init`: data objects and their initialization read from `*.txt` files in the `data.zip` archive
* `launcher`: code needed for the game launcher
* `menu`: game main menu logic
* `script`: logic for loading Java `*.jar` files and their code from mods
* `settlement`: logic for game city building
* `settlement.entity.humanoid.ai`: ai logic for your citizens
* `settlement.job`: jobs your citizens will execute like "Clear trees" or build something
* `settlement.room`: logic for the rooms you can build
* `snake2d`: game engine
* `util`: all sorts of stuff
* `view`: complex in-game GUIs
* `world`: logic for the over world map

## Important classes

* `game.GAME`: actual game instance running as a separate process started via the main menu
* `game.boosting.BOOSTABLES`: contains all the bonuses
* `game.VERSION`: contains information about the current game version
* `game.events.EVENTS`: access to all the random events, which can happen during playing
* `game.faction.FACTONS`: access to all the NPC factions and your player faction
* `game.time.TIME`: information about the game time like the current day, month and year
* `game.tourism.TOURISM`: information about your settlement tourism
* `init.Main`: logic for starting the game launcher process
* `init.MainLaunchLauncher`: just starts the init.Main 
* `init.MainProcess`: logic for starting the game menu process
* `init.paths.PATHS`: all the game and mod file paths
* `init.race.RACES`: access to all the races currently loaded (also modded ones)
* `init.resources.RESOURCES`: all the available resources like wood and stone
* `init.settings.S`: contains information about the current game settings made in-game
* `init.sprite.SPRITES`: all the game sprites
* `init.sprite.UI.UI`: all the sprites used for the UI
* `init.tech.TECHS`: access to all the techs currently loaded (also modded ones)
* `init.constant.C`: contains constants with core game configs like the screen dimensions
* `util.text.D`: handles translations for GUIs
* `init.type.DISEASES`: contains information about all available diseases
* `integrations.INTEGRATIONS`: handles Steam integrations for e.g. achievements
* `menu.ScMain`: main menu you see when starting
* `script.SCRIPT`: interface for hooking into some of the game features
* `settlement.main.SETT`: access to everything in your settlement map, like the buildings or the terrain
* `settlement.stats.STATS`: game statistics like your population count or happiness
* `settlement.stats.law.LAW`: current status of your settlement crimes and prisoners etc.
* `settlement.stats.standing.STANDINGS`: happiness of your citizens
* `settlement.room.main.util.RoomsCreator`: for creating and registering custom rooms with custom logic
* `snake2d.util.gui.GuiSection`: container for arranging and displaying your ui elements
* `snake2d.CORE`: initializes and controls game core functionalities like the render and update loops
* `snake2d.Updater`: game update and render loops
* `view.main.VIEW`: contains all the UIs (world, settlement, battle)

## Conventions

### UPPERCASE classes

Classes or interfaces written only in UPPERCASE characters like `game.GAME` or `settlement.main.SETT` are the entry point for a lot of the game functionality.
They provide methods or APIs to interact with deeper systems and often hold the game data.

### Custom data types

Instead of the standard Java types like `java.util.ArrayList` the game uses a custom `snake2d.util.sets.ArrayList`.
The game has a custom type for almost every Java standard type.
This is mostly done because of performance reasons and to have more control. 
This means you cannot use Java standard features like streams that easily and may have to convert the types when necessary.

### Member variables prefixed with "¤¤"

The "¤¤" symbols are used as markers for injecting translated text into the class.
The text in there appears in the game GUI.
They are read from the `data.zip/data/assets/text/dictionary/Dic.txt` file.

The entry `game.save.GameSaver` for example, refers to the class with the same package and name.

```
game.save.GameSaver: {
	save: "Saving",
	savingDisk: "Saving to disk, please wait.",
},
```

```java
public class GameSaver {
    
    // ...

    private static CharSequence ¤¤save = "Saving";
    private static CharSequence ¤¤savingDisk = "Saving to disk, please wait.";
}
```

The injection is triggered in a static block via the `init.text.D` class. 
This is done before any non-static code is executed.

```java
public class GameSaver {

    // ...

    static {
        D.ts(GameSaver.class);
    }
}
```

### Closed-closed principle and class visibilities

In contradiction to the [open–closed principle](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle), Songs of Syx uses a more closed approach.
Classes often exist in a package and are only accessible from within this package. 
So when writing a class for your mod, you often cannot easily extend from a certain vanilla class. 
You have to instead move it into the same package space as the vanilla class you are trying to extend from.
See: [Access game code](access_game_code.md#extending)

Code also isn't structured in a way that makes it easy to hook into or add functionality to it.
There are a few exceptions like adding a new room.
You may have to overwrite whole game classes
or rely on somewhat more unconventional methods if you want to add something in most cases.
See: [Access game code](access_game_code.md)

## GUI

Most of the game GUI is located in the `view` package. 
It is custom written for Songs of Syx and uses [Swing](https://en.wikipedia.org/wiki/Swing_(Java)) under the hood.
See [Add UI element](add_ui_element.md) for how to add a new element.

There are different interfaces providing basic APIs:

* `snake2d.util.gui.clickable.RENDEROBJ`: The most basic interface to render a GUI element.
* `snake2d.util.gui.clickable.HOVERABLE`: Provides mouse hovering functionality.
* `snake2d.util.gui.clickable.CLICKABLE`: Provides mouse clicking functionality.
* `snake2d.util.gui.clickable.GUI_BOX`: Used for tooltips and other hover elements.

The `snake2d.util.gui.GuiSection` is used as a container for multiple UI elements. 
There you can arrange elements and build a layout with. It acts like a `<div>` in HTML.
GUI elements like a simple Button `util.gui.GButt.Panel` are in the `util.gui` package.

```java
import snake2d.util.datatypes.DIR;
import snake2d.util.gui.GuiSection;
import util.gui.misc.GButt;

public class MyOwnView extends GuiSection {

    public MyOwnView() {
        GButt.Panel button = new GButt.Panel("Button");

        // add the button without any arrangement 
        add(button);

        // add the button under the last element with 5px space between them
        addDown(5, button);

        // add the button under the last element horizontally centered with 5px space between them
        addDownC(5, button);

        // add the button right next to the last element with 5px space between them
        addRight(5, button);

        // add the button right next to the last element horizontally centered with 5px space between them
        addRightC(5, button);

        // add the button vertically and horizontally centered 
        addCentredX(button, body().cX());

        // add another GuiSection above this one with two buttons
        GuiSection section = new GuiSection();
        section.addRightC(0, button);
        section.addRightC(0, button);
        addRelBody(50, DIR.N, section);
        
        // ...
    }
}
```