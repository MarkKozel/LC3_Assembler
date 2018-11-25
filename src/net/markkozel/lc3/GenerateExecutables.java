package net.markkozel.lc3;

import java.util.ArrayList;

public class GenerateExecutables {

	public GenerateExecutables() {

	}

	public void toFile(ArrayList<Symbol> asmSymbols, ArrayList<Instruction> asmInstruction) {
		for (Instruction line : asmInstruction) {
			System.out.println(line.toString());

		}

		for (Symbol symbol : asmSymbols) {
			System.out.println(symbol.toString());

		}
	}

}
