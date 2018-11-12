package net.markkozel.lc3;

import java.util.Arrays;
import java.util.List;

public class Instruction_Comment extends Instruction {

	private String comment;

	public Instruction_Comment(String line) {
		super(line);
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
}