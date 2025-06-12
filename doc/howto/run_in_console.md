# Run the game in a console and monitor logs without an IDE

Sometimes it's necessary to boot up the game and see the log output without using an IDE.
Because this skips the launcher process, make sure your mod is active.

**Windows**

1. Go to the game installation folder
2. RIGHT + LEFT CLICK on empty space in the folder; this should open a context menu
3. Choose "Open in Terminal" or "Open PowerShell window here"
4. Enter `.\jre\bin\java.exe -jar SongsofSyx.jar`
5. The game should start and it skips the launcher

**Linux**

1. Open a shell in the game installation folder or navigate to it
2. Enter `./jre/bin/java -jar SongsofSyx.jar`
3. The game should start and it skips the launcher