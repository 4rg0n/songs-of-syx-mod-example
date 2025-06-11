# Access game code

The way the game code is structured makes loose coupling of your mod classes to the game classes through
dependency injection and / or inversion of control very hard until impossible.
Here are some tips on how you can extend and alter the game code.
I will refer a lot to methods used in so-called "[Monkey Patching](https://www.baeldung.com/java-monkey-patching)" here.


## Add an API layer

The game code can drastically change from each major version. Because of this, it's a wise idea to be prepared for change.
The easiest way would be to encapsulate code, which accesses game functionality into its own layer. This doesn't always make sense (e.g. UI code),
but should be applied if possible.

Here is an example API for manipulating the games settlement sound:
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

In this example, I try to encapsulate the collection of data I need from the game and how to manipulate each sound.
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

## Aspect Oriented Programming

AOP is a special way (paradigm) to write code.
It allows hooking into all sorts of code executions.
It requires a special dependency like [AspectJ](https://www.baeldung.com/aspectj).
This comes with a downside though.
As soon as another mod uses the same technique with the same AspectJ dependency, it will conflict.
A common code mod with AspectJ in it would be necessary where both mods depend on.

I experimented with it and got it running on my machine within the [Mod SDK](https://github.com/4rg0n/songs-of-syx-mod-more-options/tree/main/mod-sdk/src/main/java/com/github/argon/sos/mod/sdk),
but there seems no current use case for it. As this is a very invasive dependency, I didn't follow it any further for now.
You can still try and use it though :)


## Dynamic Proxies

[Dynamic proxies](https://www.baeldung.com/java-dynamic-proxies) in Java are a way to do something before and after a method call.
Java provides an [InvocationHandler](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html) interface for this case.
You can create and destroy these proxies whenever you need to on runtime. 
That's why they are "dynamic".

## Decorator Pattern

A [Decorator](https://refactoring.guru/design-patterns/decorator) is one of many [design patterns](https://en.wikipedia.org/wiki/Software_design_pattern) used when writing code. 
It can do the same as a [dynamic proxies](#dynamic-proxies).
You just cannot alter them on runtime. They are "static" proxies.

## Extending and replacing game classes

A lot of the games classes are package private and aren't [accessible](https://www.javatpoint.com/access-modifiers) from within your mod package.
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
Replacing code has its limitations. For example, direct final declarations with simple types:

```java
package settlement.job;

public abstract class Job implements SETT_JOB {
    static final byte NOTHING = (byte) 0x07F;
}
```

When this is referenced by another class via `Job.NOTHING`, the reference won't be there anymore in the compiled Java Bytecode.
It will replace all references with its real value. In this case `0x07F`.
So if you want this to be changed, you would also need to replace all classes in your mod, where the reference is used.