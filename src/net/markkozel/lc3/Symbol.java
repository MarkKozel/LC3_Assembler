package net.markkozel.lc3;

/**
 * Contains a single symbol
 * @author mark
 *
 */
public class Symbol {

	private String name;
	private int	address;
	
	Shared shared = Shared.getInstance();
	
	public Symbol(String name, int address) {
		this.name = name;
		this.address = address;
	}
	
//	private int stringToHex(String hex) {
//		int result = 0;
//		result = Integer.parseInt(hex, 16);
//		return result;
//	}
	
	public String toString() {
		String space = " ";
		int indent = 2;
		String padding1 = shared.repeat(" ", (22 - this.name.length() + 2));
		return this.name + padding1 + Integer.toHexString(address);
	}
}
