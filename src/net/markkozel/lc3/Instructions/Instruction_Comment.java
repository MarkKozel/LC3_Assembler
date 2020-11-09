package net.markkozel.lc3.Instructions;

//import java.util.Arrays;
//import java.util.List;

public class Instruction_Comment extends Instruction {

	protected String comment;

	public Instruction_Comment(String line, int lineNumber) {
		super(line, lineNumber);
		setType(Instruction_Type.COMMENT);
		this.comment = line;
	}

	public String toString() {
		String result = this.line;

		if (this.comment != null) {
			result = "Comment-> " + comment;
		}

		return result;
	}

	public String toBinary() {
		return "";
	}

	public String toHex() {
		return "";
	}

	public byte[] toObjCode() {
		return new byte[1];
	}
}