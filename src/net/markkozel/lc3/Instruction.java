package net.markkozel.lc3;

public class Instruction {

	private String[] OpCodes = {"ADD", "AND", "BR", "JMP", "JSR", "JSRR", "LD", "LDI", "LDR", "LEA", "NOT", "RET", "RTI", "ST", "STI", "STR","TRAP"};
	private String[] PseudoOps = {".ORIG", ".FILL", ".BLKW", ".STRINGZ", "END"};
	
	private String label;
	private String opCode;
	private String operands;
	private String comments;
	private String pseudo;
	
	private String line;
	private String[] tokens;
	
	private String delimit = "[ ]+";
	
	public Instruction(String line) {
		this.line = line;
		tokens = line.split(delimit);
		
		for(int i = 0; i < tokens.length; i++){
			if(tokens[i].startsWith(".")){
				this.pseudo = tokens[i];
			}
		}
		
		this.label = getLabel();
	}
	
	private String getLabel(){
		String result = "";
		
		return result;
	}
}
