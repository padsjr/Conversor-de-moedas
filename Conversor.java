import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double valor;
        int moeda1, moeda2;
        double taxa;



        System.out.println("Selecione a moeda que voce deseja converter");
        moeda1 = entradaMoeda(sc);
        valor = entradaValor(sc);
        System.out.println("O valor digitado foi: " + valor);
        System.out.println("Selecione agora em qual moeda sera convertida");
        moeda2 = entradaMoeda(sc);
        System.out.println("Voce selecionou a conversão de  " + valor +"$ " + nomearMoeda(moeda1) + ", para converter em "+ nomearMoeda(moeda2));
        System.out.println("Convertido a moeda, voce teria " +  converterMoeda(moeda1, moeda2, valor) + "$ " + nomearMoeda(moeda2));






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

            try{
                int moedaSelecionada = sc.nextInt();
                if (3 < moedaSelecionada || 1 > moedaSelecionada) {
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
public static String nomearMoeda( int moeda) {
        switch (moeda) {
            case 1:
                return "Dolares";
            case 2:
                return "Euros";
            case 3:
                return "Libras";
        }
    return "Ocorreu um erro ao identificar a moeda";
}
    public static double converterMoeda(int moeda1, int moeda2,double valor) {
        double dolarEuro = 0.87;
        double dolarLibra = 0.75;
        double euroLibra = 0.86;

        switch (moeda1){
            case 1:
               switch (moeda2){
                    case 1:
                        return valor;
                    case 2:
                        return valor*dolarEuro;
                    case 3:
                        return valor*dolarLibra;
               }
            case 2:
               switch (moeda2){
                    case 1:
                        return valor/dolarEuro;
                    case 2:
                        return valor;
                    case 3:
                        return valor*euroLibra;
               }
            case 3:
                switch (moeda2){
                    case 1:
                        return valor/dolarLibra;
                    case 2:
                        return valor/euroLibra;
                }
        }
        return 0;
    }
}
