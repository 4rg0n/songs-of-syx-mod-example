# Songs of Syx Modding Guide and Template for V70

This project is intended for people who want to start modding for the game [Songs of Syx](https://store.steampowered.com/app/1162750/Songs_of_Syx/).
It contains [documentation](doc/index.md) on general modding techniques and serves as a starting point if you want to create code mods.
The automated build pipeline will take care of compiling, packaging and installing the mod into the games mod folder.
So the game will always load your latest changes you made in your mod without manually copying files.

You will need a **Java 21 JDK** for developing.

## Content

* [General Modding Information](doc/index.md)
  * [Mod Folder Structure](doc/index.md#mod-folder-structure) 
  * [Game Boosters](doc/index.md#game-boosters)
  * [Scripting / Coding](doc/index.md#scripting--coding)
    * [In-game Debug and Development Tool](doc/index.md#in-game-debug-and-development-tool)
    * [Logging](doc/index.md#logging)
    * [Game and Mod Initialization](doc/index.md#game-and-mod-initialization)
* [Example Mod Structure](doc/example)
* [Project Setup](doc/howto/intellij_setup.md)
* [Getting Started](#getting-started)
* [Mod SDK (Optional)](#mod-sdk-optional)
  * [Code](https://github.com/4rg0n/songs-of-syx-mod-more-options/tree/main/mod-sdk)
  * [Documentation](https://github.com/4rg0n/songs-of-syx-mod-more-options/tree/main/mod-sdk/src/main/java/com/github/argon/sos/mod/sdk#mod-sdk)
* [Make your own Mod](doc/howto/make_your_own_mod.md)
* [Publish your Mod](#publish-your-mod)
* [How-to Guides](doc/howto/index.md)
  * [Run in Console](doc/howto/run_in_console.md) 
  * [Understand Game Code](doc/howto/game_code.md) 
  * [Access Game Code](doc/howto/access_game_code.md) 
  * [Modding Strategy](doc/howto/modding_strategy.md) 
  * [Add an UI Element](doc/howto/add_ui_element.md) 
* [Project Build Commands](#project-build-commands)
* [Mod Info / Build Settings](#mod-info--build-settings)
* [Debugging your Mod](#debugging)
* [Other Modding Resources](#other-modding-resources)

## General Modding Information

See [Documentation](doc/index.md)

## Setup for Intellij IDEA

See [Setup IntelliJ IDEA](doc/howto/intellij_setup.md)

## Getting Started

:information_source: [Maven](https://maven.apache.org/) is required to compile, package and install the mod.
When using [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), Maven is already bundled within.

You can run the Maven commands via the `m` icon on the right-hand side in the IntelliJ IDEA UI:

![Maven UI](doc/img/maven.png)

#### 1. (**mvn validate**) Install the game jar file as [local maven dependency](https://www.baeldung.com/maven-local-repository) by copying it from the game installation folder.
```
mvn validate 
```

:information_source: You only have to run `validate` once (or when the games got an update you need)

#### 2. (**mvn install**) Compile mod source code; build jar file; prepare mod file structure; copy mod files and jar to game mod directory
```
mvn install 
```

#### 3. Run the game, and you should see a `Example Mod` mod. Activate it and launch.

# Run the game from within your IDE (IntelliJ IDEA)

There are three different "Run Configurations" in the `.run` folder:

* `DEBUG` for running the game with debug capabilities via the little green bug icon
* `Main` for running the game and skipping the launcher
* `MainLaunchLauncher` for running the game with launcher

You may have to *edit the game installation paths* in these files.
Intellij should automatically recognize these files and add them to your "Run Configuration" selection.
It is usually found in the upper right-hand corner.
You can select the command via the dropdown menu and run them with the play icon.
The green bug icon will run the game with debug capabilities. This will only work with the `DEBUG` run configuration though.

**DEBUG.run.xml**
```xml
    <classpathModifications>
      <entry path="C:\Program Files (x86)\Steam\steamapps\common\Songs of Syx\base\script\Examples.jar" />
      <entry path="C:\Program Files (x86)\Steam\steamapps\common\Songs of Syx\base\script\Tutorial.jar" />
      <entry path="$PROJECT_DIR$\target\Example Mod.jar" />
    </classpathModifications>
```

For debugging Java code, some additional **classpathModifications** are necessary. 
These can differ on your system. 
Also, the name of your built `*.jar` file may be different.

## Mod SDK (Optional)

:information_source: The Mod SDK is still somewhat experimental. 
I'm using it for my [More Options](https://github.com/4rg0n/songs-of-syx-mod-more-options) mod. 
That's also the place where currently the [code](https://github.com/4rg0n/songs-of-syx-mod-more-options/tree/main/mod-sdk) and [documentation](https://github.com/4rg0n/songs-of-syx-mod-more-options/tree/main/mod-sdk/src/main/java/com/github/argon/sos/mod/sdk#mod-sdk) of it lives.

See [Mod SDK Setup](doc/howto/mod_sdk_setup.md)

## Start making your own mod

See [make your own mod](doc/howto/make_your_own_mod.md).

## Publish your Mod

There's a maven profile `mods-uploader` you can use to automatically copy and clean your files into the mod uploader.
The directory is configurable via the `<game.mod.uploader.directory>` property in the `<profiles>` in the [pom.xml](pom.xml).

Default set to:

* **Windows:** `${user.home}/AppData/Roaming/songsofsyx/mods-uploader`
* **Linux:** `${user.home}/.local/share/songsofsyx/mods-uploader`

Will copy the mod files into the [Steam Workshop Uploader](https://cdn.discordapp.com/attachments/664478122347069441/1023961932476186704/Songs_of_Syx_Workshop_Uploader.zip?ex=67ef419c&is=67edf01c&hm=9c4cb16f1e6b6007000c8fb07392bc997e44f880614340bc2035d1d4431b4da7&) directory.

```
mvn install -P mods-uploader
```

Will remove the mod files from the mod uploader. For updating the mod files.

```
mvn clean -P mods-uploader
```

### Steam

* [Official How to up- and download mods using Steam Workshop](https://steamcommunity.com/sharedfiles/filedetails/?id=2229540768)
* [(Discord) Steam Workshop Uploader](https://cdn.discordapp.com/attachments/664478122347069441/1023961932476186704/Songs_of_Syx_Workshop_Uploader.zip?ex=67adff5c&is=67acaddc&hm=e6c3e2e9cb6e365e61cb69c0cb242be13f2856cf06391d46541f842a6b3902f7&)


## How-to Guides

See [How-to Guides](doc/howto/index.md)

## Project Build Commands

:information_source: Installs only the games `SongsOfSyx.jar` and `info/SongsOfSyx-sources.jar` as a dependency, and validate whether it was successful.
```
mvn validate
```

:warning: This is required or the project won't find the games code.

:information_source: Building the mod only into `target/out`:
```
mvn package
```

The source code of the mod will be copied into e.g. `target/out/songs-of-syx-mod-example/V70/script/_src`.

:information_source: Build and copy the output into the games mods folder (excluding `_src`):
```
mvn install
```

:warning: The game mod folder location varies on each OS.
There are maven profiles "windows" and "linux". The "windows" profile is the default.
Maven should detect when you are building on a Linux OS and switch to the "linux" profile (not tested).
You can force a profile with e.g.

```
mvn install -P linux
```

:information_source: Deletes the `target` directory containing the packaged mod files and removes the mod from the games mod directory.
```
mvn clean
```

## Mod Info / Build Settings

In the [pom.xml](pom.xml) you will find `<properties>` where you can change information about the mod.
There you can also change the `<game.version.major>` property to your used game version.
The `<game.version.minor>` property is only important when your mod really depends on stuff in this version and isn't compatible with lower versions.

Files (e.g. assets) for the mod are located in `src/main/java/resources/mod-files` and will be copied in the `package` phase.

## Debugging

You can enable **Debug Mode** and **Developer Mode** in the game launcher **settings**.
You will get more detailed logs and in-game developer tools for testing.

### Eclipse

* Add a new [Run Configuration](https://www.subjectcoach.com/tutorials/detail/contents/beginners-guide-to-eclipse-ide/chapter/working-with-run-configurations).
* Set the **main class** name to `init.MainLaunchLauncher`.
* In the tab **Arguments** set the **working directory** to your game installation folder e.g. `C:/Program Files (x86)/Steam/steamapps/common/Songs of Syx`.

### Testing

There's [JUnit 5](https://junit.org/junit5/) with [AssertJ](https://assertj.github.io/doc/) and [Mockito 4](https://site.mockito.org/) for testing your code.

### Developing Tips

See [doc/index.md](doc/index.md).

## Other Modding Resources

* [Discord](https://discord.com/eacfCuE)
* [Make a mod](doc/res/MAKE_A_MOD.txt) (OUTDATED)
* [Script Modding](https://docs.google.com/document/d/1FVOtfr3Y-cxH2Gw-i-OqW3Vbp0MPJp0xSyQ80UoCABE/edit) (OUTDATED)
* [Modding Guide](https://drive.google.com/file/d/1_OesG68HtJ4CwyHK7M72hQDOaCjeqgqT/view) (OUTDATED)









