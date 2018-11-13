package net.markkozel.lc3;

import java.util.StringTokenizer;

//import net.markkozel.lc3.Instruction.Instruction_Type;

public class Instruction_Operate extends Instruction {

	Shared shared = Shared.getInstance();

//	protected String label;
//	protected String code;
	private String destReg;
	private String srcReg1;
	private String srcReg2;
//	protected String value;
	private boolean immediate = false; //Mode for AND and ADD

	public Instruction_Operate(String line) {
		super(line);
		setType(Instruction_Type.OPERATE);

		StringTokenizer tokens = new StringTokenizer(line, " ");

		if (tokens.countTokens() < 2) {
			this.isGood = false;
			this.errorMsg = line + " Incorrect # of parameters";
			return;
		}

		while (tokens.hasMoreTokens()) {
			String currentOriginal = tokens.nextToken(); // Preserves case
			String current = currentOriginal.toUpperCase(); // Uppercase for comparison

			if (shared.OpCodeOperateList.contains(current) && (this.getCode() == null)) { // Op
				this.setCode(current);
				if (this.getCode().equals("NOT")) {
					this.immediate = false;
				}
			} else {
				if (this.getCode() != null) {
					if (current.startsWith("R")) { //Register
						if (destReg == null) {
							destReg = current;
						} else {
							if (srcReg1 == null) {
								srcReg1 = current;
							} else {
								if (srcReg2 == null) {
									srcReg2 = current;
									this.immediate = false;
								}
							}
						}
					} else { //Not Register. IMM5 or Label
						if (current.startsWith("#") || current.startsWith("X")) {
							this.setValue(currentOriginal);
							this.immediate = true;
						} else { // Must be label
							this.isGood = false;
							this.errorMsg = current + " cannot be parsed";
						} //else label
					} //else "R"
				} else { // Must be label
					if (this.getLabel() == null) {
						this.setLabel(current);
					} else {// error
						this.isGood = false;
						this.errorMsg = current + " cannot be parsed";
					}
				} //else label

			}
		} //While

	}//method

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

		if (this.getCode() != null) {
			//			result = "Operate-> " + this.label + " code: " + this.code + " destR: " + this.destReg + " srcR1: "
			//					+ this.srcReg1 + " srcR2: " + this.srcReg2 + " value: " + this.value + "IMM?: " + this.immediate;

			String isLabel = this.getLabel() != null ? (this.getLabel() +" ") : "";
			String isDest2 = (this.srcReg2 != null) ? this.srcReg2 : (this.getValue() + " IMM5");
			result = "Operate-> " +Integer.toHexString(this.getAddress())+" "+ isLabel + this.getCode() + " " + this.destReg + " " + this.srcReg1 + " " + isDest2;
			;
		}

		return result;
	}
}