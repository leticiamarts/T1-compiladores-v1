import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java_cup.runtime.*;

class Main {

    static boolean do_debug_parse = false;

    static public void main(String[] args) {
        // Verifica se um arquivo foi passado como argumento
        if (args.length < 1) {
            System.out.println("Uso: java Main <caminho_do_arquivo>");
            return;
        }

        // Cria o objeto de parsing
        Parser parser_obj = null;
        try {
            parser_obj = new Parser(new Lexer(new FileReader(args[0])));
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
            return;
        }

        // Tenta fazer o parsing do arquivo
        Symbol parse_tree = null;

        try {
            if (do_debug_parse) {
                parse_tree = parser_obj.debug_parse();
            } else {
                parse_tree = parser_obj.parse();
            }
            System.out.println("Parsing concluído com sucesso.");
        } catch (Exception e) {
            System.out.println("Parsing falhou: " + e.getMessage());
            e.printStackTrace(); // Exibe o stack trace para debugging.
        } finally {
            // Aqui você pode fazer o fechamento de recursos se necessário
        }
    }
}
