import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
/**
 * controls the playlist and all songs inside it
 * handsle playing songs and searching etc.
 */
public class Playlist{
	private SongLinkedList songs;
	private Song currSong;
	private AudioPlayer audioPlayer;
/**
 * creates an empty playlit for us to work with, and the audioplayer
 */
	public Playlist(){
		this.songs = new SongLinkedList();
		this.currSong = null;
		this.audioPlayer = new AudioPlayer(this); //added "this" so that audioplayer takes constructor
	}
	/**
	 * adds song to end of playlist
	 * @param song
	 */
	public void addSong(Song song){
		songs.addLast(song);
	}
	public SongLinkedList getSongs(){
		return songs;
	}
	public void displayPlaylist(){
		songs.displayForward();
	}

	public String getTotalDuration(){
		int total = 0;
		for(int i = 0;i<songs.size();i++){
			total +=songs.getSong(i).getDuration();
		}
		int min = total/60;
		int sec = total%60;
		return String.format("%d:%02d",min,sec);
	}
	public void playCurrentSong(){
		if(currSong!=null){
			audioPlayer.playAudio(currSong);
		}
	}
	public void stopPlayback(){
		if(audioPlayer.isPlaying()){
			audioPlayer.stopAudio();
		}
		else{
			System.out.println("Not playing a song.");
		}
	}
	/**
	 * changes current song using its index in linkedlist
	 */
	public void setCurrentSong(int index){
		Song song = songs.getSong(index);
		if(song!=null){
			currSong = song;
		}	
		else{
			System.out.println("Song was unfortunately not found.");
		}

	}

	/**
	 * is able to jump to the song that it wants to get to 
	 * will check bounds, then if its playing stop that audio
	 * then get the song of that index and play that song
	 * @param index
	 */
	public void jumpToSong(int index){
		if(index<0||index>=songs.size()){
			return;
		}
		if(audioPlayer.isPlaying()){
			audioPlayer.stopAudio();
		}
		currSong=songs.get(index);
		audioPlayer.playAudio(currSong);
	}


	public Song getCurrentSong(){
		return currSong;
	}
	public void loadPlaylistFromCSV(String fileN){
		try{
			FileInputStream fs = new FileInputStream(fileN);
			Scanner scnr = new Scanner(fs);
			if(scnr.hasNextLine()){ 
				scnr.nextLine(); 
			}
	
			while(scnr.hasNextLine()){
				try{

					String line = scnr.nextLine();
					line = line.replace("\"", "");
					String[]parsed = line.split(",");
					String title = parsed[0];
					String artist = parsed[1];
					int duration = Integer.parseInt(parsed[2]);
					String genre = parsed[3];
					String filePath = parsed[4];
					if(title.isEmpty()||artist.isEmpty()||duration<=0||genre.isEmpty()||filePath.isEmpty()){
						System.out.println("Invalid song data.... skipping line.");
						continue;
					}
					File audioFile = new File(filePath);
					if(!audioFile.exists()){

						System.out.println("Audio file Does NOT exist.... skipping..");
						continue;
					}
					Song song = new Song(title, artist, duration, genre, filePath);
					addSong(song);
				}
				catch(NumberFormatException e){
					System.out.println("Invalid duration format.. skipping line.");
				}
				catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Skipping line.. missing information.");
				}
				

			}
			scnr.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error finding the file.");
		}

	}
	public void savePlaylistToCSV(String fileN){
		try{
			FileOutputStream fs = new FileOutputStream(fileN);
			PrintWriter writer = new PrintWriter(fs);
			writer.println("title,artist,duration,genre,filePath");
			for(int i = 0;i<songs.size();i++){
				Song song = songs.getSong(i);
				writer.println(song.getTitle()+","+song.getArtist()+","+song.getDuration()+","+song.getGenre()+","+song.getFile());
			}
			writer.close();
		}
		catch(IOException e){
			System.out.println("Error writing in the file..");
		}
		

	}
	public void playNext(){
		for(int i=0;i<songs.size();i++){
			if(songs.getSong(i)==currSong){
				if(i+1<songs.size()){
					if(audioPlayer.isPlaying()){
						audioPlayer.stopAudio();
					}
					currSong = songs.getSong(i+1);
					audioPlayer.playAudio(currSong);
				}
				else{
					System.out.println("There are no songs after this...");
				}
				return;
			}
		}
	}
	public boolean hasSongs(){
		if(songs.isEmpty()==true){
			return false;
		}
		return true;
	}
	public Song findSongByTitle(String title){
		for(int i = 0;i<songs.size();i++){
			Song song = songs.get(i);
			if(song.getTitle().equalsIgnoreCase(title)){
				return song;
			}
		}
		return null;
	}
	public Playlist findSongsByArtist(String artist){
		Playlist copy = new Playlist();
		for(int i = 0;i<songs.size();i++){
			Song song = songs.get(i);
			if(song.getArtist().equalsIgnoreCase(artist)){
				copy.addSong(song);
			}
		}
		return copy;
	}
	public void getPlaylistStatistics(){
		System.out.println("Total songs: " + this.songs.size() + " | Duration total: " + getTotalDuration());
	}
	public void playFromStart(){
		if(hasSongs()==true){
			if(audioPlayer.isPlaying()){ //i need to stop if it's playing a song
				audioPlayer.stopAudio();
			}
			currSong = songs.get(0);
			audioPlayer.playAudio(currSong); //turns index to 0 and then switches the audio to play that
		}
		else{
			System.out.println("playlist is empty...");
		}
	}
	public void playRandom(){
		if(hasSongs()== true){
			if(audioPlayer.isPlaying()){
				audioPlayer.stopAudio();
			}
			currSong = songs.get((int) (Math.random()*songs.size()));
			audioPlayer.playAudio(currSong);
		}
		else{
			System.out.println("PLAYLIST IS EMPTY...");
		}
	}
	public void pausePlayback(){
		audioPlayer.paused();
	}
	public void resumePlayback(){
		audioPlayer.resume();
	}
	public void setVolume(String lev){
		audioPlayer.setVolume(lev);
	}
	public boolean hasNext(){
		if(hasSongs()==true){
			for(int i = 0;i<songs.size();i++){
				if(songs.get(i)==currSong){
					if(i+1<songs.size()){
						return true;
					}
				}
			}
		}
		return false;
	}
	public Song nextSong(){
		if(hasNext()==true){
			for(int i = 0;i<songs.size();i++){
				if(songs.get(i)==currSong){
					currSong = songs.get(i+1);
					return currSong;
				}
				
			}
		}
		return null;
	}
	public boolean hasPrevious(){
		if(hasSongs()==true){
			if(songs.size()>1){
				for(int i = 0;i<songs.size();i++){
					if(songs.get(i)==currSong){
						if(i>0){
							return true;
						}
					}
				}
			}
			
		}
		return false;
	}
	public Song previousSong(){
		if(hasPrevious()==true){
			for(int i = 0;i<songs.size();i++){
				if(songs.get(i)==currSong){
					currSong = songs.get(i-1);
					return currSong;
				}
			}
		}
		return null;
	}

}