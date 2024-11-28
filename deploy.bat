@echo off
setlocal


set current=%~dp0
set bin=%current%bin
set lib=%current%lib
set src=%current%src
set temp=%current%src\temp


if not exist "%temp%" (
    mkdir "%temp%"
)


for /r "%src%" %%f in (*.java) do (
    copy "%%f" "%temp%" >nul
)

javac -d "%bin%" -cp "%lib%\*" "%temp%\*.java"

rmdir /s /q "%temp%"

set src_dir=bin
set web_dir=web\jsp
set lib_dir=lib
set assets_dir=assets
set config_dir=conf

set target_name=talenta

set target_dir=C:\path\to\apache-tomcat-10.1.23\webapps\

if exist "temp" (
    rmdir /s /q "temp"
)
mkdir "temp"
mkdir "temp\WEB-INF"
mkdir "temp\WEB-INF\classes"
mkdir "temp\WEB-INF\lib"
mkdir "temp\WEB-INF\views"
mkdir "temp\assets"

xcopy "%lib_dir%\*" "temp\WEB-INF\lib" /e /i /q
xcopy "%src_dir%\*" "temp\WEB-INF\classes" /e /i /q
xcopy "%web_dir%\*" "temp\WEB-INF\views" /e /i /q
copy "%web_dir%\index.jsp" "temp\"
del "temp\WEB-INF\views\index.jsp"
xcopy "%config_dir%\*" "temp\WEB-INF" /e /i /q
xcopy "%assets_dir%\*" "temp\assets" /e /i /q

jar -cf "%target_name%.war" -C temp .

copy "%target_name%.war" "%target_dir%"

del "%target_name%.war"
rmdir /s /q "temp"

endlocal
