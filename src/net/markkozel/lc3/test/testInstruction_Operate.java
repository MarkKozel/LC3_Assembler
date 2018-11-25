package net.markkozel.lc3.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.markkozel.lc3.Instruction_Operate;

public class testInstruction_Operate {

	String R0 = "000";
	String R1 = "001";
	String R2 = "010";
	String R3 = "011";
	String R4 = "100";
	String R5 = "101";
	String R6 = "110";
	String R7 = "111";
	
	String Imm5_5 = "00101";
	
	String ADD = "0001";
	String AND = "0101";
	String NOT = "1001";
	
	String Immdiate = "1";
	String Register = "0";
	String RegFill = "00";
	String NotFill = "111111";
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public final void testAddImm5() {
		Instruction_Operate InOp = new Instruction_Operate("ADD R0, R0, #5", 1);
		assertEquals(InOp.toBinary(),ADD+R0+R0+Immdiate+Imm5_5);
		
		Instruction_Operate InOp1 = new Instruction_Operate("ADD R1, R2, #5", 1);
		assertEquals(InOp1.toBinary(),ADD+R1+R2+Immdiate+Imm5_5);
		
		Instruction_Operate InOp2 = new Instruction_Operate("ADD R3, R4, #5", 1);
		assertEquals(InOp2.toBinary(),ADD+R3+R4+Immdiate+Imm5_5);
		
		Instruction_Operate InOp3 = new Instruction_Operate("ADD R5, R6, #5", 1);
		assertEquals(InOp3.toBinary(),ADD+R5+R6+Immdiate+Imm5_5);
		
		Instruction_Operate InOp4 = new Instruction_Operate("ADD R5, R7, #5", 1);
		assertEquals(InOp4.toBinary(),ADD+R5+R7+Immdiate+Imm5_5);
		
		Instruction_Operate InOp5 = new Instruction_Operate("ADD R5, R7, x5", 1);
		assertEquals(InOp5.toBinary(),ADD+R5+R7+Immdiate+Imm5_5);
	}
	
	@Test
	public final void testAddReg() {
		Instruction_Operate InOp = new Instruction_Operate("ADD R0, R0, R0", 1);
		assertEquals(InOp.toBinary(),ADD+R0+R0+Register+RegFill+R0);
		
		Instruction_Operate InOp1 = new Instruction_Operate("ADD R1, R2, R0", 1);
		assertEquals(InOp1.toBinary(),ADD+R1+R2+Register+RegFill+R0);
		
		Instruction_Operate InOp2 = new Instruction_Operate("ADD R3, R4, R5", 1);
		assertEquals(InOp2.toBinary(),ADD+R3+R4+Register+RegFill+R5);
		
		Instruction_Operate InOp3 = new Instruction_Operate("ADD R7, R6, R0", 1);
		assertEquals(InOp3.toBinary(),ADD+R7+R6+Register+RegFill+R0);
		
		Instruction_Operate InOp4 = new Instruction_Operate("ADD R2, R4, R6", 1);
		assertEquals(InOp4.toBinary(),ADD+R2+R4+Register+RegFill+R6);
	}
	
	@Test
	public final void testAndImm5() {
		Instruction_Operate InOp = new Instruction_Operate("AND R0, R0, #5", 1);
		assertEquals(InOp.toBinary(),AND+R0+R0+Immdiate+Imm5_5);
		
		Instruction_Operate InOp1 = new Instruction_Operate("AND R1, R2, #5", 1);
		assertEquals(InOp1.toBinary(),AND+R1+R2+Immdiate+Imm5_5);
		
		Instruction_Operate InOp2 = new Instruction_Operate("AND R3, R4, #5", 1);
		assertEquals(InOp2.toBinary(),AND+R3+R4+Immdiate+Imm5_5);
		
		Instruction_Operate InOp3 = new Instruction_Operate("AND R5, R6, #5", 1);
		assertEquals(InOp3.toBinary(),AND+R5+R6+Immdiate+Imm5_5);
		
		Instruction_Operate InOp4 = new Instruction_Operate("AND R5, R7, #5", 1);
		assertEquals(InOp4.toBinary(),AND+R5+R7+Immdiate+Imm5_5);
		
		Instruction_Operate InOp5 = new Instruction_Operate("AND R5, R7, x5", 1);
		assertEquals(InOp5.toBinary(),AND+R5+R7+Immdiate+Imm5_5);
	}
	
