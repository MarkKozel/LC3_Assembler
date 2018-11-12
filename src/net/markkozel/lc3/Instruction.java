package net.markkozel.lc3;

import java.util.Arrays;
import java.util.List;

public class Instruction {

//	private final static String[] OpCodeOperate = {"ADD", "AND", "NOT"};
//	static List<String> OpCodeOperateList = Arrays.asList(OpCodeOperate);
//	
//	private final String[] OpCodeData = {"LD", "LDI", "LDR", "LEA", "RTI", "ST", "STI", "STR"};
//	private List<String> OpCodeDataList = Arrays.asList(OpCodeData);
//	
//	private final String[] OpCodeControl = {"BR", "JMP", "JSR", "JSRR", "TRAP"};
//	private List<String> OpCodeControlList = Arrays.asList(OpCodeControl);
//	
//	protected final String[] PseudoOps = { ".ORIG", ".FILL", ".BLKW", ".STRINGZ", ".END" };
//	protected List<String> PseudoOpsList = Arrays.asList(PseudoOps);
	
	enum Instruction_Type 
	{ 
	    PSEUDOOP, COMMENT, OPERATE, DATAMOVEMENT, CONTROL; 
	} 
	
	private String label;
	private String opCode;
	private String operands;
	private String comments;
	private String pseudo;
	private String address;
	
	private Instruction_Type type;
	
	protected String line;
	protected String[] tokens;
	
	protected String delimit = "[ ]+";
	
	protected boolean isGood = false;
	protected String errorMsg;
	
	public Instruction(String line) {
		this.line = line;
//		tokens = line.split(delimit);
		
	}
	
	protected void setType(Instruction_Type type){
		this.type= type;
	}
	
	public Instruction_Type getType(){
		return this.type;
	}
}
