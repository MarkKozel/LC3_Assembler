package net.markkozel.lc3.Instructions;

import net.markkozel.lc3.ISA;
import net.markkozel.lc3.Shared;

/**
 * Instructions parent class
 * 
 * <p>Maintains information common to all LC-3 instructions:
 * <ul>
 * <li>Comments (not technically an instruction, but maintained for line numbers)
 * <li>Control - Branches and jumps
 * <li>Data Movement - Load and Store
 * <li>Operate - ADD,AND, NOT
 * <li>Pseudo Ops - runtime settings
 * </ul> {@link Shared}
 * @author Mark Kozel
 *
 */
public class Instruction {

	/**
	 * Source code line number
	 */
	protected int lineNumber = 0;
	/**
	 * LC-3 Op Code
	 */
	private String opCode;
	/**
	 * LC-3 Label text, if present
	 */
	protected String label;
	protected String code;
	protected String value;
	/**
	 * LC-3 Immediate Value, if present
	 */
	protected String immValue;
	/**
	 * Memory Address for this instruction, based on .ORIG and Line Number
	 */
	private int address; // updated by Symbol Table as it is updated

	/**
	 * Reference for separating LC-3 Instruction Type
	 *
	 */
	public enum Instruction_Type {
		PSEUDOOP, COMMENT, OPERATE, DATAMOVEMENT, CONTROL;
	};

	/**
	 * {@link ISA} instance for parsing
	 */
	ISA isa = ISA.getInstance();
	/**
	 * {@link Shared} instance for conversion utils
	 */
	Shared shared = Shared.getInstance();

	/**
	 * LC-3 Instruction Type based on {@link Instruction_Type}
	 */
	private Instruction_Type type;

	/**
	 * Raw source code
	 */
	protected String line;
	/**
	 * Container for parsing parts of source elements
	 */
	protected String[] tokens;

	/**
	 * 1 or more spaces used to delimit source code tokens
	 */
	protected String delimit = "[ ]+";

	/**
	 * Flag to indicate that source code represents a valid LC-3 instruction
	 */
	protected boolean isGood = true;
	/**
	 * Error message, is needed, for invalid LC-3 instruction during parsing
	 */
	protected String errorMsg = "";
	
	protected byte[] lineBytes = new byte[2];

/**
 * Constructor, called from child class
 * @param line source code line
 * @param lineNumber line number of source code line, including blank and comment lines
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
