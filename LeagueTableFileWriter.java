import java.io.*;
import java.nio.charset.StandardCharsets;

public class LeagueTableFileWriter {
	public static void write(String league, File file) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
		writer.write(league);
		writer.close();
	}
}
