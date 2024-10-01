package views;
import java.util.Scanner;

public class MenuView {
    public static void menuPrincipal() {
        Scanner input = new Scanner(System.in);
        int escolha = 0;
        boolean controller = false;

        do {
            try {
                System.out.println("---------MENU----------");
                System.out.println(" 1. Hospede");
                System.out.println(" 2. Funcionario");
                System.out.println(" 3. Quarto");
                System.out.println(" 4. Reserva");
                System.out.println(" 5. Sair");
                System.out.println("-----------------------");
                System.out.print("-> ");
                escolha = input.nextInt();

                switch(escolha) {
                    case 1:
                        System.out.println("Case 1");
                        break;
                    case 2:
                        System.out.println("Case 2");
                        break;
                    case 3:
                        System.out.println("Case 3");
                        break;
                    case 4:
                        System.out.println("Case 4");
                        break;
                    case 5:
                        controller = true;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! ");
                }

            } catch(Exception e) {
                System.out.println("Valor invalido!");
            } finally {
                input.nextLine(); // Limpa o buffer
            }
        } while(!controller);

        input.close();
    }
}
