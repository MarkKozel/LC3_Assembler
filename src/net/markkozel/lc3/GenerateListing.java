package net.markkozel.lc3;

import java.util.ArrayList;

public class GenerateListing {

	public GenerateListing() {
		
	}

	public void toFile(ArrayList<Symbol> asmSymbols, ArrayList<Instruction> asmInstructions) {
		for (Instruction line : asmInstructions) {
			if (line.getCode() != null) { // Skip comments
				
			}
		}
	}

}
