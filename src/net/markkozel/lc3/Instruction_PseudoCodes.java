package net.markkozel.lc3;

import java.util.Arrays;
import java.util.List;

public class Instruction_PseudoCodes extends Instruction {

	private String code;
	private String value;

	public Instruction_PseudoCodes(String line) {
		super(line);
		setType(Instruction_Type.PSEUDOOP);

		this.tokens = this.line.split(this.delimit);

		for (int i = 0; i < tokens.length; i++) {
			if(tokens[i].toUpperCase() == ".ORIG") {
				this.code = ".ORIG";
				this.value = tokens[i+1];
			}
			
			if(tokens[i].toUpperCase() == ".END") {
				this.code = ".END";
			}
//			if (PseudoOpsList.contains(tokens[i])) {
//				this.pseudo = tokens[i];
//				if (this.pseudo == ".ORIG") {
//					this.address = tokens[i + 1];
//					i++;
//				}
//			}
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