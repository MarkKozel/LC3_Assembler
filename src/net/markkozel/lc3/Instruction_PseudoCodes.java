package net.markkozel.lc3;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Instruction_PseudoCodes extends Instruction {

	// protected final String[] PseudoOps = { ".ORIG", ".FILL", ".BLKW",
	// ".STRINGZ", ".END" };
	// private final List<String> PseudoOpsList = Arrays.asList(PseudoOps);
	Shared shared = Shared.getInstance();

	private String label;
	private String code;
	private String value;

	public Instruction_PseudoCodes(String line) {
		super(line);
		setType(Instruction_Type.PSEUDOOP);

		StringTokenizer tokens = new StringTokenizer(line, " ");
		// this.tokens = this.line.split(this.delimit);

		if (tokens.countTokens() < 1) {
			this.isGood = false;
			this.errorMsg = line + " is incomplete Pseudo Op";
			return;
		}

		while (tokens.hasMoreTokens()) {
			String current = tokens.nextToken().toUpperCase();
			// if(current == ".ORIG" || current == ".BLKW" || current ==
			// ".FILL"){
			if (shared.PseudoOpsList.contains(current) && (this.code == null)) { // Op
				this.code = current;
			} else {
				if (this.code != null) { // Address or Size after op
					if (this.code == ".STINGZ") {
						StringTokenizer string = new StringTokenizer(line, "\"\" ");
						while(string.hasMoreTokens()) {
							this.value = this.value + string + " ";
						}
						this.value.trim();
					} else {
						this.value = current.toLowerCase();
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
		}
	}

	public String toString() {
		String result = this.line;

		if (this.code != null) {
			result = "Pseudo-> " + this.label + " code:" + this.code + "   value: " + this.value;
		}

		return result;
	}
}