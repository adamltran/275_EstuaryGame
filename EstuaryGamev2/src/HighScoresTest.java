import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class HighScoresTest {

	@Test
	public void test() {

		HighScores h = new HighScores();
		h.add(100,"AAA");
		h.add(90,"BBB");
		h.add(80,"CCC");
		h.add(70,"DDD");
		h.add(50,"EEE");
		h.add(60,"FFF");
		h.add(30,"GGG");
		h.add(20,"HHH");
		h.add(10,"III");
		h.add(1,"JJJ");
		h.add(0,"KKK");
		
		try{
		FileOutputStream fos = new FileOutputStream("Scores.high");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(h);
		oos.close();
		}catch(Exception ex){
			fail("Exception thrown during test: " + ex.toString());
		}
		
		
		try
		{
			FileInputStream fis = new FileInputStream("Scores.High");
			ObjectInputStream ois = new ObjectInputStream(fis);
			HighScores loaded = (HighScores) ois.readObject();
			ois.close();

			assertEquals(100,h.getScores().get(0).getScore());
			assertEquals(90,h.getScores().get(1).getScore());
			assertEquals("AAA",h.getScores().get(0).getInitials());
			assertEquals("BBB",h.getScores().get(1).getInitials());

			// Clean up the file
			new File("Scores.high").delete();
		}
		catch (Exception ex)
		{
			fail("Exception thrown during test: " + ex.toString());
		}
	}

}
