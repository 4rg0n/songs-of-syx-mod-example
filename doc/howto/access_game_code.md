# Access game code

The way the game code is structured makes loose coupling of your mod classes to the games classes through
dependency injection and / or inversion of control very hard until impossible. Here are some methods and tips:

## Add an API layer

The game code can drastically change from each major version. Because of this, it's a wise idea to be prepared for change.
The easiest way would be to encapsulate code, which accesses game functionality into its own layer. This doesn't always make sense (e.g. UI code),
but should be applied if possible.

Here an example API for manipulating the games settlement sound:

```java
package mymod.game.api;

public class SoundApi {
    public  Map<String, SoundSettlement.Sound> getSettlementSounds() {
        // code for reading all available settlements sounds from the game
    }

    public void setSoundsGainLimiter(SoundSettlement.Sound sound, int limit) {
        // code for manipulating their volume
    }
}
```

In this example I try to encapsulate the collection of data I need from the game and how to manipulate each sound.
Other code will then trigger the collection and manipulation. This way each component can act on their own and can be replaced.
It's still not perfect as we still rely on the game `SoundSettlement.Sound` object and it's functions, but it's better than nothing.


## Extending and inheritance

This is useful when you want to reuse a game element, but slightly modified. UI elements are a good example for this:

```java
package mymod.ui;

public class MyButton extends GButt.ButtPanel {
    // overwritten / custom code
}
```

Keep in mind that this is a strong and somewhat unpredictable link between your custom code and the game code. 
When the extended class changes its behavior in a game update, it can cause all sorts unwanted behavior or even crash the game. 

## Reflection

This is useful when you need access to something on runtime. 
Via [reflection](https://www.baeldung.com/java-reflection), you can circumvent most visibility problems and access private methods and members.

Let's say you want to access this private `Ground.all` field, which contains all available ground types:

```java
package settlement.tilemap;

public class Ground extends TileMap.Resource{
    private final ArrayList<GROUND> all = new ArrayList<GROUND>(15);
}
```

```java
package mymod;

import settlement.main.SETT;
import settlement.tilemap.Ground;

public class GroundBreaking {
    public static void doSmth() {
        try {
            Field allField = Ground.class.getField("all");
            allField.setAccessible(true); // this will make it readable

            ArrayList<GROUND> all = (ArrayList<GROUND>) allField.get(SETT.GROUND());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

Reflection is a mighty tool to do all sorts of "forbidden" things in Java. 
I would recommend reading some [external guides](https://www.baeldung.com/java-reflection) to get an overview of the capabilities.
Be prepared for handling exceptions when using this. Failed access to a game class shouldn't crash your mod and the game.


## Extending and replacing game classes

A lot of the games classes are package private and aren't [accessible](https://www.javatpoint.com/access-modifiers) from within your mods package.
For example your class is `my.awesome.mod.job.MyModJob` and you want to access the game class `settlement.job.Job`.
In that case you will not be able to `import` it into your mods package.

### Extending

The trick is to just put your classes into the same package as the class with private access:

```java
package settlement.job;

public class MyModJob extends Job {
    // ... do stuff here =)
}
```

### Replacing

With the same method it's possible to replace game classes with your own custom ones. Just copy it into the same package.

```java
package settlement.job;

public abstract class Job implements SETT_JOB {
    // ... custom stuff here =)
}
```

**LIMITATIONS**
Replacing code has its limitations. For example direct final declarations with simple types:

```java
package settlement.job;

public abstract class Job implements SETT_JOB {
    static final byte NOTHING = (byte) 0x07F;
}
```

When this is referenced by another class via `Job.NOTHING`, the reference won't be there anymore in the compiled Java Bytecode.
It will replace all references with its real value. In this case `0x07F`.
So if you want this to be changed, you would also need to replace all classes in your mod, where the reference is used.