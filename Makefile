export CLASSPATH=/usr/local/lib/antlr-*-complete.jar

ANTLR = java -jar /usr/local/lib/antlr-*-complete.jar -listener -visitor -long-messages
JAVAC = javac -g
JAVA = java


PFILE = $(shell find . -name "SysYParser.g4")
LFILE = $(shell find . -name "SysYLexer.g4")
JAVAFILE = $(shell find ./ -name "*.java")
ANTLRPATH = $(shell find /usr/local/lib -name "antlr-*-complete.jar")

antlr: $(LFILE) $(PFILE)
	$(ANTLR) $(PFILE) $(LFILE)

clean:
	rm -f ./*.tokens
	rm -f ./*.interp
	rm -f ./*.java
	rm -f ./*.class
	rm -f ./SysYLexer.java src/SysYParser.java src/SysYParserBaseListener.java src/SysYParserBaseVisitor.java src/SysYParserListener.java src/SysYParserVisitor.java
	rm -rf classes


.PHONY: antlr clean


