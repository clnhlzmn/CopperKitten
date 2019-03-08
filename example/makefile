
CKC = java -jar "..\compiler\out\artifacts\ckc_jar\ckc.jar"
CKA = java -jar "..\compiler\out\artifacts\cka_jar\cka.jar"

STACKSIZE = 100
HEAPSIZE = 1000
WORDSIZE = 4

CKAFLAGS = -s ${STACKSIZE} -m ${HEAPSIZE} -w ${WORDSIZE}

example.c : example.cka
	${CKA} -i example.cka -o example.c ${CKAFLAGS}

example.cka : example.ck
	${CKC} -i example.ck -o example.cka