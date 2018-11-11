import net.markkozel.lc3.AsmFile;

public class Assembler {
	String asmFileName;
	AsmFile asmFile = new AsmFile();

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
		System.out.println("Assembling" + asmFileName);
		asmFile.readAsmFile(asmFileName);
	}

}
