import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * these are my junit tests
 * checks atleast 10, and all the stuff on the rubric until A level
 */
public class MusicJUnitTest {
@Test
public void testAddFirst() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	testList.addFirst(song);
	assertEquals(1, testList.size());
	assertEquals(song,testList.get(0));
}
@Test
public void testAddLast() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("AAAAddd", "Artssdist AA", 123, "Jazzaas", "testddd.wav");
	testList.addLast(song);
	testList.addLast(song2);
	assertEquals(2, testList.size());
	assertEquals(song ,testList.get(0));
	assertEquals(song2 ,testList.get(1));
}
@Test
public void testRemoveFirst() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("AAAAddd", "Artssdist AA", 123, "Jazzaas", "testddd.wav");
	testList.addFirst(song);
	testList.addLast(song2);
	testList.removeFirst();
	assertEquals(1, testList.size());
	assertEquals(song2 ,testList.get(0));
}
@Test
public void testRemoveLast() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("AAAAddd", "Artssdist AA", 123, "Jazzaas", "testddd.wav");
	testList.addFirst(song);
	testList.addLast(song2);
	testList.removeLast();
	assertEquals(1, testList.size());
	assertEquals(song ,testList.get(0));
}

@Test
public void testForward() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("BBB", "Artist BB", 22, "Jazz", "zest.wav");
	Song song3 = new Song("CCCCC", "Artist CC", 234, "Jazz", "cest.wav");
	testList.addLast(song);
	testList.addLast(song2);
	testList.addLast(song3);
	
	assertEquals(song,testList.get(0));
	assertEquals(song2,testList.get(1));
	assertEquals(song3,testList.get(2));
}
@Test
public void testBackward() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("BBB", "Artist BB", 22, "Jazz", "zest.wav");
	Song song3 = new Song("CCCCC", "Artist CC", 234, "Jazz", "cest.wav");
	testList.addLast(song);
	testList.addLast(song2);
	testList.addLast(song3);
	
	assertEquals(song3,testList.getBackward(0));
	assertEquals(song2,testList.getBackward(1));
	assertEquals(song,testList.getBackward(2));
}
@Test
public void testInstertAfter() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("BBB", "Artist BB", 22, "Jazz", "zest.wav");
	Song song3 = new Song("CCCCC", "Artist CC", 234, "Jazz", "cest.wav");
	testList.addLast(song);
	testList.addLast(song3);
	testList.insertAfter(0,song2);
	
	assertEquals(song,testList.get(0));
	assertEquals(song2,testList.get(1));
	assertEquals(song3,testList.get(2));
	assertEquals(3,testList.size());
}
@Test
public void testInsertBefore() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("BBB", "Artist BB", 22, "Jazz", "zest.wav");
	Song song3 = new Song("CCCCC", "Artist CC", 234, "Jazz", "cest.wav");
	testList.addLast(song);
	testList.addLast(song3);
	testList.insertBefore(1,song2);
	
	assertEquals(song,testList.get(0));
	assertEquals(song2,testList.get(1));
	assertEquals(song3,testList.get(2));
	assertEquals(3,testList.size());
}
@Test
public void testSwapAdj() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("BBB", "Artist BB", 22, "Jazz", "zest.wav");
	Song song3 = new Song("CCCCC", "Artist CC", 234, "Jazz", "cest.wav");
	testList.addLast(song);
	testList.addLast(song3);
	testList.addLast(song2);
	testList.swapNodes(1,2);
	
	assertEquals(song,testList.get(0));
	assertEquals(song2,testList.get(1));
	assertEquals(song3,testList.get(2));
	assertEquals(3,testList.size());
}
@Test
public void testSwapNonAdj() {
// test code goes here
	SongLinkedList testList = new SongLinkedList();
	Song song = new Song("AAAA", "Artist AA", 102, "Jazz", "test.wav");
	Song song2 = new Song("BBB", "Artist BB", 22, "Jazz", "zest.wav");
	Song song3 = new Song("CCCCC", "Artist CC", 2, "roooo", "cest.wav");
	Song song4 = new Song("DDDD", "Artist DD", 355, "band", "cest.wav");
	Song song5 = new Song("EEEEE", "Artist EEE", 22, "Jazz", "cest.wav");
	testList.addLast(song);
	testList.addLast(song2);
	testList.addLast(song3);
	testList.addLast(song4);
	testList.addLast(song5);
	testList.swapNodes(1,3);
	
	assertEquals(song,testList.get(0));
	assertEquals(song4,testList.get(1));
	assertEquals(song3,testList.get(2));
	assertEquals(song2,testList.get(3));
	assertEquals(song5,testList.get(4));
	assertEquals(5,testList.size());
}


}
