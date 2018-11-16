package net.markkozel.lc3;

import java.nio.file.Path;
import java.nio.file.Paths;
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

	public String baseFilename = "";
	public String path = "";
	
	public final int IMM5_MAX = 15;
	public final int IMM5_MIN = -16;

	private Shared() {

	}

	public static Shared getInstance() {
		if (instance == null) {
			instance = new Shared();
		}

		return instance;
	}

	public void updateFileInfo(String fileRef) {
		Path p = Paths.get(fileRef);
		this.baseFilename = p.getFileName().toString();
		this.baseFilename = this.baseFilename.substring(0, this.baseFilename.lastIndexOf('.'));
		this.path = p.getParent().toString();
	}

	public String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
	
	/**
	 * Pads left of str with paddingChar finalLen times
	 * @param str String to pad
	 * @param finalLength final length of str when done
	 * @param paddingChar character to use to pad
	 * @return
	 */
	public String padWithChar(String str, int finalLen, String paddingChar) {
		String result = str;
		int currLen = str.length();
		
		if(currLen < finalLen){
			for(int x = 0; x < (finalLen = currLen); x++){
				result = paddingChar + result;
			}
		}
		return result;
	}
	
}
