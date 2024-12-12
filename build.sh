#!/bin/bash

current="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
bin="$current/bin"
lib="$current/lib"
src="$current/src"
temp="$current/src/temp"

if [ ! -d "$temp" ]; then
  mkdir "$temp"
fi

find "$src" -type f -name "*.java" -exec cp -r {} "$temp" \;
javac -d "$bin" -cp "$lib/*" "$temp"/*.java
rm -R "$temp"

java -cp "bin:lib/*" model.employe.Presence