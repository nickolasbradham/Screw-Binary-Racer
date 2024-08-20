This util is for solving the Binary Racer minigame, and more specifically, getting the acheivement in the video game Turing Complete (because let's be real, the timer is dumb).

![image](https://github.com/user-attachments/assets/e7d48200-85b2-4a63-a8f4-a8ea7cd2d925)
# How to use this tool
## First set the correct coordinates for the first four boxes.
This game clicks on the screen to enter things into the game so we have to tell the program where to click. You can use [this program](https://github.com/nickolasbradham/Java-Mouse-and-Color-Util) to get the screen X and Y coordinates.

**Set the game to Window mode in the settings.**
- `128 Bit X` is the X coordinate of the 128 bit (farthest left) toggle in game.
- `128 Bit Y` is the Y coordinate of the 128 bit toggle in game.
- `64 Bit X` is the X coordinate of the 64 bit toggle in game.
- `Submit Y` is the Y cordinate of the Submit button in game.
- `Value` is the current value the game is asking for (during the minigame).
## Solving Binary Racer
Once the program is setup you can start Binary Racer, switch back to this util and start entering the values the game is looking for into the value field.

Pro tip: Alt + Tab on the keyboard can quickly switch between your open windows.

Every time you hit enter (or press the submit button), the program will click into the game, toggle the necessary bits, click submit, refocus itself and select the text in the value field, ready for you to enter the next value.

As long as the window isn't obstructing the value or buttons in game and you can type the value the game shows and hit enter before the timer runs out, this should be easy.
