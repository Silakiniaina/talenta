#!/bin/bash

current_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
bin_dir="$current_dir/bin"
lib_dir="$current_dir/lib"
temp_dir="$current_dir/src/temp"

if [ ! -d "$temp_dir" ]; then
  mkdir "$temp_dir"
fi

find "$current_dir/src" -type f -name "*.java" -exec cp -r {} "$temp_dir" \;
javac -d "$bin_dir" -cp "$lib_dir/*" "$current_dir"/src/temp/*.java
rm -R "$current_dir"/src/temp

echo "Compilation finished"
java -cp "bin:lib/*" model.Personne