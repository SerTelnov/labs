#!/bin/bash

file="target\classes\parser\RunnerKt.class"
class_path="target\classes"
exec_file="parser.RunnerKt"

if [[ -f ${file} ]]
then
    echo "print expression"
    kotlin -cp ${class_path} ${exec_file}
    read -p ""
else
    echo "didn't find $file"
fi

if [[ -f "tree.dot" ]]
then
    dot -Tpng tree.dot > output.png
    echo "tree for expression in output.png file"
fi

read -p "Press enter to exit.."