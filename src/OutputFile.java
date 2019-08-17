import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import org.omg.CORBA.Environment;

public class OutputFile {

	public OutputFile(String winingMoves, String numberOfNodesCreated, String cost, String time) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			writer.write(winingMoves+System.lineSeparator());
			writer.write("Num: "+numberOfNodesCreated+System.lineSeparator());
			writer.write("Cost: "+cost+System.lineSeparator());
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
