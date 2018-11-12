package net.markkozel.lc3;

import java.util.Arrays;
import java.util.List;

public class Instruction_DataMovement extends Instruction {

	private String code;
	private String value;

	public Instruction_DataMovement(String line) {
		super(line);
		setType(Instruction_Type.DATAMOVEMENT);

		//this.tokens = this.line.split(this.delimit);

		// for (int i = 0; i < tokens.length; i++) {
		// if (PseudoOpsList.contains(tokens[i])) {
		// this.pseudo = tokens[i];
		// if (this.pseudo == ".ORIG") {
		// this.address = tokens[i + 1];
		// i++;
		// }
		// }
		// }
		// }
	}

	public String toString() {
		String result = this.line;

		if (this.code != null) {
			result = "Data-> " + code + ": " + value;
		}

		return result;
	}
}