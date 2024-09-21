import controllers.UsuarioControle;

public class Main {
    public static void main(String[] args) {
        String nome = "asdsadad";
        String documento = "testeFINAL4";
        String telefone = "91231324";
        String email = "ated@gmail.com";
        String senha = "new22";

        System.out.println("Valores enviados:");
        System.out.println("Nome: " + nome);
        System.out.println("Documento: " + documento);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);

        
        UsuarioControle.cadastrarUsuario(nome, documento, telefone, email, senha);
    }
}
