import java.util.Map;

public class Moneda {
    private Map<String, Double> conversion_rates;

    public Moneda(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public Double obtenerTasaDeConversion(String moneda) {
        return conversion_rates.getOrDefault(moneda, null);
    }
}
