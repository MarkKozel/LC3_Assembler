package net.markkozel.lc3;

import java.util.HashMap;
import java.util.Map;

public class ISA {

	private static ISA instance = null;

	//	private Map<String, Integer> ISAOpCodes = new HashMap<String, Integer>();
	Map<String, Integer> ISAOpCodes;

	private ISA() {
		ISAOpCodes = new HashMap<String, Integer>();
		ISAOpCodes.put("NOP", new Integer((int) 0b0000));
		ISAOpCodes.put("ADD", new Integer((int) 0b0001));
		ISAOpCodes.put("AND", new Integer((int) 0b0101));
		ISAOpCodes.put("NOT", new Integer((int) 0b1001));
	}

	public static ISA getInstance() {
		if (instance == null) {
			instance = new ISA();
		}

		return instance;
	}

	public String getOpCodeStr(String opName) {
		String result = "0000"; //NOP
		if (ISAOpCodes.containsKey(opName)) {
			result = Integer.toBinaryString(ISAOpCodes.get(opName));
		}
		return result;
	}
	
	public int getOpCodeInt(String opName) {
		int result = 0; //NOP
		if (ISAOpCodes.containsKey(opName)) {
			result = ISAOpCodes.get(opName);
		}
		return result;
	}

	//	private class Operation {
	//		short OpCode = (short) 0b0000;		//All Instructions
	//		short DR  = (short) 0b000;			//ADD, AND, LD, LDI, LDR, LEA, NOT
	//		short SR1 = (short) 0b000;			//ADD, AND
	//		short SR2 = (short) 0b000;			//AND(Reg), ADD(Reg)
	//		short SR  = (short) 0b000;			//NOT
	//		short OpMode = (short) 0b0; 	 		//For AND and ADD Reg vs. Imm mode
	//		short Imm5 = (short) 0b00000;		//AND(Imm5) and ADD(Imm5)
	//		
	//		public Operation() {
	//			
	//		}
	//	}
}
