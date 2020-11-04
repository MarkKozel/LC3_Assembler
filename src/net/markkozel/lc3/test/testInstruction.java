package net.markkozel.lc3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import net.markkozel.lc3.Instructions.Instruction;

public class testInstruction {

	Instruction inst = new Instruction("line", 1);
	
	@Test
	public final void testSetGetImmValue() {
		assertTrue(inst.setImmValue("#5"));
		assertEquals(inst.getImmValue(), "00101");
		
		assertTrue(inst.setImmValue("xF"));
		assertEquals(inst.getImmValue(), "01111");
		
		assertFalse(inst.setImmValue("aF"));
	}


	@Test
	public final void testSetGetLabel() {
		inst.setLabel("#test1");
		assertEquals(inst.getLabel(), "#test1");
		
		inst.setLabel("123");
		assertEquals(inst.getLabel(), "123");
	}


	@Test
	public final void testSetGetCode() {
		inst.setCode("ADD");
		assertEquals(inst.getCode(), "ADD");
	}


	@Test
	public final void testGetValue() {
		fail("Not yet implemented");
	}

	@Test
	public final void testSetValue() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetAddress() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetOpCode() {
		fail("Not yet implemented");
	}

	@Test
	public final void testSetAddress() {
		fail("Not yet implemented");
	}

	@Test
	public final void testInstruction() {
		fail("Not yet implemented");
	}

	@Test
	public final void testSetType() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetType() {
		fail("Not yet implemented");
	}

}
