import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.File;

/**
 * This class runs the whole playlist program
 * Shows menu and lets you choose what you want to do with music :)
 */
public class MainGUI{
	public static void main(String[]args){
		
	

		System.out.println("Music GUI Logs");

		SwingUtilities.invokeLater(() -> {
            interactiveJListExample.interactiveJListExample();
        });


	}
/** this class basically holds the actual playlist GUI
 * and all the buttons/listeners that control it
 */
	public class interactiveJListExample {
/** makes the GUI and connects buttons to playlist.. lot of action listeners
 */   
   
    public static void interactiveJListExample() {
    	Playlist playlist = new Playlist();
    	JFrame loadingScreen = new JFrame("Loading Screen");
    	JFrame playlistFrame = new JFrame("Interactive Song Screen"); //our actual main menu
        loadingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadingScreen.setSize(800, 400);
        loadingScreen.setLocationRelativeTo(null); //loadin screen initalizing
        JTextField csvText = new JTextField("Enter a CSV file",20);
        JButton loadButton = new JButton("Load CSV");
        JPanel loadPanel = new JPanel();
        loadPanel.add(csvText);
        loadPanel.add(loadButton);
        loadingScreen.add(loadPanel);
/**
 * making the menu at the top
 * File has stuff like load/save/new/exit
 * Playlist has stuff that changes the playlist to whatever you choose it to be
 */
        JMenuBar menuBar= new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu playlistMenu = new JMenu("Playlist");
        //all munu stuff
        JMenuItem newItem = new JMenuItem("New Playlist");
        JMenuItem loadItem = new JMenuItem("Load Playlist");
        JMenuItem saveItem = new JMenuItem("Save your wonderful Playlist");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem clearItem = new JMenuItem("CLear your whole entire playlist.");
        fileMenu.add(newItem);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        playlistMenu.add(clearItem);
        menuBar.add(fileMenu);
        menuBar.add(playlistMenu);
        playlistFrame.setJMenuBar(menuBar);

        

 

        //sets up main playlist window stuff
        playlistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playlistFrame.setSize(800, 400);
        playlistFrame.setLocationRelativeTo(null);
        //actually stores strings shown inside the JLIST
        //when the playlist changes, should also change too so the GUI can match
        DefaultListModel<String> songListMode1 = new DefaultListModel<>();
        JList<String> songList = new JList<>(songListMode1); 
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //only ONE selection
        
        // Create components for interaction
        JLabel selectedLabel = new JLabel("Selected: None");
       	JButton editSong = new JButton("Change song information...");
        JButton addButton = new JButton("Add a song!");
        JButton removeButton = new JButton("Remove Selected");
        JButton stopB = new JButton("⏹");
        JButton playB = new JButton("⏵");
        JButton playPrev = new JButton("⏮");
        JButton pauseB = new JButton("⏸");
        JButton playNextB = new JButton("⏭ ");
        
        // Add selection listener
        songList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = songList.getSelectedValue();
                selectedLabel.setText("Selected: " + (selected != null ? selected : "None"));
            }
        });
        /** checks if the mouse is clicked on playlist
         * if double clicked, starts playing, only checks for double click
         */
        songList.addMouseListener(new java.awt.event.MouseAdapter(){
        	@Override
        	public void mouseClicked(java.awt.event.MouseEvent e){
        		if(e.getClickCount()==2){
        			int ind = songList.getSelectedIndex();
        			if(ind!=-1){
        				playlist.setCurrentSong(ind);
        				playlist.playCurrentSong();
        				Song curr = playlist.getCurrentSong();
        				selectedLabel.setText("NOW PLAYING: "+ curr.getTitle());
        			}
        		}
        	}
        });
        
       
        
        
        // Add button listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser choose = new JFileChooser();
                int res = choose.showOpenDialog(playlistFrame);
                if(res==JFileChooser.APPROVE_OPTION){
                	File selected = choose.getSelectedFile();
                	String filePath = selected.getAbsolutePath();
                	System.out.println("The file you selected was: " + filePath);

                	Song newSong = new Song(selected.getName(),"Unknown Artist", 180, "Unknown", filePath);
                	playlist.addSong(newSong);
                	songListMode1.addElement(newSong.toString());
                }
            }
        });
        
        // Remove button listener
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = songList.getSelectedIndex();
                if (selectedIndex != -1) {
                	//if selected and is deleted.. we stop it first
                	Song selectedS = playlist.getSongs().getSong(selectedIndex);
                	if(selectedS ==playlist.getCurrentSong()){
                		playlist.stopPlayback();
                		selectedLabel.setText("Now playing: NONE;");
                	}
                	playlist.getSongs().removeAt(selectedIndex); //removes from linked
                	songListMode1.remove(selectedIndex); //removes from what gui shows
                }
            }
        });
        //lets user edit what they want the info of the song to be
        editSong.addActionListener(e ->{
        	int ind = songList.getSelectedIndex();
        	if(ind !=-1){
        		Song song = playlist.getSongs().getSong(ind);
        		//starts dialogs with whatever the song currently has
        		String titleChange = JOptionPane.showInputDialog(playlistFrame, "New Title: ", song.getTitle());
        		String artistChange = JOptionPane.showInputDialog(playlistFrame, "New Artist: ", song.getArtist());

        		String durationChange = JOptionPane.showInputDialog(playlistFrame, "New Duration: ", song.getDuration());
        		//these nulls mean if its empty or cancelled 
        		if(titleChange!=null){
        			song.setTitle(titleChange);

        		}
        		if(artistChange!= null){
        			song.setArtist(artistChange);
        		}

        		if(durationChange!=null){
        			try{ //try to get a int from this.. nothing else
        				int newDur = Integer.parseInt(durationChange);
        				song.setDuration(newDur);
        			}
        			catch(NumberFormatException exx){
        				JOptionPane.showMessageDialog(playlistFrame, "Enter a correct number for the duration please..");
        			}
        			
        		}

        		songListMode1.set(ind,song.toString()); //refreshes that one song in JList after changing
        	}
        });
        playB.addActionListener(e -> {
        	int ind = songList.getSelectedIndex();
        	if(ind !=-1){
        		playlist.setCurrentSong(ind);
        		playlist.playCurrentSong();
        		Song current = playlist.getCurrentSong();
        		selectedLabel.setText("NOW PLAYING: " + current.getTitle());
        	}
        });
        stopB.addActionListener(e -> {
        	int ind = songList.getSelectedIndex();
        	playlist.stopPlayback();
        	selectedLabel.setText("Now playing: NONE..");
        	
        });
        pauseB.addActionListener(e -> {
        	Song curr = playlist.getCurrentSong();
        	if(curr!=null){
        		
        		playlist.pausePlayback();
        		selectedLabel.setText("PAUSED: " + curr.getTitle());
        	}
        });
        playNextB.addActionListener(e -> {
        	int ind = songList.getSelectedIndex();
        	if(ind !=-1 && ind+1<playlist.getSongs().size()){
        		playlist.stopPlayback();
        		playlist.setCurrentSong(ind+1);
        		playlist.playCurrentSong();
        		songList.setSelectedIndex(ind+1);

        		Song current = playlist.getCurrentSong();
        		selectedLabel.setText("NOW PLAYING: " + current.getTitle());
        	}
        });
        playPrev.addActionListener(e -> {
        	int ind = songList.getSelectedIndex();
        	if(ind>0){
        		playlist.stopPlayback();
        		playlist.setCurrentSong(ind-1);
        		playlist.playCurrentSong();
        		songList.setSelectedIndex(ind-1);

        		Song current = playlist.getCurrentSong();
        		selectedLabel.setText("NOW PLAYING: " + current.getTitle());
        	}
        });


        
        // Layout components
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
        //TOP PANEL STUF
        topPanel.add(addButton);
        topPanel.add(editSong);
        topPanel.add(removeButton);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //BOTTOM PANEL STUFF
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(selectedLabel);
        
        bottomPanel.add(stopB);
        bottomPanel.add(playPrev);
        bottomPanel.add(playB);
        bottomPanel.add(pauseB);
        bottomPanel.add(playNextB);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //puts Jlist inside scroll so we can scroll if it gets big
        JScrollPane scrollPane = new JScrollPane(songList);
        scrollPane.setPreferredSize(new Dimension(350, 150));
        
        playlistFrame.setLayout(new BorderLayout());
        playlistFrame.add(topPanel, BorderLayout.NORTH);
        playlistFrame.add(scrollPane, BorderLayout.CENTER);
        playlistFrame.add(bottomPanel, BorderLayout.SOUTH);
        //starts on loading screen first, then later playlistFrame
        loadingScreen.setVisible(true);
        playlistFrame.setVisible(false);
        newItem.addActionListener(e -> {
        	playlist.stopPlayback();
        	playlist.getSongs().clear();
        	songListMode1.clear();
        	selectedLabel.setText("NOW PLAYING NOTHING...");
        });
        saveItem.addActionListener(e ->{
        	JFileChooser choose = new JFileChooser();
        	int res = choose.showSaveDialog(playlistFrame);
        	if(res==JFileChooser.APPROVE_OPTION){
        		File selected = choose.getSelectedFile();
        		String filePath = selected.getAbsolutePath();
        		playlist.savePlaylistToCSV(filePath);
        	}
        });
        exitItem.addActionListener(e -> {
        	System.exit(0);
        });
        loadItem.addActionListener(e -> {
        	JFileChooser choose = new JFileChooser();
        	int res = choose.showOpenDialog(playlistFrame);
        	if(res==JFileChooser.APPROVE_OPTION){
        		playlist.stopPlayback();
        		playlist.getSongs().clear(); //gets rid of old stuff before loading new
        		songListMode1.clear();
        		File selected = choose.getSelectedFile();
        		String filePath = selected.getAbsolutePath();
        		playlist.getSongs().clear();
        		songListMode1.clear();
        		playlist.loadPlaylistFromCSV(filePath);
        		//updates our gui list and linkedlist
        		for(int i = 0;i<playlist.getSongs().size();i++){
        			Song song = playlist.getSongs().getSong(i);
        			songListMode1.addElement(song.toString());
        		}
        		selectedLabel.setText("NOW PLAYING: NONE");
        	}
        });
        clearItem.addActionListener(e -> {
        	playlist.stopPlayback();
        	playlist.getSongs().clear();
        	songListMode1.clear();
        	selectedLabel.setText("Now playing.. nothing...");
        });

        loadButton.addActionListener(e -> {
        	String fileName = csvText.getText();

        	File file = new File(fileName);
        	//only does it if the file exists
        	if(file.exists()){
        		System.out.println("Loading csv...");
        		playlist.loadPlaylistFromCSV(fileName);
        		songListMode1.clear(); //clears everything in there
        		for(int i =0;i<playlist.getSongs().size();i++){
        			Song song = playlist.getSongs().getSong(i);
        			songListMode1.addElement(song.toString());
        		} //THEN we add to linkedlist.. and copies songs into what the GUI displays
        		loadingScreen.dispose();
        		playlistFrame.setVisible(true); //finally showing main menu
        	}
        	else{
        		System.out.println("File does not exist.");
        	}
        	

        	
        });


    }
}
}