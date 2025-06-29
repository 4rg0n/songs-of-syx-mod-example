# Setup Intellij IDEA

1) Download and install [Intellij IDEA Community Edition](https://www.jetbrains.com/idea/download) (not Ultimate)
2) Create a `File->New->Project from Version Control` via:

   ![New Project from Version Control](../img/intellij-setup/new_project.png)
3) Enter the git url `https://github.com/4rg0n/songs-of-syx-mod-example.git` and "clone" the repository: 

   ![clone](../img/intellij-setup/project_from_version_control.png)
4) Goto `File->Project Structure` and choose to download a JDK:
 
   ![JDK Project Structure](../img/intellij-setup/jdk_project_structure.png)
5) Choose `Version 1.8` and a Vendor of your choice and download it: 

   ![JDK Download](../img/intellij-setup/jdk_download.png)
6) Check the [README Prerequisite](../../README.md#prerequisite), for the correct game installation paths
7) On the right hand side click on the `M` icon and doubleclick the maven `validate` command: 

   ![Maven](../img/intellij-setup/maven.png)
8) After that run the `install` command

## Problems?

Check [](../../README.md#faq--troubleshooting)

## Reading the games source code

In the project tree on the left-hand site, you will see **External Libraries** at the bottom.
Under this you will find a "Maven" dependency called something like `com.songsofsyx:songsofsyx:XX.XX`.

![](../img/intellij-setup/source_code.png)

There you can browse the game source code.
See: [Reading and understanding the Songs of Syx Source Code](game_code.md) for some more information.


## Running the game with the mod

There are three `.xml`files in the `.run/` folder:

* `Main` launches the game directly
* `MainLaunchLauncher` starts the game launcher
* `DEBUG` starts the game with debug capabilities

You may want to edit the `WORKING_DIRECTORY`and anything in `<classpathModifications>` to your local game installation path.
It's default set to: `C:/Program Files (x86)/Steam/steamapps/common/Songs of Syx`.

For the future: Your package name may also differ in the `PATTERN` option.
It's set default to: `your.mod.*`

They should be automatically available [in the IDE](https://www.jetbrains.com/help/idea/run-debug-configuration.html): 

![Run Commands](../img/intellij-setup/run_commands.png)

Run the game at first through the `MainLaunchLauncher` and enable the `Example Mod` in the launcher.
Once the mod is enabled, you can skip the launcher by running the `Main` or `DEBUG` command.

