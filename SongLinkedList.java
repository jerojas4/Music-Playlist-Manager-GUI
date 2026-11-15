/**
 * 
 * this song makes the linmed list that stores all of the songs
 * keeps track of everything, especially most important, size
 * 
 * 
 */
public class SongLinkedList{
	private SongNode head;
	private SongNode tail;
	private int size;

	public SongLinkedList(){
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	/** 
	 * adds a song to the beginning of the list. 
	 * @param song the song being added 
	 */
	public void addFirst(Song song){ //prepend
		SongNode newNode = new SongNode(song);
		if(head==null){
			this.head = newNode;
			this.tail = newNode;
		}
		else{
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}
		size++;
	}
	public Song getSong(int index){
		SongNode curr = head;
		if(index<0||index>=size){
			return null;
		}
		for(int i = 0;i<index;i++){
			curr = curr.getNext();
		}
		return curr.getSong();
	}
	public void addLast(Song song){ //append
		SongNode newNode = new SongNode(song);
		if(head==null){
			this.head = newNode;
			this.tail = newNode;
		}
		else{
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
	}
	public Song removeFirst(){
		if(head==null){
			return null;
		}
		Song removed = head.getSong();
		head = head.getNext();
		if(head==null){
			tail=null;
		}
		else{
			head.setPrevious(null);
		}
		size--;
		return removed;
	}
	public Song removeLast(){
		if(tail==null){
			return null;
		}
		Song removed = tail.getSong();
		
		if(head==tail){
			tail=null;
			head = null;
		}
		else{
	
			tail = tail.getPrevious();
			tail.setNext(null);
		}
		size--;
		return removed;
	}
	/**
	 * swaps two aactual nodes in LINKED LIST
	 * has to check if theyre next to eachother..or further apart
	 * makes sure head and tail still point to correct nodes
	 * @param index1
	 * @param index2
	 */
	public void swapNodes(int index1, int index2){
		if(index1<0||index1>=size ||index2<0||index2>=size){
			return;
		}
		//NOT SWAPPING SONG DATA.. actual nodes
		SongNode indCurr1 = head;
		SongNode indCurr2 = head;
		//finding where they both at
		for(int i = 0;i<index1;i++){
			indCurr1 = indCurr1.getNext();
		}
		for(int i = 0;i<index2;i++){
			indCurr2 = indCurr2.getNext();
		}
		//setting up for easier swaps
		SongNode prev1 = indCurr1.getPrevious();
		SongNode next1 = indCurr1.getNext();
		SongNode prev2 = indCurr2.getPrevious();
		SongNode next2 = indCurr2.getNext();
		//same exact nodes.. nothing happens
		if(index1 == index2){
			return;
		}
		//if node2 is directy after node1
		if(next1==indCurr2){
			if(prev1!=null){
				prev1.setNext(indCurr2);
			}
			else{
				head = indCurr2;
			}
			indCurr2.setPrevious(prev1);
			indCurr2.setNext(indCurr1);
			indCurr1.setPrevious(indCurr2);
			indCurr1.setNext(next2);
			if(next2!=null){
				next2.setPrevious(indCurr1);
			}
			else{
				tail = indCurr1;			}
		}
		//if node1 is directly after node2
		else if(next2==indCurr1){

			if(prev2!=null){ //thingbefore node2 should point to node1
				prev2.setNext(indCurr1);
			}
			else{
				head = indCurr1;
			}
			indCurr1.setPrevious(prev2);
			indCurr1.setNext(indCurr2);
			indCurr2.setPrevious(indCurr1);
			indCurr2.setNext(next1);
			if(next1!=null){
				next1.setPrevious(indCurr2);

			}
			else{
				tail = indCurr2;			
			}
		}
		//the nodes are NOT next to eachother.. and separated
		else{
			indCurr1.setPrevious(prev2);
			indCurr1.setNext(next2);
			indCurr2.setPrevious(prev1);
			indCurr2.setNext(next1);
			if(prev1!=null){
				prev1.setNext(indCurr2);

			}
			else{
				head = indCurr2;			
			}
			if(next1!= null){
			next1.setPrevious(indCurr2);
			}
			else{
				tail = indCurr2; //such as assuming that we hit the tail
			}
			if(prev2!=null){ //have to check all to see if we're allowed to add or not
				prev2.setNext(indCurr1); //we do not want to hit a dead end
			}
			else{
				head = indCurr1;
			}
			if(next2 != null){
				next2.setPrevious(indCurr1);
			}
			else{
				tail = indCurr1;
			}
		}

	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty(){
		if(head==null){
			return true;
		}
		return false;
	}
	public void displayForward(){
		SongNode curr = head;
		int num = 1;
		while(curr!=null){
			System.out.println(num + ". "+curr.getSong());
			num++;
			curr = curr.getNext();
		}
	}
	public void displayBackward(){
		SongNode curr = tail;
		int temp = size;

		while(curr!=null){
			System.out.println(temp + ". "+curr.getSong());
			temp--;
			curr = curr.getPrevious();
		}
	}
	/**
	 * 
	 * 
	 * heavily depends on where it is being added, and what song
	 * amde a lot of different cases, which is important.
	 * this is like the insertNodeAfter case we know much about
	 * @param index
	 * @param song
	 */
	public void addAt(int index, Song song){
		if(index<0||index>size){
			return;
		} 
		if(index==0){
			addFirst(song);
			return;
		}
		if(index==size){//tail
			addLast(song);
			return;
		}
		SongNode curr = head;
		for(int i = 0;i<index-1;i++){
			curr = curr.getNext();
		}
		SongNode newNode = new SongNode(song);
		newNode.setNext(curr.getNext());
		newNode.setPrevious(curr.getNext().getPrevious());
		curr.getNext().setPrevious(newNode);
		curr.setNext(newNode);
		size++;
	}
	/**
	 * inserts a new song after the index
	 * creates a new node and reconnects stuff
	 * also handles if we are inserting after the tail, or head
	 */
	public void insertAfter(int index, Song song){
		if(index<0||index>=size){
			return;
		} 
		SongNode newNode = new SongNode(song);
		if(index==0){
			SongNode aft = head.getNext();
			head.setNext(newNode);
			newNode.setPrevious(head);
			newNode.setNext(aft);
			if(aft!=null){
				aft.setPrevious(newNode);

			}
			else{
				tail = newNode;
			}
			size++;
			return;

		}
		if(index==size-1){//tail
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
			size++;
			return;
		}
		SongNode curr = head;
		for(int i =0;i<index;i++){
			curr = curr.getNext();

		}
		newNode.setNext(curr.getNext());
		newNode.setPrevious(curr);
		curr.getNext().setPrevious(newNode);
		curr.setNext(newNode);
		
		size++;
	}
	/**
	 * inserts a new song before the index
	 * creates a new node and reconnects stuff
	 * also handles if we are inserting before the tail, or head, reconnects accordingly
	 */
	public void insertBefore(int index, Song song){
		if(index<0||index>=size){
			return;
		} 
		SongNode newNode = new SongNode(song);
		if(index==0){
			newNode.setPrevious(null);
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
			size++;
			return;
			
		}
		if(index==size-1){//tail
			newNode.setPrevious(tail.getPrevious());
			newNode.setNext(tail);
			tail.getPrevious().setNext(newNode);
			tail.setPrevious(newNode);
			size++;
			return;
		}
		SongNode curr = head;
		for(int i =0;i<index;i++){
			curr = curr.getNext();

		}
		newNode.setPrevious(curr.getPrevious());
		curr.getPrevious().setNext(newNode);
		newNode.setNext(curr);
		curr.setPrevious(newNode);
		
		size++;
	}
	public void removeAt(int index){
		if(index<0||index>=size){
			return;
		}
		if(index==0){
			removeFirst();
			return;
		}
		if(index==size-1){
			removeLast();
			return;
		}
		SongNode curr = head; //had a typo here "SogNode" - AI
		for(int i =0;i<index-1;i++){
			curr = curr.getNext();
		}
		SongNode successor = curr.getNext().getNext();
		curr.setNext(successor);
		successor.setPrevious(curr);
		size--;
	}
	public boolean contains(Song song){ //checks if we contain the song by looping through
		SongNode curr = head;
		while(curr!=null){
			if(curr.getSong()==song){
				return true;
			}
			curr = curr.getNext();
		}
		return false;
	}
	public int indexOf(Song song){ //checks index by looping through and returns
		int index = 0;
		SongNode curr = head;
		while(curr!=null){
			
			if(curr.getSong()==song){
				return index;
			}
			index++;
			curr = curr.getNext();
		}
		return -1;
	}
	public void clear(){ //simple, just turn everything to clear it, no fancy loops needed
		tail = null;
		head = null;
		size = 0;
	}
	public Song get(int index){ //made this one because on rubric there's get and getSong, so I just did both
		return getSong(index);
	}
	/**
	 * gets song from starting fromt ail and then backwards
	 * useful for checking if the previous pointers work good
	 * @param index how many spots backwards fromt he tail
	 * @return the song found or null if invalid
	 */
	public Song getBackward(int index){
		if(index<0||index>=size){
			return null;
		}
		SongNode curr = tail;
		for(int i = 0;i<index;i++){
			curr = curr.getPrevious();
		}
		return curr.getSong();
	}
}