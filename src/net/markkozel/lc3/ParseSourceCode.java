package net.markkozel.lc3;

import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

import net.markkozel.lc3.Instructions.Instruction;
import net.markkozel.lc3.Instructions.Instruction_Comment;
import net.markkozel.lc3.Instructions.Instruction_Control;
import net.markkozel.lc3.Instructions.Instruction_DataMovement;
import net.markkozel.lc3.Instructions.Instruction_Operate;
import net.markkozel.lc3.Instructions.Instruction_PseudoCodes;


public class ParseSourceCode {
	
	Shared shared = Shared.getInstance();
	private int lineNumber = 0;

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
				instructions.add(new Instruction_Comment(line, lineNumber));
			} else {
				tokens = line.split(delimit);
				for (String code : tokens) {
					if (shared.OpCodeOperateList.contains(code)) {
						instructions.add(new Instruction_Operate(line, lineNumber));
						break;
					}
					if (shared.OpCodeDataList.contains(code)) {
						instructions.add(new Instruction_DataMovement(line, lineNumber));
						break;
					}
					if (shared.OpCodeControlList.contains(code)) {
						instructions.add(new Instruction_Control(line, lineNumber));
						break;
					}
					if (shared.PseudoOpsList.contains(code)) {
						instructions.add(new Instruction_PseudoCodes(line, lineNumber));
						break;
					}
				}
			}

		}

		return instructions;
	}

}
