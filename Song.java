 /**
  * my song class
  * used to create a song with several params
  * 
  */
 class Song{
	private String title, artist, absFilePath, genre; //csv file asks for 5 (+ genre)
	private int duration; //seconds
	/**
	 * creates the song
	 * @param title the title
	 * @param artist artist 
	 * @param duration the duration in SeCONDS
	 * @param genre genre of song
	 * @param absFilePath file path of the song
	 */
	public Song(String title, String artist, int duration, String genre, String absFilePath){
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.duration = duration;
		this.absFilePath = absFilePath;
		
	}

	//getters and setters, self explanatory
	public String getTitle(){
		return this.title;
	}
	public String getArtist(){
		return this.artist;
	}
	public int getDuration(){
		return this.duration;
	}
	public String getFile(){
		return this.absFilePath;
	}
	public String getGenre(){
		return this.genre;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setArtist(String artist){
		this.artist = artist;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}

	public void setFile(String absFilePath){
		this.absFilePath = absFilePath;
	}


	
	@Override
	public String toString(){
		return "Title: "+this.title+" | Artist: "+this.artist+" | Duration: "+this.duration;
	}
}
