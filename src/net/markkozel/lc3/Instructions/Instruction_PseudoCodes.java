package net.markkozel.lc3.Instructions;

import java.util.StringTokenizer;

import net.markkozel.lc3.Shared;

public class Instruction_PseudoCodes extends Instruction {

	Shared shared = Shared.getInstance();

//	protected String label;
//	protected String code;
//	protected String value = "";

	public Instruction_PseudoCodes(String line, int lineNumber) {
		super(line, lineNumber);
		setType(Instruction_Type.PSEUDOOP);

		StringTokenizer tokens = new StringTokenizer(line, " ");

		if (tokens.countTokens() < 1) {
			this.isGood = false;
			this.errorMsg = line + " is incomplete Pseudo Op";
			return;
		}

		while (tokens.hasMoreTokens()) {
			String currentOriginal = tokens.nextToken(); // Preserves case
			String current = currentOriginal.toUpperCase(); // Uppercase for comparison

			if (shared.PseudoOpsList.contains(current) && (this.getCode() == null)) { // Op
				this.setCode(current);
			} else {
				if (this.getCode() != null) { // Address or Size after op

					// Build STRINGZ string by iterating to the end
					if (this.getCode().equalsIgnoreCase(".STRINGZ")) {
						this.setValue(this.getValue() + currentOriginal + " ");
					} else {
						this.setValue(currentOriginal);
					}
				} else { // Must be label
					if (this.getLabel() == null) {
						this.setLabel(current);
					} else {// error
						this.isGood = false;
						this.errorMsg = current + " cannot be parsed";
					}
				}
			}
		} //While
			//Clean up value
		if (this.getValue() != null) { // Clean up extra spaces on STRINGZ
			this.setValue(this.getValue().replace("\"", "")); //Remove leading and trailing quotes
			this.setValue(this.getValue().trim());
		}
	} //method

//	public String getCode() {
//		return this.code;
//	}
//	
//	public String getValue() {
//		return this.value;
//	}
//	
//	public String getLabel() {
//		return this.label;
//	}
	
	public String toString() {
		String result = this.line;

		if (this.code != null) {
			String isLabel = this.getLabel() != null ? (this.getLabel() +" ") : "";
			result = "Pseudo->  " +Integer.toHexString(this.getAddress())+" "+ isLabel + " " + this.getCode() + " " + this.getValue();
		}

		return result;
	}
}