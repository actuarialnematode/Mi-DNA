#!/bin/bash

cp uploads/$1.vcf $1.vcf

bgzip $1.vcf
tabix -f $1.vcf.gz

echo "Finished zipping."

CONDA_BASE=$(conda info --base)

. $CONDA_BASE/etc/profile.d/conda.sh
conda activate varfish-annotator

varfish-annotator \
    -XX:MaxHeapSize=10g \
    -XX:+UseConcMarkSweepGC \
    annotate \
    --db-path ./varfish-annotator-20201006/varfish-annotator-db-20201006.h2.db \
    --ensembl-ser-path varfish-annotator-20201006/hg19_ensembl.ser \
    --refseq-ser-path varfish-annotator-20201006/hg19_refseq_curated.ser \
    --ref-path varfish-annotator-20201006/hs37d5.fa \
    --input-vcf "$1.vcf.gz" \
    --release "GRCh37" \
    --output-db-info "FAM_name.db-info.tsv" \
    --output-gts "FAM_name.gts.tsv" \
    --case-id "FAM_name"
    
echo "Finished annotating."

gzip -c FAM_name.db-info.tsv >FAM_name.db-info.tsv.gz
md5sum FAM_name.db-info.tsv.gz >FAM_name.db-info.tsv.gz.md5
gzip -c FAM_name.gts.tsv >FAM_name.gts.tsv.gz
md5sum FAM_name.gts.tsv.gz >FAM_name.gts.tsv.gz.md5

cd varfish-cli
pip install -e .
conda activate varfish-cli
cd ..

varfish-cli --no-verify-ssl case create-import-info --resubmit a1ab6df9-5c9e-483b-bba1-afab33c53215 FAM_name.db-info.tsv.gz FAM_name.gts.tsv.gz INPUT3.ped

echo "Finished importing."

