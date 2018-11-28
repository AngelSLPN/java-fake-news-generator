package TextClassification;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.text.Document;

/**
 * A class for analyzing a text (e.g. counting keywords and to find the lines,
 * where they're)
 *
 * @author Huber
 */

class TextAnalyzer implements Analyzer {
	JsonArray mJsonArray;

	public int getEmptyParagraphs() {
		String line;
		int emptyLine = 0;

		try {
			// Create a reader which reads json-files
			InputStream fileInputStream = new FileInputStream(JSON_FILE);
			JsonReader jsonReader = Json.createReader(fileInputStream);
			mJsonArray = jsonReader.readArray();

			int emptyParagraphCounter = 0;
			int lineNumber = 0;
			while ((line = ((BufferedReader) mJsonArray).readLine()) != null) {
				if (line.equals("")) {
					emptyParagraphCounter++;
					System.out.println("The" + emptyParagraphCounter + "empty paragraph is in line" + lineNumber);
					emptyLine = lineNumber;
				}

				lineNumber++;
			}

			// Close jsonReader and fileInputStream
			jsonReader.close();
			fileInputStream.close();

		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return emptyLine;
	}

	public int searchKeywordsAndLines(String words[]) {
		String fileLine = "";
		String searchedWord = words[0];

		int lineNumber = 0;
		int countWord = 0;
		int word = 0;

		if (words.length > 0) {
			while (words[word] != "") {
				try {
					// Create a reader which reads json-files
					InputStream fileInputStream = new FileInputStream(JSON_FILE);
					JsonReader jsonReader = Json.createReader(fileInputStream);
					mJsonArray = jsonReader.readArray();

					// Close jsonReader and fileInputStream
					jsonReader.close();
					fileInputStream.close();

					// Search position and how often this words appear in this text
					while ((fileLine = ((BufferedReader) mJsonArray).readLine()) != null) {
						lineNumber++;
						int position = fileLine.indexOf(searchedWord);

						if (position > -1) {
							countWord++;
							System.out.println("The word is at " + position + ", line " + lineNumber);
						}
					}

					// Close BufferedReader
					((BufferedReader) mJsonArray).close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
				word++;
			}
		} else {
			System.out.println("Please enter a word.");
		}

		return countWord;
	}

	public List<String> separateParagraphs(JsonArray article) {
		String searchFor = "\n";
		String json = "";
		boolean contains = false;
		int length = article.size() / 3;
		List<String> listOfParagraphs = new ArrayList<>();

		for (int i = 0; i < length; i++) {
			JsonObject temp = article.getJsonObject(i);
			for (int j = 0; j < temp.size(); j++) {
				json = article.toString();
				contains = json.contains(searchFor);

				if (contains == true) {
					listOfParagraphs.add(json);
				}
			}
		}
		return listOfParagraphs;
	}

}
