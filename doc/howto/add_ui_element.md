# Add a UI Element

There are multiple ways to add a UI Element to the game. 

## [Reflection](access_game_code.md#reflection)

This method requires some more preparation. This is done, so you'll have an easier time when the game decides to change the vanilla code you are accessing. 
You can then simply rewrite just a few methods and the rest of your code still functions.

**Example:** Adding a button into the top bar.

There are at least three instances of the top bar in different UIs:

* Battle UI
* World UI
* Settlement UI

If you want your button to be present in all of them, you also have to inject them into all 3 of them.

I would suggest you create a `ReflectionUtil` class with some `static` methods helping you with all this nasty reflection stuff.
Of course, I prepared one just for you. This example will handle reading values from a private and therefore inaccessible member variable.

```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtil {
    
    public static Optional<Field> getDeclaredField(String fieldName, Object instance)  {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            return Optional.of(field);
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> getDeclaredFieldValue(Field field, Object instance) {
        field.setAccessible(true);

        try {
            return Optional.ofNullable((T) field.get(instance));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> getDeclaredFieldValue(String fieldName, Object instance) {
        return getDeclaredField(fieldName, instance.getClass()).map(field ->
            (T) getDeclaredFieldValue(field, instance).orElse(null)
        );
    }
}
```

Now you can do something similar for accessing stuff in the game UI. 
In this case a `Singleton` pattern fits better than static methods. 
It'll give more flexibility in the future. 
The code actually looks a lot, but is basically just three methods repeated three times. 
One method for each of the three top panels.

```java
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GameUiApi {

    @Getter(lazy = true)
    private final static GameUiApi instance = new GameUiApi();

    public SettView settlement() {
        return VIEW.s();
    }

    public WorldView world() {
        return VIEW.world();
    }

    public SBattleView battle() {
        return VIEW.s().battle;
    }

    public <T> Optional<T> findUIElementInSettlementView(Class<T> clazz) {
        return ReflectionUtil.getDeclaredFieldValue("inters", settlement().uiManager)
            .flatMap(inters -> extractFromIterable((Iterable<?>) inters, clazz));
    }

    public <T> Optional<T> findUIElementInWorldView(Class<T> clazz) {
        return ReflectionUtil.getDeclaredFieldValue("inters", world().uiManager)
            .flatMap(inters -> extractFromIterable((Iterable<?>) inters, clazz));
    }

    public <T> Optional<T> findUIElementInBattleView(Class<T> clazz) {
        return ReflectionUtil.getDeclaredField("inters", battle().uiManager)
            .flatMap(inters -> extractFromIterable((Iterable<?>) inters, clazz));
    }

    public void injectIntoUITopPanels(RENDEROBJ element) {
        injectIntoBattleUITopPanel(element);
        injectIntoWorldUITopPanel(element);
        injectIntoSettlementUITopPanel(element);
    }

    public void injectIntoWorldUITopPanel(RENDEROBJ element) {
        Object object;

        try {
            object = findUIElementInWorldView(UIPanelTop.class)
                .flatMap(uiPanelTop -> ReflectionUtil.getDeclaredFieldValue("right", uiPanelTop))
                .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Could not inject ui element into World UIPanelTop#right", e);
        }

        if (object == null) {
            throw new RuntimeException("Could not find ui element in World UIPanelTop");
        }

        GuiSection right = (GuiSection) object;
        right.addRelBody(8, DIR.W, element);
    }

    public void injectIntoSettlementUITopPanel(RENDEROBJ element) {
        Object object;

        try {
            object = findUIElementInSettlementView(UIPanelTop.class)
                .flatMap(uiPanelTop -> ReflectionUtil.getDeclaredFieldValue("right", uiPanelTop))
                .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Could not inject ui element into Settlement UIPanelTop#right", e);
        }

        if (object == null) {
            throw new RuntimeException("Could not find ui element in Settlement UIPanelTop");
        }

        GuiSection right = (GuiSection) object;
        right.addRelBody(8, DIR.W, element);
    }

    public void injectIntoBattleUITopPanel(RENDEROBJ element) {
        Object object;

        try {
            object = findUIElementInBattleView(UIPanelTop.class)
                .flatMap(uiPanelTop -> ReflectionUtil.getDeclaredFieldValue("right", uiPanelTop))
                .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Could not inject ui element into Battle UIPanelTop#right", e);
        }

        if (object == null) {
            throw new RuntimeException("Could not find ui element in Battle UIPanelTop");
        }

        GuiSection right = (GuiSection) object;
        right.addRelBody(8, DIR.W, element);
    }

    private <T> Optional<T> extractFromIterable(Iterable<?> iterable, Class<T> clazz) {
        for (Object inter : iterable) {
            if (clazz.isInstance(inter)) {
                log.debug("Found ui element %s", clazz.getSimpleName());
                return Optional.of((T) inter);
            }
        }

        return Optional.empty();
    }
}
```

Now that you have that, you can finally inject your button into the UI:

```java
public final class YourScript implements SCRIPT {
    @Override
    public void onViewSetup() {
        GButt.Panel button = new GButt.Panel("BUTTON");
        button.clickActionSet(() -> {
            System.out.println("BUTTON clicked!"); 
        });

        GameUiApi.getInstance().injectIntoUITopPanels(button);
    }
}
```

## [Replacing](access_game_code.md#replacing)

It's not always possible to access UI elements via reflection. In this case you will have to replace the vanilla game class with your own one. 
Replacing code in this way decreases your mods compatibility to other mods and increases the coupling to the game code itself. 
This means when the vanilla code you changed in your mod changes in a newer version, you will have to redo your changes in the new code than.

To make this work, you have to copy the class you want from the vanilla game code and place it in the exact the same package with the exact same name into your mod.
Adding a new main menu entry in `ScMain` for example:

```java
package menu;

public class ScMain implements SC {
    //...
    
    private GuiSection getFirst(Menu menu) { 
        //...        
        
        // MODDED: adds another menu entry opening the credits
        text = getNavButt("Example Entry (Credits)");
        text.clickActionSet(new ACTION() {
            @Override
            public void exe() {
                menu.switchScreen(menu.credits);
            }
        });
        current.addDown(8, text);
        
        //...
    }
    
    //...
}
```

This part is modified and adds a new entry to the game main menu.