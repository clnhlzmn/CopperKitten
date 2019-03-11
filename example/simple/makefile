
#ck compiler and ck assembler executables
CKC = java -jar ../../compiler/out/artifacts/ckc_jar/ckc.jar
CKA = java -jar ../../compiler/out/artifacts/cka_jar/cka.jar

#ck assembler options
STACKSIZE = 100
HEAPSIZE = 1000
WORDSIZE = 4

#C flags
CFLAGS = -Wall -Wextra

#cka options
CKAFLAGS = -s ${STACKSIZE} -m ${HEAPSIZE} -w ${WORDSIZE}

#path to ck runtime implementation
RUNTIMEDIR = ../../runtime

#chosen gc impl, currently hardcoded in cka, could be option, would still have to link correct implementation here
GCIMPL = ../../runtime/mark_compact_gc.c

#directory of output
OUTDIR = out

#make outdir and example.exe
all: ${OUTDIR} ${OUTDIR}/example

out:
	mkdir out

#use gcc to compile generated c code
out/example: out/example.c example_cfuns.c
	gcc out/example.c example_cfuns.c ${GCIMPL} -o ${OUTDIR}/example -I${RUNTIMEDIR} ${CFLAGS}

#use cka to generate c from example.cka
out/example.c: out/example.cka
	${CKA} -i out/example.cka -o ${OUTDIR}/example.c ${CKAFLAGS}

#use ckc to generate cka from example.ck
out/example.cka: example.ck
	${CKC} -i example.ck -o ${OUTDIR}/example.cka

#remove outdir
clean:
	rm -rf ${OUTDIR}