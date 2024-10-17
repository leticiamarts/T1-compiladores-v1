import java.io.*;

public class GeradorDeCodigo {
	
	String str1;
	String str2;
	String str3;

	public void gerar_condicional(Object cond) {
		String condicional = cond.toString();
		String[] termos = condicional.split("[==|>|<]");

		str1 = termos[0];
		str2 = termos[1];
		if (condicional.contains("<")){
			gravar_arquivo("\nmov eax, " + str1 + "\r\ncmp eax, " +  str2 + "\njge else");
		}
		if (condicional.contains(">")){
			gravar_arquivo("\nmov eax, " + str1 + "\r\ncmp eax, " +  str2 + "\njle else");
		}
		if (condicional.contains("==")){
			str2 = termos[2];
			gravar_arquivo("\nmov eax, " + str1 + "\r\ncmp eax, " +  str2 + "\njne else");
		}
	}	

	public void gerar_printf(Object texto) {
		str1 = texto.toString();
		str1 = str1.replace("\"", "");
		str1 = str1.concat("\\0");
		gravar_arquivo("\n.intel_syntax noprefix\r\n" + 
				"\r\n" + 
				".extern _printf\r\n" + 
				".global _printf2\r\n" + 
				"\r\n" + 
				"_texto: .ascii \""  + str1 + "\"\r\n" + 
				"\r\n" + 
				"_printf2:\r\n" + 
				"    push ebp\r\n" + 
				"    mov ebp, esp\r\n" + 
				"\r\n" + 
				"    push OFFSET _texto\r\n" + 
				"    call _printf\r\n" + 
				"    add esp, 4\r\n" + 
				"\r\n" + 
				"    pop ebp\r\n" + 
				"    ret\n");
	}

	public void gravar_arquivo(String cod) {
		  try{
			  FileWriter fstream = new FileWriter("codigo_gerado.txt",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(cod);
			  out.close();
		  }catch (Exception e){
			 System.err.println("Erro ao gravar arquivo: " +
		      e.getMessage());
		  }
	}
}