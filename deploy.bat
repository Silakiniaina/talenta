@echo off

:: Define your project directories
set PROJECT_DIR=%~dp0
set SRC_DIR=%PROJECT_DIR%src
set BIN_DIR=%PROJECT_DIR%bin
set LIB_DIR=%PROJECT_DIR%lib
set TMP_DIR=%PROJECT_DIR%temp

:: Create bin and tmp directories if they don't exist
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"
if not exist "%TMP_DIR%" mkdir "%TMP_DIR%"

:: Copy only .java files from src to tmp
for /R "%SRC_DIR%" %%f in (*.java) do (
    xcopy "%%f" "%TMP_DIR%" /I
)

:: Compile the code
cd "%TMP_DIR%"
javac -d "%BIN_DIR%" -cp "%LIB_DIR%/*" *.java

rmdir /q/s "temp"

set target_dir=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps

set bin-dir="bin"
set web-dir="web/jsp"
set assets-dir="assets"
set lib-dir="lib"
set web-xml="conf"

cd "%PROJECT_DIR%"

set target-dir="C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"

rmdir /q/s "temp"
mkdir "temp/WEB-INF"
mkdir "temp/WEB-INF/classes"
mkdir "temp/WEB-INF/lib"
mkdir "temp/WEB-INF/views"

echo D | xcopy /q/y/s %web-dir% "temp/WEB-INF/views"
echo D | xcopy /q/y/s %assets-dir% "temp/assets"
echo D | xcopy /q/s/y %bin-dir% "temp/WEB-INF/classes"
echo D | xcopy /q/s/y %lib-dir% "temp/WEB-INF/lib"
echo D | xcopy /q/y/s %web-xml% "temp/WEB-INF/"

jar -cvf talenta.war -C temp/ .

echo D | xcopy /q/y talenta.war %target-dir%

rmdir /q/s "temp"

endlocal
