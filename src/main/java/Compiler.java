import backend.RegisterAllocate;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.IOException;
import antlr.*;
import static IRBuilder.IRModule.PrintModuleToFile;
import backend.codeGen;

public class Compiler {
    public static void main(String[] args) throws IOException {
//      功能测试： compiler testcase.sysy -S -o testcase.s
//      性能测试： compiler testcase.sysy -S -o testcase.s -O1

        String source = args[0];
        String mcDest = args[3];

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

        codeGen code = new codeGen();
        code.MachineCodeGen(irGenVisitorVisitor.getModule());
        RegisterAllocate allocator = new RegisterAllocate(code.getMCFunctions());
        allocator.easyAllocate();
        code.PrintCodeToFile(mcDest);
    }
}
