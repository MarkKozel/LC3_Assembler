package net.markkozel.lc3;

/**
 * Contains a single symbol
 * @author mark
 *
 */
public class Symbol {

	private String name;
	private int	address;
	
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
		return this.name +" "+ Integer.toHexString(address);
	}
}
