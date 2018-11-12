package net.markkozel.lc3;

import java.util.Arrays;
import java.util.List;

public class Instruction_Operate extends Instruction {

	private String code;
	private String value;

	public Instruction_Operate(String line) {
		super(line);
		setType(Instruction_Type.OPERATE);

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
			result = "Operate-> " + code + ": " + value;
		}

		return result;
	}
}