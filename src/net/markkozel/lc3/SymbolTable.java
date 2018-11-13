package net.markkozel.lc3;

import java.util.ArrayList;

//public class SymbolTable extends LC3_FileTypeParent implements LC3_FileTypeTemplate{
public class SymbolTable {

	public String fileExt = ".sym";
	private int startingAddress = 0;
	private int addressOffset = 0;
	ArrayList<Symbol> asmSymbols = new ArrayList<Symbol>();

	public SymbolTable() {
		super();
	}

	public boolean buildSymbolTable(ArrayList<Instruction> asmInstructions) {
		boolean result = true;

		//			if (asmInstructions.get(0).getCode().equals(".ORIG")) {
		//				String sAddr = asmInstructions.get(0).getValue();
		//				startingAddress = Integer.parseInt(sAddr, 16);
		//			}
		for (Instruction line : asmInstructions) {

			if (line.getCode() != null) { //Skip comments
				if (line.getCode().equals(".ORIG")) {
					String sAddr = line.getValue().substring(1); //remove leading 'x'
					
					try {
						startingAddress = Integer.parseInt(sAddr, 16);
					} catch (NumberFormatException nfe) {
						result = false;
						return result;
					}

				} else {

					line.setAddress(startingAddress + addressOffset);
					
					if(line.getLabel() != null) {
						asmSymbols.add(new Symbol(line.getLabel(), line.getAddress()));
					}
					
					System.out.println(line.toString());
					addressOffset++;
				}
			}
		}
		
		System.out.println("Symbol Table");
		for(Symbol sym : asmSymbols) {
			System.out.println(sym.toString());
		}
		return result;
	}

	//	@Override
	//	public void writeFile() {
	//		// TODO Auto-generated method stub
	//		
	//		super.writeFile();
	//	}

}
