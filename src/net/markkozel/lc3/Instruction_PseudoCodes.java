package net.markkozel.lc3;

import java.util.Arrays;
import java.util.List;

public class Instruction_PseudoCodes extends Instruction {

	private String label;
	private String code;
	private String value;

	public Instruction_PseudoCodes(String line) {
		super(line);
		setType(Instruction_Type.PSEUDOOP);

		this.tokens = this.line.split(this.delimit);

		for (int i = 0; i < tokens.length; i++) {
			// .ORIG || .BLKW || .FILL
			if ((tokens[i].toUpperCase() == ".ORIG") || (tokens[i].toUpperCase() == ".BLKW")
					|| (tokens[i].toUpperCase() == ".FILL")) {
				if (tokens[i + 1] != null) {
					this.code = tokens[i].toUpperCase();
					this.value = tokens[i + 1];
					this.isGood = true;
					break;
				} else {
					this.isGood = false;
					this.errorMsg = tokens[i].toUpperCase() + " requires additional parameter";
				}

			} else {
				// .END
				if (tokens[i].toUpperCase() == ".END") {
					this.code = tokens[i].toUpperCase();
					this.value = "";
					this.isGood = true;
					break;
				} else {
					
					// .STRINGZ
					if (tokens[i].toUpperCase() == ".STRINGZ") {
						this.code = tokens[i].toUpperCase();
						this.value = tokens[i];
						this.isGood = true;
						break;
					} else {
						
						// Label
						if (this.label == null) {
							this.label = tokens[i];
						} else {
							this.isGood = false;
							this.errorMsg = tokens[i].toUpperCase() + " label cannot be parsed";
						}
					}
				}

			}

		}
	}

	public String toString() {
		String result = this.line;

		if (this.code != null) {
			result = "Pseudo-> code:" + code + "   value: " + value;
		}

		return result;
	}
}