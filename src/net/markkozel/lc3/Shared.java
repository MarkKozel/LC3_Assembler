package net.markkozel.lc3;

import java.util.Arrays;
import java.util.List;

public class Shared {
	private static Shared instance = null;
	
	public final static String[] OpCodeOperate = { "ADD", "AND", "NOT" };
	public final List<String> OpCodeOperateList = Arrays.asList(OpCodeOperate);

	public final String[] OpCodeData = { "LD", "LDI", "LDR", "LEA", "RTI", "ST", "STI", "STR" };
	public final List<String> OpCodeDataList = Arrays.asList(OpCodeData);

	public final String[] OpCodeControl = { "BR", "JMP", "JSR", "JSRR", "TRAP" };
	public final List<String> OpCodeControlList = Arrays.asList(OpCodeControl);

	public final String[] PseudoOps = { ".ORIG", ".FILL", ".BLKW", ".STRINGZ", ".END" };
	public final List<String> PseudoOpsList = Arrays.asList(PseudoOps);
	
	private Shared() {
		
	}
	
	public static Shared getInstance() {
		if(instance == null) {
			instance = new Shared();
		}
		
		return instance;
	}


}
