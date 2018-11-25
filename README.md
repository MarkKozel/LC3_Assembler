# LC3 Assembler
Java-based assembler for Little Computer 3 (LC-3) [Wikipedia Page](https://en.wikipedia.org/wiki/LC-3)

## Purpose
Create a command-line assembler that can be used to generate all output files of the LC-3 Simulator 3.01 [McGraw Hill Textbook Reference](http://highered.mheducation.com/sites/0072467509/index.html)

This assembler will produce .obj, .lst, and .sym file to support the LC-3 Simulator virtual environment. .obj will be binary compatable to the LC-3 Simulator assembler output

### File Input
* .asm - LC-3 Assembly souce code file
### File Output
* .sym - Symbol table file
* .bin - Binary representation of source file
* .hex - Hex representation of source file
* .lst - List file of source file
* .obj - LC-3 executable object file

[logo]: https://github.com/MarkKozel/LC3_Assembler/design/01_Overview.png "Overview of Classes"


---
### Dev Log

2018-11-24 - Added classes to generate Listing and Executable files. Added JUnit test package and test files for some classes. Currently taking a deep-dive to get Operate Instrucitons working all teh way to .org file.

2018-11-12 - Completed logic to parse PseudoOps and Operate commands. Added SymbolTable and Symbol classes to extract and contain symbols. Project can produce a complete symbol table and output to disk file.

2018-11-11 - Started project. Defined classes to hold Instructions (Comments, Control, DataMovement, Operate, and PseudoOps). Created package `net.markkozel.lc3` to hold classes related to LC-3 capabilities. Built main in Assembler class. Main reads in .asm file from command-line and populates list of Instruction objects
