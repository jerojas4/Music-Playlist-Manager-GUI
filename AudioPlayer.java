import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * this class controls the audio stuff FOR the playlist
 * can do a lot of controls like play stop or resume etc.
 * 
 * 
 */
public class AudioPlayer{
	private Clip clip;
	private boolean paused;
	private Playlist playlist;
	public AudioPlayer(Playlist playlist){
		this.clip = null;
		this.paused = false;
		this.playlist = playlist; //keep the playlist so I can move to the next song when one is finished
	}
/**
 * creates audio player, then connects it to palylist
 * @param playlist, as it is the one the audio player uses
 * 
 */
	public void playAudio(Song song){
		try{
			//AI IMPLEMENT: ai thought it would be nice to add this to not play multiple songs at once
			if(clip!=null){
				clip.stop();
				clip.close();
			}
			paused = false; //means paused is false, because it's being played 
			File file = new File(song.getFile()); //GETS audio file path inside the song
			AudioInputStream audio = AudioSystem.getAudioInputStream(file); //with the audioinputstream, it can read it (like filereader)
			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.addLineListener(event ->{ //listens for any changes to the clip
				if(event.getType()==LineEvent.Type.STOP){ //only care if the clip stopped
					Clip stoppedClip = (Clip) event.getLine(); //this is like Line line = event.getLine()
					if(stoppedClip.getMicrosecondPosition()>=stoppedClip.getMicrosecondLength()){
						playlist.playNext(); //if it stopped at the END of the audio, then play the NEXT song
					}
				}
			});
			if(clip!=null){
				clip.start();
				System.out.println("Now playing: " + song);
			}
				
		}
		catch(Exception e){
			System.out.println("ERROR playing audio...");
		}
	}
	/**
	 * no params, but very simple, stop if there is a clip no matter what
	 */
	public void stopAudio(){
		try{
			if(clip!=null){
				clip.stop();
				System.out.println("The audio has stopped playing...");
			}
		}
		catch(Exception e){
			System.out.println("ERROR STOPPING AUDIO.");
		}
	}
	public boolean isPlaying(){
		if(clip!=null && clip.isRunning()==true){
			return true;
		}
		return false;
	}
	public void paused(){
		if(isPlaying()==true){ //only pauses if theres a song playing
			clip.stop();
			paused = true;
			System.out.println("Audio has been paused.");
		}
	}
	public void resume(){
		if(paused==true){ //vise versa, if song not playing
			clip.start();
			paused = false;
			System.out.println("Audio has been resumed.");
		}
	}
	/**
	 * changes volume to low, high, or emedium
	 * @param level which is the volume level user can choose (3 choices)
	 */
	public void setVolume(String level){
		if(clip!=null){ //MASTER_GAIN controls main volume of clip using decibels, as explained before
			FloatControl volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN); //floatcontrol is the float of volume 
			if(level.equalsIgnoreCase("low")){
				volume.setValue(-30.0f);

			}
			else if(level.equalsIgnoreCase("medium")){
				volume.setValue(0.0f);
				
			}
			else if(level.equalsIgnoreCase("high")){
				volume.setValue(6.0f); //got the loudest
				
			}
			else{
				System.out.println("Invalid volume level.");
			}
		}
	}


}