package net.markkozel.lc3;

/**
 * Contains a single symbol
 * @author mark
 *
 */
public class Symbol {

	private String name;
	private int	address;
	
	public Symbol(String name, String address) {
		this.name = name;
		this.address = stringToHex(address);
	}
	
	private int stringToHex(String hex) {
		int result = 0;
		result = Integer.parseInt(hex, 16);
		return result;
	}
}
