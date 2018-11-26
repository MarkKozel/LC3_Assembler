package net.markkozel.lc3;

import java.util.StringTokenizer;

/**
 * Container for LC-3 Operate Instructions
 * <p>
 * LC-3 Operate instructions:
 * <ul>
 * <li>ADD - Addition for Register-only and Register-Immediate Value
 * <li>AND - Logical And Register-only and Register-Immediate Value
 * <li>NOT - Logical Not Register-Only
 * </UL>
 * 
 * <p>
 * Container parses Operate source code into binary values for OpCode and
 * Operands If line is invalid, container sets {@link isGood} and
 * {@link errorMsg}
 * 
 * @author Mark Kozel
 *
 */
public class Instruction_Operate extends Instruction {

	/**
	 * {@link Shared} instance for conversion utils
	 */
	Shared shared = Shared.getInstance();

	/**
	 * Holds Destination Register
	 */
	private String destReg;
	/**
	 * Holds Source Register 1
	 */
	private String srcReg1;
	/**
	 * Holds Source Register 2 if applicable
	 */
	private String srcReg2;
	/**
	 * Flag to indicate Immediate Value is used
	 */
	private boolean immediate = false;

	/**
	 * Filler bits for unused part of NOT's Operands
	 */
	private String notFiller = "111111";

	/**
	 * Created new Instruction_Operate object and populates
	 * {@link Instruction_Operate} and {@link Instruction} fields If line does
	 * not parse correctly, isGood and errMsg are set accordingly
	 * 
	 * @param line
	 *            source code line
	 * @param lineNumber
	 *            line number of source code line, including blank and comment
	 *            lines
	 */
	public Instruction_Operate(String line, int lineNumber) {
		super(line, lineNumber);
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
							this.errorMsg = current + " cannot be parsed"; // ~
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

	/**
	 * Converts elements into bit string
	 * 
	 * @return String of 16-bit representation
	 */
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

	/**
	 * Converts elements into hex string
	 * 
	 * @return String of base-16 representation
	 */
	public String toHex() {
		String temp = this.toBinary();
		int decimal = Integer.parseInt(temp, 2);
		return Integer.toString(decimal, 16);
	}

	/**
	 * Debugging string output of elements
	 * 
	 * @return String breakdown of elements
	 */
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