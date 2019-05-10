import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile {

	public OutputFile(String winingMoves, String numberOfNodesCreated, String cost, String time) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			writer.write(winingMoves+"\n");
			writer.write(numberOfNodesCreated+"\n");
			writer.write(cost+"\n");
			writer.write(time+"\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
