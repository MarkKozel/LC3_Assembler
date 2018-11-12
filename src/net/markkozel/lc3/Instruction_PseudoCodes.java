package net.markkozel.lc3;

//import java.util.Arrays;
//import java.util.List;
import java.util.StringTokenizer;

public class Instruction_PseudoCodes extends Instruction {

	Shared shared = Shared.getInstance();

	private String label;
	private String code;
	private String value = "";

	public Instruction_PseudoCodes(String line) {
		super(line);
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

			if (shared.PseudoOpsList.contains(current) && (this.code == null)) { // Op
				this.code = current;
			} else {
				if (this.code != null) { // Address or Size after op

					// Build STRINGZ string by iterating to the end
					if (this.code.equalsIgnoreCase(".STRINGZ")) {
						this.value = this.value + currentOriginal + " ";
					} else {
						this.value = currentOriginal;
					}
				} else { // Must be label
					if (this.label == null) {
						this.label = current;
					} else {// error
						this.isGood = false;
						this.errorMsg = current + " cannot be parsed";
					}
				}
			}
		} //While
			//Clean up value
		if (this.value != null) { // Clean up extra spaces on STRINGZ
			this.value = this.value.replace("\"", ""); //Remove leading and trailing quotes
			this.value.trim();
		}
	} //method

	public String toString() {
		String result = this.line;

		if (this.code != null) {
			String isLabel = this.label != null ? (this.label +" ") : "";
			result = "Pseudo->  " + isLabel + " " + this.code + " " + this.value;
		}

		return result;
	}
}