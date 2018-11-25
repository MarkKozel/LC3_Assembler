package net.markkozel.lc3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Shared {
	private static Shared instance = null;

	public final static String[] OpCodeOperate = { "ADD", "AND", "NOT" };
	public final List<String> OpCodeOperateList = Arrays.asList(OpCodeOperate);

	public final String[] OpCodeData = { "LD", "LDI", "LDR", "LEA", "RTI", "ST", "STI", "STR" };
	public final List<String> OpCodeDataList = Arrays.asList(OpCodeData);

	public final String[] OpCodeControl = { "BR", "JMP", "JSR", "JSRR", "TRAP" };
	public final List<String> OpCodeControlList = Arrays.asList(OpCodeControl);

	public final String[] PseudoOps = { ".ORIG", ".FILL", ".BLKW", ".STRINGZ", ".END" };
	public final List<String> PseudoOpsList = Arrays.asList(PseudoOps);

	public String baseFilename = "";
	public String path = "";

	public final int IMM5_MAX = 15;
	public final int IMM5_MIN = -16;

	private Shared() {

	}

	public static Shared getInstance() {
		if (instance == null) {
			instance = new Shared();
		}

		return instance;
	}

	/**
	 * Converts value to 2's complement bit string of set length.
	 * toBinaryString() perform the conversion and 2's complement operation.
	 * 
	 * @param value
	 *            number to convert to bit string
	 * @param length
	 *            desired length of resulting bit string
	 * @return bit string representation of value set to length
	 */
	public String twosComp(int value, int length) {
		String result = "";
		String bitStr = padWithChar(Integer.toBinaryString(value), length, "0");

		if (value < 0) { // perform 2's complement
			for (int x = bitStr.length() - 1; x >= (bitStr.length() - length); x--) {
				result = bitStr.charAt(x) + result;
			}
		} else {
			result = bitStr; // Pos number, no 2's comp
		}
		return result;
	}

	public void updateFileInfo(String fileRef) {
		Path p = Paths.get(fileRef);
		this.baseFilename = p.getFileName().toString();
		this.baseFilename = this.baseFilename.substring(0, this.baseFilename.lastIndexOf('.'));
		this.path = p.getParent().toString();
	}

	public String repeat(String str, int times) {
		return new String(new char[times]).replace("\0", str);
	}

	/**
	 * Pads left of str with paddingChar finalLen times
	 * 
	 * @param str
	 *            String to pad
	 * @param finalLength
	 *            final length of str when done
	 * @param paddingChar
	 *            character to use to pad
	 * @return
	 */
	public String padWithChar(String str, int finalLen, String paddingChar) {
		String result = str;
		int currLen = str.length();

		if (currLen < finalLen) {
			for (int x = 0; x < (finalLen - currLen); x++) {
				result = paddingChar + result;
			}
		}
		return result;
	}

	/**
	 * Converts a string Register Name (R0, R6) to 3-bit address
	 * 
	 * @param regName
	 *            Register Name
	 * @return 3-bit binary address of Register or 'null' if regName is bad
	 */
	public String regToAddress(String regName) {
		String result = "";
		int regNumber;

		if (regName.length() != 2) {// Invalid Reg Name
			return null;
		}

		if (!regName.toUpperCase().startsWith("R")) { // Doesn't start with r/R
			return null;
		}

		try {
			regNumber = Integer.parseInt(regName.substring(1));
		} catch (NumberFormatException e) { // Not a number
			return null;
		}

		if (regNumber > 7) {// R8 and R9 are invalid reg numbers
			return null;
		}

		// OK, got good Reg Name and Number.
		// Convert to 3-bit address and return as string
		result = padWithChar(Integer.toBinaryString(regNumber), 3, "0");

		return result;
	}

	/**
	 * Converts Dec or Hex Immediate Value to bit string
	 * 
	 * @param immName
	 *            Immediate value
	 * @param numBits
	 *            Number of bits in final result
	 * @return bit string, numBits long (zero extended), of immediate value
	 */
	public String immToAddress(String immName, int numBits) {
		String result = "";
		int immValue;

		if (!(immName.toUpperCase().startsWith("#") || immName.toUpperCase().startsWith("X"))) {
			// bad imm tag (#/x)
			return null;
		}

		if (immName.toUpperCase().startsWith("#")) { // Decimal Value
			try { // Make sure value is number
				immValue = Integer.parseInt(immName.substring(1));
			} catch (NumberFormatException e) { // Not a number
				return null;
			}
			if ((immValue <= IMM5_MAX) && (immValue >= IMM5_MIN)) {
				result = padWithChar(Integer.toBinaryString(immValue), numBits, "0");
			}
		}

		if (immName.toUpperCase().startsWith("X")) { // Decimal Value
			try { // Make sure value is number
				immValue = Integer.parseInt(immName.substring(1), 16);
			} catch (NumberFormatException e) { // Not a number
				return null;
			}
			if ((immValue <= IMM5_MAX) && (immValue >= IMM5_MIN)) {
				result = padWithChar(Integer.toBinaryString(immValue), numBits, "0");
			}
		}
		return result;
	}

}
