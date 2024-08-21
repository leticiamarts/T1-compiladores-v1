import java.io.FileReader;
import java_cup.runtime.Symbol;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <input file>");
            return;
        }

        try {
            // Cria o objeto Lexer (analisador léxico) usando o arquivo de entrada
            Lexer lexer = new Lexer(new FileReader(args[0]));

            // Cria o objeto Parser (analisador sintático) com base no Lexer
            Parser parser = new Parser(lexer);

            // Tenta realizar a análise sintática do código-fonte
            parser.parse();
            System.out.println("Análise sintática concluída com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro durante a análise: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
