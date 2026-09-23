import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double valor;
        int moeda1, moeda2;


        System.out.println("Selecione a moeda que voce deseja converter");
        moeda1 = entradaMoeda(sc);
        valor = entradaValor(sc);
        System.out.println("O valor digitado foi: " + valor);
        System.out.println("Selecione agora em qual moeda sera convertida");
        moeda2 = entradaMoeda(sc);

        String moeda1String = nomearMoeda(moeda1);
        String moeda2String = nomearMoeda(moeda2);

        System.out.printf("Voce selecionou a conversão de %.2f %s para converter em %s \n", valor, moeda1String, moeda2String);
        System.out.println("Convertido a moeda, voce teria " +  converterMoeda(moeda1String, moeda2String, valor) + " " + moeda2String);

        System.out.println(chamadaAPI(moeda1String, moeda2String));
    }


    public static double entradaValor( Scanner sc) {
        while (true) {
            System.out.println("Digite o valor do moeda: ");
            try {

                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Valor invalido, digite novamente");
                sc.nextLine();
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
                return "USD";
            case 2:
                return "EUR";
            case 3:
                return "GBP";
        }
    return "Ocorreu um erro ao identificar a moeda";
}
    public static double converterMoeda(String moeda1, String moeda2,double valor) {
        double taxaDeConversao = chamadaAPI(moeda1, moeda2);
        return valor*taxaDeConversao;
    }
    public static double chamadaAPI(String moedaConversao, String moedaConvertida) {

        HttpClient client = HttpClient.newHttpClient();

        String urlRequest = "https://economia.awesomeapi.com.br/json/last/" + moedaConversao + "-" + moedaConvertida;
        System.out.println(urlRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlRequest))
                .GET()
                .header("Accept", "application/json")
                .build();
        try{
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return obterCotacao(response.body());
            }

        }catch (IOException e) {
            System.out.println("Erro de conexão de rede: " + e.getMessage());
    }catch (InterruptedException e) {
            System.out.println("A requisição foi cancelada: " + e.getMessage());
}
        return 0;
    }
    public static double obterCotacao(String jsonBody){
        String termoBusca = "\"bid\":\"";
        int buscaInicioBid = jsonBody.indexOf(termoBusca);

        if (buscaInicioBid == -1) {
            System.out.println("Campo 'bid' não encontrado no JSON.");
            return 0.0;
        }
        buscaInicioBid += termoBusca.length();

        int buscaFimBid = jsonBody.indexOf("\"", buscaInicioBid);
        if (buscaFimBid == -1) {
            System.out.println("O \" não foi encontrado no JSON.");
            return 0.0;
        }
        String valorBid = jsonBody.substring(buscaInicioBid, buscaFimBid);
        return Double.parseDouble(valorBid);




    }
}
