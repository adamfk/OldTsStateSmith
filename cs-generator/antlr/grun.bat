:: REMEBER TO RUN IN CMD prompt and not POWERSHELL!

:: javac *.java
:: usage `grun.bat Grammar1 r -gui`
SET CLASSPATH=.;C:\libs\antlr-4.9.2-complete.jar;%CLASSPATH%

java org.antlr.v4.gui.TestRig %*