; Just a Comment

.ORIG x3000

ADD R0, R0, #5
Label ADD R1, R1, R0

AND R2, R2, #1
Label0 AND R3, R1, R2

NOT R2 R3
label2 NOT R0 R1

Label3 .FILL #6
Label4 .BLKW #10
Label5 .STRINGZ "test 123 45 6"
Label6 .FILL #0

.END
