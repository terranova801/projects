# 6-Card Golf (Java Swing GUI)

This project is a Java Swing implementation of the card game *6-Card Golf*.  
You play against three NPCs (Tiger, Harold, and Steve). The goal is to finish each hole (round) with the **lowest score**.

Begin game by flipping a card in your hand to reveal. 
Then draw a card from either the discard pile(kitty) or the draw pile. You can choose to either place the drawn card into your hand, or discard the drawn card and end your turn
The game is over when a player flips over all of their cards.

---

## Running the Project

> ⚠️ Important: The game expects the working directory to be the **`GUIGame`** folder so that the image paths like `src/cards/…` resolve correctly.

### Option 1: Run from VS Code

1. Open VS Code.
2. Use **File → Open Folder…** and select the **`GUIGame`** folder (the one containing `src`, `bin`, etc.).
3. Make sure the Java extension pack is installed.
4. In the **JAVA PROJECTS** or **Explorer** view, open the `GolfGame` class.
5. Click the **Run** button (▶) next to the `main` method (or use `Run → Start Debugging` and choose `GolfGame` as the main class).
6. The GUI window should open, and the card images should load correctly from `src/cards`.

If your run configuration lets you set a working directory, make sure it is the **`GUIGame`** root folder.

### Option 2: Run from the Terminal

From inside the `GUIGame` folder:

```bash
# Compile
javac -d bin src/*.java

# Run (make sure GolfGame has a main method)
java -cp bin App
## Citations/Sources:

1) [Cardscans](https://cardscans.piwigo.com/) Source for some card image files
2) [Kenny Yip Coding](https://www.kennyyipcoding.com/) Tutorials used to develope the game design/ideas. I also sourced card files and naming convention from his site as well
3) [Youtube: Bro Code - Java Swing GUI Full Course](https://www.youtube.com/watch?v=Kmgo00avvEw&t=16971s) Lots of stuff
4) [Stack Overflow: How to make a JTable non-editable](https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable) Misc 
5) ChatGPT used for proofreading code, helping with debugging and formatting/editing README instructions only. NO CODE was used from ChatGPT

