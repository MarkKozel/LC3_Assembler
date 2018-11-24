package net.markkozel.lc3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import net.markkozel.lc3.Shared;

public class testShared {
	public Shared shared;

	@Test
	public void initTest() {
		shared = Shared.getInstance();
		assertNotNull(shared);
	}

	@Test
	public void testTwosComplement() {
		shared = Shared.getInstance();
		assertEquals(shared.twosComp(0, 4), "0000");

		assertEquals(shared.twosComp(1, 4), "0001");
		assertEquals(shared.twosComp(2, 4), "0010");
		assertEquals(shared.twosComp(3, 4), "0011");
		assertEquals(shared.twosComp(4, 4), "0100");
		assertEquals(shared.twosComp(5, 4), "0101");
		assertEquals(shared.twosComp(6, 4), "0110");
		assertEquals(shared.twosComp(7, 4), "0111");

		assertEquals(shared.twosComp(-1, 4), "1111");
		assertEquals(shared.twosComp(-2, 4), "1110");
		assertEquals(shared.twosComp(-3, 4), "1101");
		assertEquals(shared.twosComp(-4, 4), "1100");
		assertEquals(shared.twosComp(-5, 4), "1011");
		assertEquals(shared.twosComp(-6, 4), "1010");
		assertEquals(shared.twosComp(-7, 4), "1001");

		// Imm5
		assertEquals(shared.twosComp(15, 5), "01111");
		assertEquals(shared.twosComp(1, 5), "00001");
		assertEquals(shared.twosComp(0, 5), "00000");
		assertEquals(shared.twosComp(-1, 5), "11111");
		assertEquals(shared.twosComp(-16, 5), "10000");

		// Offset6
		assertEquals(shared.twosComp(31, 6), "011111");
		assertEquals(shared.twosComp(1, 6), "000001");
		assertEquals(shared.twosComp(0, 6), "000000");
		assertEquals(shared.twosComp(-1, 6), "111111");
		assertEquals(shared.twosComp(-32, 6), "100000");

		// PCOffset9
		assertEquals(shared.twosComp(255, 9), "011111111");
		assertEquals(shared.twosComp(1, 9), "000000001");
		assertEquals(shared.twosComp(0, 9), "000000000");
		assertEquals(shared.twosComp(-1, 9), "111111111");
		assertEquals(shared.twosComp(-256, 9), "100000000");

		// PCOffset11
		assertEquals(shared.twosComp(1023, 11), "01111111111");
		assertEquals(shared.twosComp(1, 11), "00000000001");
		assertEquals(shared.twosComp(0, 11), "00000000000");
		assertEquals(shared.twosComp(-1, 11), "11111111111");
		assertEquals(shared.twosComp(-1024, 11), "10000000000");
	}

	@Test
	public void updateFileInfoTest() {
		shared = Shared.getInstance();
		shared.updateFileInfo("c://MyDir//MyFile.jpeg");

		assertEquals(shared.baseFilename, "MyFile");
		assertEquals(shared.path, "c:\\MyDir");
	}

	@Test
	public void repeatTest() {
		shared = Shared.getInstance();

		String test = "hello";
		test = shared.repeat(test, 2);
		assertEquals(test, "hellohello");
	}

	@Test
	public void padWithCharTest() {
		shared = Shared.getInstance();

		// Append with 5 '0' chars
		String test = "hello";
		test = shared.padWithChar(test, 10, "0");
		assertEquals(test, "00000hello");

		// Append with no additional '0' chars
		test = "hello";
		test = shared.padWithChar(test, 5, "0");
		assertEquals(test, "hello");

		// finalSize is < original string size. Expect no chnage
		test = "hello";
		test = shared.padWithChar(test, 4, "0");
		assertEquals(test, "hello");
	}

	@Test
	public void regToAddressTest() {
		shared = Shared.getInstance();

		// RegName too short
		String test = shared.regToAddress("s");
		assertNull(test);

		// RegName too long
		test = shared.regToAddress("s23");
		assertNull(test);

		// RegName does not start with 'R'
		test = shared.regToAddress("S1");
		assertNull(test);

		// RegName does not start with 'R'
		test = shared.regToAddress("RR");
		assertNull(test);

		// RegName does not start with 'R'
		test = shared.regToAddress("R8");
		assertNull(test);
		test = shared.regToAddress("R8");
		assertNull(test);

		// Good Reg Names
		test = shared.regToAddress("R0");
		assertEquals(test, "000");
		test = shared.regToAddress("R1");
		assertEquals(test, "001");
		test = shared.regToAddress("R2");
		assertEquals(test, "010");
		test = shared.regToAddress("R3");
		assertEquals(test, "011");
		test = shared.regToAddress("R4");
		assertEquals(test, "100");
		test = shared.regToAddress("R5");
		assertEquals(test, "101");
		test = shared.regToAddress("R6");
		assertEquals(test, "110");
		test = shared.regToAddress("R7");
		assertEquals(test, "111");
	}

	@Test
	public void immToAddressTest() {
		shared = Shared.getInstance();

		// immName bad first char
		String test = shared.immToAddress("{", 0);
		assertNull(test);

		// Decimal value not a number
		test = shared.immToAddress("#GHG", 0);
		assertNull(test);

		// Decimal value is good
		test = shared.immToAddress("#5", 5);
		assertEquals(test, "00101");

		test = shared.immToAddress("x5", 5);
		assertEquals(test, "00101");

		test = shared.immToAddress("xff", 8);
		assertEquals(test, "11111111");

		test = shared.immToAddress("xff", 10);
		assertEquals(test, "0011111111");

		test = shared.immToAddress("x00", 10);
		assertEquals(test, "0000000000");
	}
}
