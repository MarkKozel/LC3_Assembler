package net.markkozel.lc3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AsmFile {

	public String fileExt = ".asm";
	private String AsmFileName;
	ArrayList<String> asmCode = new ArrayList<String>();

	public AsmFile() {
//		super();
	}

//	@Override
//	public void writeFile() {
//		super.writeFile();
//	}

/**
 * Read source code from fileName and pack in asmCode String Array
 * @param fileName path and name of source (asm) file
 * @return String Array {@link asmCode} containing all source lines
 */
	public ArrayList<String> readAsmFile(String fileName) {
		AsmFileName = fileName;
		String currentLine;

		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(AsmFileName);
			br = new BufferedReader(fr);

			while ((currentLine = br.readLine()) != null) {
				asmCode.add(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}
		return asmCode;
	}
}
