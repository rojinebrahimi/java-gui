package findingwordsedited;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileReader {

	public String fileRead() throws FileNotFoundException {
		String line = "";
		try {
			FileReader fr = new FileReader("D:\\Eclipse\\Finding Words- Edited\\Sample.txt");
			BufferedReader br = new BufferedReader(fr);

			line = br.readLine();
			br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
}
