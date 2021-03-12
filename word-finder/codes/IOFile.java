package findingwordsedited;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@SuppressWarnings("serial")
public class IOFile extends WordPanel{

	// FileRedaer/Writer Method
	// ---------------------------------------------------
	public String IOfile(String inputWords) {

		HashMap<String, String> map = new HashMap<String, String>();
		HashSet<String> set = new HashSet<>();

		String line = "";
		try {
			FileReader fr = new FileReader("D:\\Eclipse\\Finding Words- Edited\\Sample.txt");
			BufferedReader br = new BufferedReader(fr);

			line = br.readLine();

			//br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] words;
		words = inputWords.split("\\s");
		for (String str : inputWords.split("\\s")) {
			set.add(str);
		}

		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			for (int i = 0; i < words.length; i++) {
					map.put(words[i], words[i].toUpperCase());
					words[i] = map.get(words[i]);

			}
			

			for (String word : words) {
				line = line.replaceAll(word.toLowerCase(), word);
			}

			try {
				FileWriter fw = new FileWriter("D:\\Eclipse\\Finding Words- Edited\\Sample.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(line);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return line;
	}

}
