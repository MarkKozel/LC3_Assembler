; Assignment:
; Module: 
; Name: PUTS Trap Rountine
; -----------------------
; Description of Code: Trap X22
; Note/Comments:

.ORIG x0450

	;;; Save Registers ;;;
SavRegs	ST R7, R7_Store ; Return Address
	ST R0, R0_Store ; Pointer to String to Puts
	ST R1, R1_Store
	ST R2, R2_Store

Loop	LDR R1, R0, #0; Get address of first char of string
	BRz Done ;	Char was 0, so we are done

Wait	LDI R2, DSR ;	Console available?
	BRzp Wait ;	No, loop back ans try again

	STI R1, DDR ;	Write char to console

	ADD R0, R0, #1;	Move pointer to next char in String
	BRnzp Loop ;	Loop back for next caracter

	;;; Restore Registers ;;;
Done	LD R0, R0_Store
	LD R1, R1_Store
	LD R2, R2_Store
	LD R7, R7_Store
	
	RET
		
;End of Program

;Data Declarations-------------

	DSR		.FILL xFE04
	DDR		.FILL xFE06
	MX0462		.FILL xF3FD
	MX0463		.FILL xF3FE
	R0_Store	.FILL #0
	R1_Store	.FILL #0
	R2_Store	.FILL #0
	R7_Store	.FILL #0


.END