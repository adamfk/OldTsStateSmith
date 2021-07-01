:: REMEBER TO RUN IN CMD prompt and not POWERSHELL!

@echo off

:: javac *.java
:: usage `grun.bat Grammar1 <rule-name> -gui`
SET CLASSPATH=.;C:\libs\antlr-4.9.2-complete.jar;%CLASSPATH%

@REM java org.antlr.v4.gui.TestRig %*

java org.antlr.v4.gui.TestRig Grammar1 state_defn -gui -tokens -trace test_input.txt
