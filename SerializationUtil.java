import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;

public class SerializationUtil {
	
	public SerializationUtil(List<DatedMatchResult> matchResults) {
		
	}

	public static ArrayList<DatedMatchResult> deserialize(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<DatedMatchResult> clubs = null;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filef));
		clubs = (ArrayList<DatedMatchResult>) in.readObject(); //casting to a parameterized type (in this case ArrayList<DatedMatchResult>. DatedMatchResult is the parameterized type) will always result in an unchecked warning from the compiler.
		in.close();
		return clubs;
	}

	public static void serialize(File file, List<DatedMatchResult> matchResults) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(file.getName());
		System.out.println(matchResults.isEmpty());
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(matchResults);
		fos.close();
		oos.close();
	}
}
