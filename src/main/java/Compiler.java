import backend.RegisterAllocate;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
//import antlr.*;
import static IRBuilder.IRModule.PrintModuleToFile;
import backend.codeGen;
import pass.deadCode.DeadCodeScan;

public class Compiler {
    public static void main(String[] args) throws IOException {
//      功能测试：compiler -S -o testcase.s testcase.sy
//      性能测试： compiler testcase.sysy -S -o testcase.s -O1

        String source = args[3];
        String mcDest = args[2];
        boolean opt = args[4] != null;

        // hanlde the starttime and stoptime function
        File file = new File(source);
        String searchWord;
        String replaceWord;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            // execute the replace
            searchWord = "starttime\\(\\)";
            replaceWord = "_sysy_starttime(0)";
            String updateContent = content.toString().replaceAll(searchWord, replaceWord);

            searchWord = "stoptime\\(\\)";
            replaceWord = "_sysy_stoptime(0)";
            updateContent = updateContent.replaceAll(searchWord, replaceWord);

            // write the update content to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(updateContent);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }


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

        if(opt) {

        }


        codeGen code = new codeGen();
        code.MachineCodeGen(irGenVisitorVisitor.getModule());

        if(opt) {
            DeadCodeScan deadCodeScan = new DeadCodeScan();
            deadCodeScan.deadCodeScan(code);
        }

        RegisterAllocate allocator = new RegisterAllocate(code.getMCFunctions());
        allocator.easyAllocate();
        code.PrintCodeToFile(mcDest);
    }
}
