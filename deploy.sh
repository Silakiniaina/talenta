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

src_dir="bin"
web_dir="web/jsp"
lib_dir="lib"
assets_dir="assets"
config_dir="conf"

target_name="talenta"

#ito no ovaina atao chemin  makany amin'ny webapps ao amin'ny pc anareo 
target_dir="/opt/apache-tomcat-10.1.23/webapps/"

rm -rf "temp"
mkdir "temp"
mkdir "temp/WEB-INF"
mkdir "temp/WEB-INF/classes"
mkdir "temp/WEB-INF/lib"
mkdir "temp/WEB-INF/views"
mkdir "temp/assets"

cp -r "$lib_dir"/* "temp/WEB-INF/lib"
cp -r "$src_dir"/* "temp/WEB-INF/classes"
cp -r "$web_dir"/* "temp/WEB-INF/views"
cp  "$web_dir"/index.jsp "temp/"
rm "temp/WEB-INF/views/index.jsp"
cp -r "$config_dir"/* "temp/WEB-INF"
cp -r "$assets_dir"/* "temp/assets"

jar -cf "$target_name.war" -C temp/ .

cp "$target_name.war" "$target_dir"
rm "$target_name.war"
rm -rf "temp"