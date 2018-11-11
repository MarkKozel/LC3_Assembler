package net.markkozel.lc3;

import java.util.ArrayList;

public class SymbolTable extends LC3_FileTypeParent implements LC3_FileTypeTemplate{

	public String fileExt = ".sym";
	ArrayList<Symbol> asmCode = new ArrayList<Symbol>();
	
	public SymbolTable() {
		super();
	}
	
	public boolean buildSymbolTable(String[] source) {
		boolean result = true;
		for(String line : source) {
			
		}
		return result;
	}
	
	
	@Override
	public void writeFile() {
		// TODO Auto-generated method stub
		
		super.writeFile();
	}

}
