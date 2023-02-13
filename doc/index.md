## In-game Debug and Development Tool

**(!)** You have to enable **Debug** and **Developer** in the games Launcher **Settings**.

```java
import view.interrupter.IDebugPanel;

public final class ExampleScript implements SCRIPT {
    
    //...

    @Override
    public SCRIPT_INSTANCE initAfterGameCreated() {
        // adds a command that opens the copier tool
        IDebugPanel.add("Copy Stuff", () -> VIEW.s().ui.copier.activate());

        return new Instance();
    }
}
```

You can find your custom added commands in-game in the `developer-tools`. Located in the upper right corner above the minimap :

![Developer Tools](img/dev-tools.png)

There's also another debug panel next to the status bars in the left hand upper corner with some additional commands:

![Debug Tools](img/debug-tools.png)

## Logging

```java
import game.GAME;
import snake2d.LOG;

public final class ExampleScript implements SCRIPT {
    
    //...

    @Override
    public SCRIPT_INSTANCE initAfterGameCreated() {
        // will log the message with a stacktrace
        GAME.Notify("MESSAGE");
        
        // will write a log entry
        LOG.ln("MESSAGE");

        // will write an error log entry
        LOG.err("MESSAGE");

        return new Instance();
    }
}
```

## Static game classes

These classes contain various accessible information and features for the game.

```java

public final class ExampleScript implements SCRIPT {

    //...

    @Override
    public SCRIPT_INSTANCE initAfterGameCreated() {

        snake2d.CORE;
        view.main.VIEW;
        integrations.INTEGRATIONS;
        
        game.GAME;
        game.VERSION;
        game.battle.BATTLE;
        game.faction.FACTIONS;
        game.time.TIME;
        game.tourism.TOURISM;
        
        settlement.main.SETT;
        settlement.stats.STATS;
        settlement.stats.law.LAW;
        settlement.stats.standing.STANDINGS;

        init.biomes.TERRAINS;
        init.boostable.BOOSTABLES;
        init.C;
        init.D;
        init.disease.DISEASES;
        init.paths.PATHS;
        init.race.RACES;
        init.resources.RESOURCES;
        init.sprite.SPRITES;
        init.sprite.UI.UI;
        init.tech.TECHS;
        
        world.army.WARMYD;
        world.army.WINDU;

        return new Instance();
    }
}
```

## Game and Mod initialization

The following resources are ready to be accessed in these methods: 

`view.sett.SettView` via `view.main.VIEW.s()` => `script.SCRIPT.SCRIPT_INSTANCE.update()`

## Extending and using game classes (since V64?)

A lot of the games classes are package private and aren't [accessible](https://www.javatpoint.com/access-modifiers) from within your mods package.
For example your class is `my.awesome.mod.job.MyModJob` and you want to access the game class `settlement.job.Job`.
In that case you will not be able to `import` it into your mods package. 

The trick is to just put your classes into the same package:

```java
package settlement.job;

public class MyModJob extends Job {
    // ... do stuff here =)
}
```

The way the game code is currently structured makes loose coupling of your mod classes to the games classes through 
dependency injection and / or inversion of control very hard until impossible. So you mostly rely on extending classes and inherit their functionality.
This has a higher risk for breaking your code when the game updates. Keep that in mind.


