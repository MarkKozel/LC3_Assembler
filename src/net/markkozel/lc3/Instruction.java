package net.markkozel.lc3;

//import java.util.Arrays;
//import java.util.List;

public class Instruction {

	protected int lineNumber = 0; //Source code line
	private String opCode;
	protected String label;
	protected String code;
	protected String value;
	protected String immValue;
	private int address; // updated by Symbol Table as it is updated

	public enum Instruction_Type {
		PSEUDOOP, COMMENT, OPERATE, DATAMOVEMENT, CONTROL;
	};

	ISA isa = ISA.getInstance();
	Shared shared = Shared.getInstance();

	private Instruction_Type type;

	protected String line;
	protected String[] tokens;

	protected String delimit = "[ ]+";

	protected boolean isGood = true;
	protected String errorMsg = "";

	/**
	 * Constructor
	 */
	public Instruction(String line, int lineNumber) {
		this.line = line;
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public boolean getIsGood(){
		return this.isGood;
	}
	
	public String getErrorMsg(){
		return this.errorMsg;
	}
	
	/**
	 * Store Immediate Value
	 * 
	 * @return Immediate value
	 */
	public String getImmValue() {
		return this.immValue;
	}

	/**
	 * Gets Immediate value
	 * 
	 * @param immValue
	 *            Value to store
	 * @return true is Immediate value is valid
	 */
	public boolean setImmValue(String immValue) {
		boolean result = false;
		String value = shared.immToAddress(immValue, 5);
		
		if ((value != null) && (value.length() == 5)) {
			this.immValue = immValue;
			result = true;
		}
		return result;
	}

	/**
	 * Gets label
	 * 
	 * @return label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Sets label
	 * 
	 * @param label
	 *            LC-3 label
	 */
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

	public Instruction(String line) {
		this.line = line;

	}

	protected void setType(Instruction_Type type) {
		this.type = type;
	}

	public Instruction_Type getType() {
		return this.type;
	}
}
