This is a Java music player that uses a custom doubly linked list to store and manage songs. It includes a graphical user interface (GUI) created using Java Swing.
Features
- Add, remove, and edit songs.
- Play, pause, and stop music.
- Skip to the next or previous song.
- Save and load playlists using CSV files.
- Custom doubly linked list.
- JUnit tests.
  
How to Run
Step 1: Compile the Java files

Open your terminal in the project folder and enter:

javac AudioPlayer.java MainGUI.java Playlist.java Song.java SongLinkedList.java SongNode.java

Step 2: Run the program

java MainGUI

Step 3: Load your music
- Enter the path to an existing CSV playlist file.
- Click "Load CSV" to open the music player.
- Click "Add a song!" to select your own WAV files.
- Use the buttons to play, pause, stop, or skip songs.
Note: Music files are not included. Users must provide their own WAV files. The CSV file must reference valid audio file paths.
Technologies Used
- Java
- Java Swing
- Doubly Linked List
- File I/O
- JUnit


Author
Jayson Rojas
University of San Francisco — Computer Science
