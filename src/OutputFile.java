import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class OutputFile {

	public OutputFile(String winingMoves, String numberOfNodesCreated, String cost, String time) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			writer.write(winingMoves+"\n");
			writer.write("Num: "+numberOfNodesCreated+"\n");
			writer.write("Cost: "+cost+"\n");
			if(!time.isEmpty()){
				DecimalFormat df = new DecimalFormat("#.###");
				writer.write(df.format(Double.parseDouble(time))+" seconds");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
