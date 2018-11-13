import java.util.ArrayList;

import net.markkozel.lc3.AsmFile;
import net.markkozel.lc3.Instruction;
import net.markkozel.lc3.ParseSourceCode;
import net.markkozel.lc3.SymbolTable;

public class Assembler {
	String asmFileName;
	ArrayList<String> asmCode = new ArrayList<String>();
	ArrayList<Instruction> asmInstructions = new ArrayList<Instruction>();
	AsmFile asmFile = new AsmFile();
	ParseSourceCode parseSource = new ParseSourceCode();
	SymbolTable symbolTable = new SymbolTable();

	public static void main(String[] args) {
		if(args.length < 1){
			System.out.println("Usage: java LC3_Assembler <asm file>");
			System.exit(1);
		}
		
		Assembler assembler = new Assembler();
		
		assembler.asmFileName = args[0];
		assembler.loadAsmFile();
		
		System.exit(0);
	}
	
	public void loadAsmFile() {
		System.out.println("Assembling " + asmFileName);
		asmCode = asmFile.readAsmFile(asmFileName);
		asmInstructions = parseSource.parse(asmCode); //Parse each line
		
		symbolTable.buildSymbolTable(asmInstructions);
		
//		for(Instruction line : asmInstructions){
//			System.out.println(line.toString());
//		}
	}

}
