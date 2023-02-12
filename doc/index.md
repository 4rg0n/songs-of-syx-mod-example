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
        
        settlement.entity.animal.ANIMALS;
        settlement.entry.SENTRY;
        settlement.environment.ENVIRONMENT;
        settlement.job.JOBS;
        settlement.main.SETT;
        settlement.path.PATHING;
        settlement.stats.STANDING;
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

