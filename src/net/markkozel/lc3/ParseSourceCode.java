package net.markkozel.lc3;

import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;


public class ParseSourceCode {
	
	Shared shared = Shared.getInstance();

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
					if (shared.OpCodeOperateList.contains(code)) {
						instructions.add(new Instruction_Operate(line));
						break;
					}
					if (shared.OpCodeDataList.contains(code)) {
						instructions.add(new Instruction_DataMovement(line));
						break;
					}
					if (shared.OpCodeControlList.contains(code)) {
						instructions.add(new Instruction_Control(line));
						break;
					}
					if (shared.PseudoOpsList.contains(code)) {
						instructions.add(new Instruction_PseudoCodes(line));
						break;
					}
				}
			}

		}

		return instructions;
	}

}
