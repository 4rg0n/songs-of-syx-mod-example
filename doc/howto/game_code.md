# Reading and understanding the Songs of Syx Source Code

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
* `game.events.EVENTS`: access to all the random events, which can happen during playing
* `game.events.FACTONS`: access to all the NPC factions and your player faction
* `init.Main`: logic for starting the game launcher process
* `init.MainLaunchLauncher`: just starts the Main 
* `init.MainProcess`: logic for starting the game menu process
* `init.paths.PATHS`: all the game and mod file paths
* `init.race.RACES`: access to all the races currently loaded (also modded ones)
* `init.tech.TECHS`: access to all the techs currently loaded (also modded ones)
* `init.text.D`: handles translations for GUIs
* `menu.ScMain`: main menu you see when starting
* `script.SCRIPT`: interface for hooking into some of the game features
* `settlement.main.SETT`: access to everything in your settlement map, like the buildings or the terrain
* `settlement.stats.STATS`: game statistics like your population count or happiness
* `settlement.room.main.util.RoomsCreator`: for creating and registering custom rooms with custom logic
* `snake2d.util.gui.GuiSection`: container for arranging and displaying your ui elements
* `snake2d.CORE`: initializes and controls game core functionalities like the render and update loops
* `snake2d.Updater`: game update and render loops

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

The injection is triggered in a static block via the `init.text.D` class: 

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

Code also isn't structured in a way that makes it easy to hook into or add functionality to it. 
You may have to overwrite whole game classes if you want to add something in most cases.
See: [Access game code](access_game_code.md)

## GUI

Most of the game GUI is located in the `view` package. 
It is custom written for Songs of Syx and uses [Swing](https://en.wikipedia.org/wiki/Swing_(Java)) under the hood.

There are different interfaces providing basic APIs:

* `snake2d.util.gui.clickable.RENDEROBJ`: The most basic interface to render a GUI element.
* `snake2d.util.gui.clickable.HOVERABLE`: Provides mouse hovering functionality.
* `snake2d.util.gui.clickable.CLICKABLE`: Provides mouse clicking functionality.
* `snake2d.util.gui.clickable.GUI_BOX`: Used for tooltips and other hover elements.

The `snake2d.util.gui.GuiSection` is used as a container for multiple UI elements. 
There you can arrange elements and build a layout with. It acts like a `<div>` in HTML.

GUI elements are in the `util.gui` package. 
They contain elements like the `util.gui.GButt.Panel` for rendering a simple button.



