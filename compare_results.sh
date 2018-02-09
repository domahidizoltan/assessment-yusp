#!/usr/bin/env bash

ACTUAL_RESULTS_FOLDER=src/main/resources/data/output/
EXPECTED_RESULTS_FOLDER=src/test/resources/data/output/

for file in ${ACTUAL_RESULTS_FOLDER}*
do
    filename=$(basename $file)
    echo "Comparing files ${filename} ..."
    diff ${ACTUAL_RESULTS_FOLDER}${filename} ${EXPECTED_RESULTS_FOLDER}${filename}
    echo
done