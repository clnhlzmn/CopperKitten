
CKC = java -jar ../../compiler/out/artifacts/ckc_jar/ckc.jar
CKA = java -jar ../../compiler/out/artifacts/cka_jar/cka.jar

STACKSIZE = 100
HEAPSIZE = 1000
WORDSIZE = 4

CKAFLAGS = -s ${STACKSIZE} -m ${HEAPSIZE} -w ${WORDSIZE}

RUNTIMEDIR = ../../runtime

GCIMPL = ../../runtime/mark_compact_gc.c

OUTDIR = out

all: ${OUTDIR} ${OUTDIR}/example

out:
	mkdir out

out/example: out/example.c
	gcc out/example.c ${GCIMPL} -o ${OUTDIR}/example -I${RUNTIMEDIR}

out/example.c: out/example.cka
	${CKA} -i out/example.cka -o ${OUTDIR}/example.c ${CKAFLAGS}

out/example.cka: example.ck
	${CKC} -i example.ck -o ${OUTDIR}/example.cka

clean:
	rm -rf ${OUTDIR}