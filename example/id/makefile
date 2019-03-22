
#ck compiler and ck assembler executables
CKC = java -jar ../../compiler/out/artifacts/ckc_jar/ckc.jar
CKA = java -jar ../../compiler/out/artifacts/cka_jar/cka.jar

#ck assembler options
STACKSIZE = 100
HEAPSIZE = 1000
WORDSIZE = 8
GC = mark_compact

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

#make outdir and id.exe
all: ${OUTDIR} ${OUTDIR}/id

out:
	mkdir out

#use gcc to compile generated c code
out/id: out/id.c id_cfuns.c
	gcc out/id.c id_cfuns.c ${GCIMPL} -o ${OUTDIR}/id -I${RUNTIMEDIR} ${CFLAGS}

#use cka to generate c from id.cka
out/id.c: out/id.cka
	${CKA} -i out/id.cka -o ${OUTDIR}/id.c ${CKAFLAGS}

#use ckc to generate cka from id.ck
out/id.cka: id.ck
	${CKC} -i id.ck -o ${OUTDIR}/id.cka

#remove outdir
clean:
	rm -rf ${OUTDIR}