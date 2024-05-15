# Make your own mod

## Maven

At first, you want to edit the information in the [pom.xml](../../pom.xml):

```xml
<groupId>org.example</groupId>
<artifactId>songs-of-syx-mod-example</artifactId>
<version>1.0.0</version>
```
See [Maven naming conventions](https://maven.apache.org/guides/mini/guide-naming-conventions.html).

The `<groupId>` is usually something like your company. Best to use only lowercase letters here.
I'm going mostly with something like: `com.github.argon`.

The `<artifactId>` is the name of your mod in lowercase letters. You can separate words with a dash.

The `<version>` is ofcourse the version of your mod. You can start with a `0.0.1` or `1.0.0`.
The first number is your `major` version. You should increase this if there are breaking changes.
The second is the `minor` version. This is increased whenever you'll add a new feature. 
The third is the `bugfix` version. You'll increase this when you fix a bug.
See also [Semantic Versioning](https://semver.org/).

## Mod Info

These settings will be copied into the [_Info.txt](../../src/main/resources/mod-files/_Info.txt). 
The game will read this to check whether your mod is compatible with the game version and to display various information.

```xml
<mod.version>${project.version}</mod.version>
<mod.name>Example Mod</mod.name>
<mod.description>This is an example mod made by Jake the game developer.</mod.description>
<mod.author>Jake</mod.author>
<mod.info>Example</mod.info>
<game.version.major>66</game.version.major>
<game.version.minor>12</game.version.minor>
```

## Java Package

:warning: This is only required when your mod will have code. Else you could just delete the [src/main/java/your](../../src/main/java/your) and [src/test/java/your](../../src/test/java/your) folders.

The example mod uses `your.mod` as package in [src/main/java](../../src/main/java) folder. 
You should refactor the names to your own package e.g. `com.github.argon.mymod`.
See also [Java package naming conventions](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html).

The `package` is also referenced in the Java `.class` files. So you'll have to adjust them there too.

Your `*Script` classes should look like:

`MainScript.java`
```java
@NoArgsConstructor
public final class MainScript implements SCRIPT {

	private final INFO info = new INFO("Your Mod Name", "Your Description");

	@Override
	public CharSequence name() {
		return info.name;
	}

	@Override
	public CharSequence desc() {
		return info.desc;
	}

	@Override
	public void initBeforeGameCreated() {}

	@Override
	public SCRIPT_INSTANCE createInstance() {
		return new InstanceScript();
	}
}
```

`InstanceScript.java`
```java
@NoArgsConstructor
final class InstanceScript implements SCRIPT.SCRIPT_INSTANCE {
    
    @Override
    public void save(FilePutter file) {}
    
    @Override
    public void load(FileGetter file) {}
    
    @Override
    public void update(double ds) {}
}
```

## Start modding!
