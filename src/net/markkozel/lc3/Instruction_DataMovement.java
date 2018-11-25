package net.markkozel.lc3;

//import java.util.Arrays;
//import java.util.List;

public class Instruction_DataMovement extends Instruction {

//	protected String label;
//	protected String code;
//	protected String value;

	public Instruction_DataMovement(String line, int lineNumber) {
		super(line, lineNumber);
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

	public String getCode() {
		return this.code;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public String toString() {
		String result = this.line;

		if (this.code != null) {
			result = "Data-> " + code + ": " + value;
		}

		return result;
	}
}