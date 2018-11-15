package net.markkozel.lc3;

//import java.util.Arrays;
//import java.util.List;

public class Instruction {

	enum Instruction_Type {
		PSEUDOOP, COMMENT, OPERATE, DATAMOVEMENT, CONTROL;
	}

	ISA isa = ISA.getInstance();
	
	private String opCode;
	// private String operands;
	// private String comments;
	// private String pseudo;
	protected String label;
	protected String code;
	protected String value;
	private int address; // updated by Symbol Table as it is updated

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.opCode = isa.getOpCodeStr(code.toUpperCase());
		this.code = code;
	}

	public String getValue() {
		if (this.value == null) {
			return "";
		} else {
			return this.value;
		}
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getAddress() {
		return address;
	}
	
	public String getOpCode() {
		return this.opCode;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	private Instruction_Type type;

	protected String line;
	protected String[] tokens;

	protected String delimit = "[ ]+";

	protected boolean isGood = true;
	protected String errorMsg = "";

	public Instruction(String line) {
		this.line = line;

	}

	protected void setType(Instruction_Type type) {
		this.type = type;
	}

	public Instruction_Type getType() {
		return this.type;
	}

	// public void setAdress(int address){
	// this.address = address;
	// }
	//
	// protected int getAddress(){
	// return address;
	// }
	//
	// public String getCode() {
	// return this.code;
	// }
	//
	// public String getValue() {
	// return this.value;
	// }
	//
	// public String getLabel() {
	// return this.label;
	// }
}
