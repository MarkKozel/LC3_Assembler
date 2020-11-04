package net.markkozel.lc3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.markkozel.lc3.Instructions.Instruction;

//public class SymbolTable extends LC3_FileTypeParent implements LC3_FileTypeTemplate{
public class GenerateSymbolTable {

	Shared shared = Shared.getInstance();

	public String fileExt = ".sym";
	private int startingAddress = 0;
	private int addressOffset = 0;
	public ArrayList<Symbol> asmSymbols = new ArrayList<Symbol>();

	public GenerateSymbolTable() {
		super();
	}

	public boolean build(ArrayList<Instruction> asmInstructions) {
		boolean result = true;

		for (Instruction line : asmInstructions) {

			if (line.getCode() != null) { // Skip comments
				if (line.getCode().equals(".ORIG")) {
					// remove leading 'x'
					String sAddr = line.getValue().substring(1); 
					try {
						startingAddress = Integer.parseInt(sAddr, 16);
					} catch (NumberFormatException nfe) {
						result = false;
						return result;
					}

				} else {

					line.setAddress(startingAddress + addressOffset);

					if (line.getLabel() != null) {
						asmSymbols.add(new Symbol(line.getLabel().toUpperCase(), line.getAddress()));
					}

					System.out.println(line.toString());

					if (line.getCode().equals(".BLKW")) {
						String val = line.getValue();
						if (val.startsWith("#")) { // Base 10 size
							addressOffset += Integer.parseInt(val.substring(1));
						}
						if (val.toUpperCase().startsWith("X")) { // Base 16 size
							addressOffset += Integer.parseInt(val.substring(1), 16);
						}
					} else {
						if (line.getCode().equals(".STRINGZ")) {
							String val = line.getValue();
							addressOffset += val.length() + 1;
						} else {
							addressOffset++;
						}
					}
				}
			}
		}

		System.out.println("Symbol Table");
		for (Symbol sym : asmSymbols) {
			System.out.println(sym.toString());
		}
		return result;
	}

	public int toFile() {

		String fileName = shared.path + "\\" + shared.baseFilename +  fileExt;

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			
			bw.write("//Symbol Name        Page Address\n");
			bw.write("//------------------ ------------\n");
			for (Symbol sym : asmSymbols) {
				bw.write("//  " + sym.toString() +"\n");
			}
			bw.write("\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return 0;
	}
}
