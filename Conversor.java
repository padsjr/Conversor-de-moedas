import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double valor;
        int moeda1, moeda2;
        double taxa;

        valor = entradaValor(sc);
        System.out.println("O valor digitado foi: " + valor);
        System.out.println("Selecione a moeda que voce deseja converter");
        moeda1 = entradaMoeda(sc);
        System.out.println("Selecione agora em qual moeda sera convertida");
        moeda2 = entradaMoeda(sc);
        System.out.println("Qual a taxa de conversão da moeda " + moeda1 + " para a moeda " + moeda2 + ": ");
        taxa = entradaTaxa(sc);
        System.out.println("O valor digitado foi: " + valor + "Convertendo a "+ moeda1 + " para a moeda " + moeda2 + " terá um total de " + converterMoeda(valor, taxa) + " " + moeda2);






    }
    public static double entradaValor( Scanner sc) {
        while (true) {
            System.out.println("Digite o valor do moeda: ");
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Valor invalido, digite novamente");
            }
        }
    }
    public static int entradaMoeda( Scanner sc) {
        while (true) {
            System.out.println("1 - Dólar (USD)");
            System.out.println("2 - Euro (EUR)");
            System.out.println("3 - Libra (GBP)");
            int moedaSelecionada = sc.nextInt();

            try{
                if (3 < moedaSelecionada) {
                    System.out.println("Valor invalido, digite novamente");
                    continue;
                }
                return moedaSelecionada;
            }catch (InputMismatchException e) {
                System.out.println("Moeda invalida, digite novamente");
                sc.nextLine();
            }
        }
}
    public static double entradaTaxa( Scanner sc) {
        while (true) {
            try{
                return sc.nextDouble();
            }catch (InputMismatchException e) {System.out.println("Taxa invalida, digite novamente");}
        }
    }
    public static double converterMoeda(double valor, double taxa) {
        return valor * taxa;
    }
}
