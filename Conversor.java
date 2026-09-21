import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double valor;
        String moeda1, moeda2;
        double taxa;

        valor = entradaValor(sc);
        System.out.println("O valor digitado foi: " + valor);
        System.out.println("Digite agora a primeira moeda");
        moeda1 = entradaMoeda(sc);
        System.out.println("Digite agora a segunda moeda");
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
    public static String entradaMoeda( Scanner sc) {
        while (true) {
            try{
                return sc.next();
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
