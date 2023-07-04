export CLASSPATH=/usr/local/lib/antlr-*-complete.jar

ANTLR = java -jar /usr/local/lib/antlr-*-complete.jar -listener -visitor -long-messages
JAVAC = javac -g
JAVA = java


PFILE = $(shell find ./src/main/java/antlr/ -name "SysYParser.g4")
LFILE = $(shell find ./src/main/java/antlr/ -name "SysYLexer.g4")
JAVAFILE = $(shell find ./src/main/java/antlr/ -name "*.java")
ANTLRPATH = $(shell find /usr/local/lib -name "antlr-*-complete.jar")

antlr: $(LFILE) $(PFILE)
	$(ANTLR) $(PFILE) $(LFILE)

clean:
	rm -f ./src/main/java/antlr/*.tokens
	rm -f ./src/main/java/antlr/*.interp
	rm -f ./src/main/java/antlr/*.java
	rm -f ./src/main/java/antlr/*.class
	rm -f ./src/main/java/antlr/SysYLexer.java ./src/main/java/antlr/SysYParser.java ./src/main/java/antlr/SysYParserBaseListener.java ./src/main/java/antlr/SysYParserBaseVisitor.java ./src/main/java/antlr/SysYParserListener.java ./src/main/java/antlr/SysYParserVisitor.java
	rm -rf classes


.PHONY: antlr clean


