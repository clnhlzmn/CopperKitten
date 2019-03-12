
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

#chosen gc impl, currently hardcoded in cka, could be option, would still have to link correct implementation here
GCIMPL = ../../runtime/${GC}_gc.c

#directory of output
OUTDIR = out

#make outdir and loop.exe
all: ${OUTDIR} ${OUTDIR}/loop

out:
	mkdir out

#use gcc to compile generated c code
out/loop: out/loop.c loop_cfuns.c
	gcc out/loop.c loop_cfuns.c ${GCIMPL} -o ${OUTDIR}/loop -I${RUNTIMEDIR} ${CFLAGS}

#use cka to generate c from loop.cka
out/loop.c: out/loop.cka
	${CKA} -i out/loop.cka -o ${OUTDIR}/loop.c ${CKAFLAGS}

#use ckc to generate cka from loop.ck
out/loop.cka: loop.ck
	${CKC} -i loop.ck -o ${OUTDIR}/loop.cka

#remove outdir
clean:
	rm -rf ${OUTDIR}