
#ck compiler and ck assembler executables
CKC = java -jar ../../compiler/out/artifacts/ckc_jar/ckc.jar
CKA = java -jar ../../compiler/out/artifacts/cka_jar/cka.jar

#ck assembler options
STACKSIZE = 1000
HEAPSIZE = 1000
WORDSIZE = 8
GC = copying

#cka options
CKAFLAGS = -s ${STACKSIZE} -m ${HEAPSIZE} -w ${WORDSIZE} -g ${GC}

#C flags
CFLAGS = -Wall -Wextra

#path to ck runtime implementation
RUNTIMEDIR = ../../runtime

#chosen gc impl
GCIMPL = ../../runtime/${GC}_gc.c

#directory of output
OUTDIR = out

#make outdir and simple_io.exe
all: ${OUTDIR} ${OUTDIR}/simple_io

out:
	mkdir out

#use gcc to compile generated c code
out/simple_io: out/simple_io.c
	gcc out/simple_io.c ${RUNTIMEDIR}/builtin_cfuns.c ${GCIMPL} -o ${OUTDIR}/simple_io -I${RUNTIMEDIR} ${CFLAGS}

#use cka to generate c from simple_io.cka
out/simple_io.c: out/simple_io.cka
	${CKA} -i out/simple_io.cka -o ${OUTDIR}/simple_io.c ${CKAFLAGS}

#use ckc to generate cka from simple_io.ck
out/simple_io.cka: simple_io.ck
	${CKC} -i simple_io.ck -o ${OUTDIR}/simple_io.cka

#remove outdir
clean:
	rm -rf ${OUTDIR}