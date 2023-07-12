import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.IOException;
//import antlr.*;
import static IRBuilder.IRModule.PrintModuleToFile;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("input path is required");
        }
        String source = args[0];
        String dest = args[1];

        CharStream input = CharStreams.fromFileName(source);
        // Lexer
        SysYLexer sysYLexer = new SysYLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(sysYLexer);
        // Parser
        SysYParser sysYParser = new SysYParser(tokens);
        ParseTree tree = sysYParser.program();
        // Generate intermediate code(IR)
        IRGenVisitor irGenVisitorVisitor = new IRGenVisitor();
        irGenVisitorVisitor.visit(tree);

        PrintModuleToFile(irGenVisitorVisitor.getModule(), dest);
    }
}
