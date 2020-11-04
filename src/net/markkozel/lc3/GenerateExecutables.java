package net.markkozel.lc3;

import java.io.FileOutputStream;
import java.util.ArrayList;

import net.markkozel.lc3.Instructions.Instruction;

public class GenerateExecutables {

	Shared shared = Shared.getInstance();
	
	FileOutputStream outputObj;
	public String fileExt = ".obj";
	String fileName = shared.path + "\\" + shared.baseFilename +  fileExt;
	public GenerateExecutables() {

	}

	public void toFile(ArrayList<Symbol> asmSymbols, ArrayList<Instruction> asmInstruction) {
		byte[] result = new byte[asmInstruction.size()];
		
		for(int i = 0; i < asmInstruction.size(); i++) {
//		    result[i] = asmInstruction.get(i).byteValue();
		}
		
		for (Instruction line : asmInstruction) {
			System.out.println(line.toString());

			
			
			
			
//			outputObj = new FileOutputStream(fileName);
//		    byte[] strToBytes = str.getBytes();
//		    outputObj.write(strToBytes);
		 
//		    outputObj.close();
			
			
		}

		for (Symbol symbol : asmSymbols) {
			System.out.println(symbol.toString());

		}
	}

}
