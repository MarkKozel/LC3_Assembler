import java.util.ArrayList;

import net.markkozel.lc3.AsmFile;
import net.markkozel.lc3.GenerateExecutables;
import net.markkozel.lc3.Instruction;
import net.markkozel.lc3.GenerateListing;
import net.markkozel.lc3.ParseSourceCode;
import net.markkozel.lc3.GenerateSymbolTable;
import net.markkozel.lc3.Shared;

public class Assembler {
	String asmFileName;
	ArrayList<String> asmCode = new ArrayList<String>();
	ArrayList<Instruction> asmInstructions = new ArrayList<Instruction>();
	AsmFile asmFile = new AsmFile();
	ParseSourceCode parseSource = new ParseSourceCode();
	GenerateSymbolTable symbolTable = new GenerateSymbolTable();
	GenerateListing lstGenerator = new GenerateListing();
	GenerateExecutables exeGenerator = new GenerateExecutables();
	Shared shared = Shared.getInstance();

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
		shared.updateFileInfo(asmFileName);
		asmCode = asmFile.readAsmFile(asmFileName);
		asmInstructions = parseSource.parse(asmCode); //Parse each line
		
		symbolTable.build(asmInstructions);		
		symbolTable.toFile();
		
		//lstGenerator.toFile(symbolTable.asmSymbols, asmInstructions);
		
		exeGenerator.toFile(symbolTable.asmSymbols, asmInstructions);
	}

}
