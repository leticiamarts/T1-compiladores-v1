import java.util.*;

public class AnalisadorSemantico {
	private Hashtable tabelaSimbolos = new Hashtable();

	public void inserir_simbolo(Object identificador, Object tipo) {
		tabelaSimbolos.put(identificador, tipo);
	}
	
	public boolean verificar_atribuicao(Object variavel, Object valor) {
		boolean condicao_legal = true;
		if(tabelaSimbolos.get(variavel)==null) {
			System.out.println("Erro: Variavel [" + variavel + "] precisa ser declarada antes de ser usada.");
			condicao_legal = false;
		}else{
			String id = tabelaSimbolos.get(variavel).toString();

			if(id.equals("inteiro") && valor.toString().contains(".")) {
				System.out.println("Erro: Variavel [" + variavel + "] eh do tipo inteiro e nao pode receber um valor decimal");
				condicao_legal = false;
			}
			else if((id.equals("real") || id.equals("inteiro")) && valor.toString().contains("\"")) {
				System.out.println("Erro: Variavel [" + variavel + "] eh do tipo '"+ id +"' e deve receber um valor numerico");
				condicao_legal = false;
			}
			else if((id.equals("palavra") || id.equals("caracter")) && !valor.toString().contains("\"")) {
				System.out.println("Erro: Variavel [" + variavel + "] eh do tipo '"+ id + "' e nao pode receber valor numerico");
				condicao_legal = false;
			}
			else if(id.equals("caracter") && valor.toString().length()>3) {
				System.out.println("Erro: Variavel [" + variavel + "] eh do tipo char e nao pode receber uma string");
				condicao_legal = false;
			}
		}
		return condicao_legal;
	}

	public boolean verificar_incremento_dcremento(Object variavel) {
		boolean condicao_legal = true;
		String id = tabelaSimbolos.get(variavel).toString();

		if(tabelaSimbolos.get(variavel)==null) {
			System.out.println("Erro: Variavel [" + variavel + "] precisa ser declarada antes de ser usada.");
			condicao_legal = false;
		}else if(!id.equals("inteiro")){
			System.out.println("Erro: Apenas variaveis do tipo inteiro podem ser incrementadas ou decrementadas e [" + variavel + "] eh do tipo '" + id + "'");
			condicao_legal = false;
		}
		return condicao_legal;
	}

	public boolean verificar_expressao_relacional_entre_variaveis(Object var1, Object var2, Object op) {
		boolean condicao_legal = true;
		String operador = op.toString();

		if(tabelaSimbolos.get(var1)==null) {
			System.out.println("Erro: Variavel [" + var1 + "] precisa ser declarada antes de ser usada.");
			condicao_legal = false;
		}
		if((tabelaSimbolos.get(var2)==null)){
			System.out.println("Erro: Variavel [" + var2 + "] precisa ser declarada antes de ser usada.");
			condicao_legal = false;
		}else{
			String id1 = tabelaSimbolos.get(var1).toString();
			String id2 = tabelaSimbolos.get(var2).toString();

			if (operador.equals(">") || operador.equals("<") || operador.equals("<=")|| operador.equals(">=")) {
				if( id1.equals("caracter") || id2.equals("caracter") || id1.equals("palavra") || id2.equals("palavra") ){
					
					System.out.println("Os operadores de Maior, Menor, Maior Igual ou Menor Igual so podem ser relacionados a numeros");
					condicao_legal = false;
				}
			}
			else if (operador.equals("=")){
				if(!id1.equals(id2)){
					System.out.println("O operador igual so pode ser relacionado a variaveis de mesmo tipo");
					condicao_legal = false;
				}
			}
				
		}
		return condicao_legal;

	}
	public boolean verificar_expressao_relacional_entre_dados(Object dado1, Object dado2, Object op) {

		boolean condicao_legal = true;
		String operador = op.toString();

		if (operador.equals(">") || operador.equals("<") || operador.equals("<=")|| operador.equals(">=")) {
				if( dado1.toString().contains("\"") || dado2.toString().contains("\"") ){
					
					System.out.println("Os operadores de Maior, Menor, Maior Igual ou Menor Igual so podem ser relacionados a numeros");
					condicao_legal = false;
				}
		}

		return condicao_legal;
	}

	public boolean verificar_expressao_relacional_mista(Object dado, Object variavel, Object op) {

		boolean condicao_legal = true;
		String operador = op.toString();
		String id = tabelaSimbolos.get(variavel).toString();

		System.out.println("expressao mista");

		if(tabelaSimbolos.get(variavel)==null) {
			System.out.println("Erro: Variavel [" + variavel + "] precisa ser declarada antes de ser usada.");
			condicao_legal = false;
		}

		if (operador.equals(">") || operador.equals("<") || operador.equals("<=")|| operador.equals(">=")) {
				if( dado.toString().contains("\"") || id.equals("caracter") || id.equals("palavra") ){
					
					System.out.println("Os operadores de Maior, Menor, Maior Igual ou Menor Igual so podem ser relacionados a numeros");
					condicao_legal = false;
				}
		}

		return condicao_legal;
	}
	public boolean verificar_scanf(Object variavel) {
		boolean condicao_legal = true;
		if(tabelaSimbolos.get(variavel)==null) {
			System.out.println("Erro: Variavel [" + variavel + "] precisa ser declarada antes de ser lida.");
			condicao_legal = false;
		}
		return condicao_legal;
	}
	
}