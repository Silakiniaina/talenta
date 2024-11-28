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

java -cp "%bin%;%lib%\*" model.Candidat

endlocal
