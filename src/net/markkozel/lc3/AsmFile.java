package net.markkozel.lc3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AsmFile extends LC3_FileTypeParent implements LC3_FileTypeTemplate {

	public String fileExt = ".asm";
	private String AsmFileName;
	ArrayList<String> asmCode = new ArrayList<String>();

	public AsmFile() {
		super();
	}

	@Override
	public void writeFile() {
		// TODO Auto-generated method stub

		super.writeFile();
	}
	
	
	/**
	 * 
	 * @param fileName path and name of source (asm) file
	 */
	public void readAsmFile(String fileName) {
		AsmFileName = fileName;
		String currentLine;
		
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(AsmFileName);
			br = new BufferedReader(fr);

			while ((currentLine = br.readLine()) != null) {
//				System.out.println(currentLine);
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
	}
}
