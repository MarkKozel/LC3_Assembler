package net.markkozel.lc3;

import java.util.ArrayList;

public class ListingGenerator {

	public ListingGenerator(ArrayList<Symbol> asmSymbols, ArrayList<Instruction> asmInstructions) {
		for (Instruction line : asmInstructions) {
			if (line.getCode() != null) { // Skip comments
				
			}
		}
	}

	public void toFile() {

	}

}