	@Test
	public final void testAndReg() {
		Instruction_Operate InOp = new Instruction_Operate("AND R0, R0, R0", 1);
		assertEquals(InOp.toBinary(),AND+R0+R0+Register+RegFill+R0);
		
		Instruction_Operate InOp1 = new Instruction_Operate("AND R1, R2, R0", 1);
		assertEquals(InOp1.toBinary(),AND+R1+R2+Register+RegFill+R0);
		
		Instruction_Operate InOp2 = new Instruction_Operate("AND R3, R4, R5", 1);
		assertEquals(InOp2.toBinary(),AND+R3+R4+Register+RegFill+R5);
		
		Instruction_Operate InOp3 = new Instruction_Operate("AND R7, R6, R0", 1);
		assertEquals(InOp3.toBinary(),AND+R7+R6+Register+RegFill+R0);
		
		Instruction_Operate InOp4 = new Instruction_Operate("AND R2, R4, R6", 1);
		assertEquals(InOp4.toBinary(),AND+R2+R4+Register+RegFill+R6);
	}
	
	@Test
	public final void testNot(){
		Instruction_Operate InOp = new Instruction_Operate("NOT R0, R0", 1);
		assertEquals(InOp.toBinary(),NOT+R0+R0+NotFill);
		
		Instruction_Operate InOp1 = new Instruction_Operate("NOT R2, R1", 1);
		assertEquals(InOp1.toBinary(),NOT+R2+R1+NotFill);
		
		Instruction_Operate InOp2 = new Instruction_Operate("NOT R4, R5", 1);
		assertEquals(InOp2.toBinary(),NOT+R4+R5+NotFill);
		
		Instruction_Operate InOp3 = new Instruction_Operate("NOT R6, R7", 1);
		assertEquals(InOp3.toBinary(),NOT+R6+R7+NotFill);
	}

	@Test
	public final void testAllWithLabels(){
	
		Instruction_Operate InOp = new Instruction_Operate("MyLabel ADD R5, R7, #5", 1);
		assertEquals(InOp.toBinary(),ADD+R5+R7+Immdiate+Imm5_5);
		
		Instruction_Operate InOp1 = new Instruction_Operate("MyLabel ADD R5, R7, R0", 1);
		assertEquals(InOp1.toBinary(),ADD+R5+R7+Register+RegFill+R0);
		
		Instruction_Operate InOp2 = new Instruction_Operate("MyLabel AND R5, R7, #5", 1);
		assertEquals(InOp2.toBinary(),AND+R5+R7+Immdiate+Imm5_5);
		
		Instruction_Operate InOp3 = new Instruction_Operate("MyLabel AND R5, R7, R0", 1);
		assertEquals(InOp3.toBinary(),AND+R5+R7+Register+RegFill+R0);
		
		Instruction_Operate InOp4 = new Instruction_Operate("MyLabel NOT R6, R7", 1);
		assertEquals(InOp4.toBinary(),NOT+R6+R7+NotFill);
	}
	
	@Test
	public final void testErrors(){
		
		Instruction_Operate InOp = new Instruction_Operate("MyLabel ADD R5, R7, .5", 1);
		assertFalse(InOp.getIsGood());
		assertEquals(InOp.getErrorMsg(),".5 cannot be parsed");
		
		Instruction_Operate InOp1 = new Instruction_Operate("MyLabel ADD R5, R7, #1024", 1);
		assertFalse(InOp1.getIsGood());
		assertEquals(InOp1.getErrorMsg(),"#1024 Imm5 value out of range");
		
		Instruction_Operate InOp11 = new Instruction_Operate("MyLabel ADD R5, R7, #16", 1);
		assertFalse(InOp11.getIsGood());
		assertEquals(InOp11.getErrorMsg(),"#16 Imm5 value out of range");
		
		Instruction_Operate InOp12 = new Instruction_Operate("MyLabel ADD R5, R7, #-17", 1);
		assertFalse(InOp12.getIsGood());
		assertEquals(InOp12.getErrorMsg(),"#-17 Imm5 value out of range");
		
		Instruction_Operate InOp2 = new Instruction_Operate("AND", 1);
		assertFalse(InOp2.getIsGood());
		assertEquals(InOp2.getErrorMsg(),"AND Incorrect # of parameters");
	}
}
