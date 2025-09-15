import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final int NUMERO_SIMULACOES = 1000;
        final int N_CLIENTES = 100;
        final double MU = 5.0;

        int[] caixas = {1, 2, 3};
        double[] sigmas = {0.25, 0.5, 1.0, 2.0};

        for (int c : caixas) {
            for (double sigma : sigmas) {
                List<Double> medias = new ArrayList<>();
                SimulacaoCaixaSupermercado sim = new SimulacaoCaixaSupermercado();
                sim.setNumeroCaixas(c);
                sim.setMediaAtendimentos(N_CLIENTES);
                sim.setMediaTempoAtendimentoPorCliente(MU);
                sim.setDesvioPadraoTempoAtendimentoPorCliente(sigma);

                for (int i = 0; i < NUMERO_SIMULACOES; i++) {
                    medias.add(sim.simular());
                }

                double media = media(medias);
                double dp = desvioPadrao(medias, media);
                System.out.printf(
                    "Caixas: %d | σ: %.2f | Média: %.3f min | DP: %.3f min%n",
                    c, sigma, media, dp
                );
            }
        }
    }

    private static double media(List<Double> xs) {
        return xs.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private static double desvioPadrao(List<Double> xs, double m) {
        double s2 = xs.stream().mapToDouble(x -> Math.pow(x - m, 2)).sum();
        return Math.sqrt(s2 / (xs.size() - 1));
    }
}
