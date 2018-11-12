package net.markkozel.lc3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ParseSourceCode {

	private final static String[] OpCodeOperate = { "ADD", "AND", "NOT" };
	private final List<String> OpCodeOperateList = Arrays.asList(OpCodeOperate);

	private final String[] OpCodeData = { "LD", "LDI", "LDR", "LEA", "RTI", "ST", "STI", "STR" };
	private final List<String> OpCodeDataList = Arrays.asList(OpCodeData);

	private final String[] OpCodeControl = { "BR", "JMP", "JSR", "JSRR", "TRAP" };
	private final List<String> OpCodeControlList = Arrays.asList(OpCodeControl);

	protected final String[] PseudoOps = { ".ORIG", ".FILL", ".BLKW", ".STRINGZ", ".END" };
	private final List<String> PseudoOpsList = Arrays.asList(PseudoOps);

	public ParseSourceCode() {

	}

	public ArrayList<Instruction> parse(ArrayList<String> sourceList) {

		String[] tokens;
		String delimit = "[ ]+";
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();

		for (String line : sourceList) {
			
			StringBuilder sb = new StringBuilder(line);
			while (sb.lastIndexOf("\t") == sb.length()) {
			    sb.delete(sb.length() - 1, sb.length());
			}
			
			line = sb.toString();
			line.trim();

			if (line.startsWith(";")) {
				instructions.add(new Instruction_Comment(line));
			} else {
				tokens = line.split(delimit);
				for (String code : tokens) {
					if (OpCodeOperateList.contains(code)) {
						instructions.add(new Instruction_Operate(line));
						break;
					}
					if (OpCodeDataList.contains(code)) {
						instructions.add(new Instruction_DataMovement(line));
						break;
					}
					if (OpCodeControlList.contains(code)) {
						instructions.add(new Instruction_Control(line));
						break;
					}
					if (PseudoOpsList.contains(code)) {
						instructions.add(new Instruction_PseudoCodes(line));
						break;
					}
				}
			}

		}

		return instructions;
	}

}
