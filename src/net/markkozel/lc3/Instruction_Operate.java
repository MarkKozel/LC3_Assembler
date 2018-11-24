package net.markkozel.lc3;

import java.util.StringTokenizer;

//import net.markkozel.lc3.Instruction.Instruction_Type;

public class Instruction_Operate extends Instruction {

	Shared shared = Shared.getInstance();

	// protected String label;
	// protected String code;
	private String destReg; // AND, ADD, NOT
	private String srcReg1; // AND, ADD, NOT
	private String srcReg2; // AND, ADD, NOT
	private boolean immediate = false; // Mode for AND and ADD
	private String notFiller = "111111";

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
			currentOriginal = currentOriginal.replaceAll(",$", "");
			// Uppercase for comparison
			String current = currentOriginal.toUpperCase();

			if (shared.OpCodeOperateList.contains(current) && (this.getCode() == null)) { // Op
				this.setCode(current);
				if (this.getCode().equals("NOT")) {
					this.immediate = false;
				}
			} else {
				if (this.getCode() != null) {
					if (current.startsWith("R")) { // Register
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
					} else { // Not Register. IMM5 or Label
						if (current.startsWith("#") || current.startsWith("X")) {
							if (this.setImmValue(current)) {
								this.immediate = true;
							} else {
								this.isGood = false;
								this.errorMsg = current + " Imm5 value out of range";
							}

						} else { // Must be label
							this.isGood = false;
							this.errorMsg = current + " cannot be parsed"; //~
						} // else label
					} // else "R"
				} else { // Must be label
					if (this.getLabel() == null) {
						this.setLabel(current);
					} else {// error
						this.isGood = false;
						this.errorMsg = current + " cannot be parsed";
					}
				} // else label
			}
		} // While
	}// method

	public String toBinary() {
		String result = "";

		result = this.getOpCode();
		result += shared.regToAddress(this.destReg);
		result += shared.regToAddress(this.srcReg1);

		if (this.getCode().equals("NOT")) {
			result += this.notFiller;
		} else { // AND or ADD
			if (this.immediate) {
				result += "1";
				result += shared.immToAddress(this.getImmValue(), 5);
			} else {
				result += "0";
				result += "00";
				result += shared.regToAddress(this.srcReg2);
			}
		}
		return result;
	}

	public String toHex() {
		String temp = this.toBinary();
		int decimal = Integer.parseInt(temp, 2);
		return Integer.toString(decimal, 16);
	}

	public String toString() {
		String result = this.line;

		if (this.getCode() != null) {
			String isLabel = this.getLabel() != null ? (this.getLabel() + " ") : "";
			String isDest2 = (this.srcReg2 != null) ? this.srcReg2 : (this.getValue() + " IMM5");
			result = "Operate-> " + Integer.toHexString(this.getAddress()) + " " + isLabel + this.getCode() + " "
					+ this.destReg + " " + this.srcReg1 + " " + isDest2 + "(x" + this.toHex() + ") " + this.toBinary();
			;
		}

		return result;
	}
}