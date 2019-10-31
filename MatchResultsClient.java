import java.io.BufferedReader;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MatchResultsClient {
	
	final int port = 6006;
	
	public void go() {		
		try {
			System.out.println("Contacting server on port: " + port);
			ArrayList<DatedMatchResultV2> clubs = null;
			Socket socket = new Socket("localhost", port); //connect to Server via port number
			ObjectInputStream ois = new ObjectInputStream((socket.getInputStream())); // accept the serializedArrayList from server
			clubs = (ArrayList<DatedMatchResultV2>) ois.readObject(); //deserialize ArrayList
			ois.close();
			
			for (DatedMatchResultV2 result : clubs) {
				System.out.println(result.toString());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void main(String [] args) {
		new MatchResultsClient().go();
	}
}
