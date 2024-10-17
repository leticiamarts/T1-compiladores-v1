import java.util.HashMap;
import java.util.Map;

public class TabelaDeSimbolos {
    private Map<String, String> tabela; // Nome da variável -> Tipo

    public TabelaDeSimbolos() {
        tabela = new HashMap<>();
    }

    public void adicionarSimbolo(String nome, String tipo) {
        tabela.put(nome, tipo);
    }

    public String obterTipo(String nome) {
        return tabela.get(nome);
    }

    public boolean existeSimbolo(String nome) {
        return tabela.containsKey(nome);
    }
}
