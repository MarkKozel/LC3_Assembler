package net.markkozel.lc3;

//import java.util.Arrays;
//import java.util.List;

public class Instruction_Control extends Instruction {

//	protected String label;
//	protected String code;
//	protected String value;

	public Instruction_Control(String line) {
		super(line);
		setType(Instruction_Type.CONTROL);

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

		if (code.length() > 0) {
			result = "Control-> " + code + ": " + value;
		}

		return result;
	}
}