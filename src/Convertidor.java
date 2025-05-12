public class Convertidor {

    private PeticionApiRespuesta peticionApi;

    public Convertidor(PeticionApiRespuesta peticionApi) {
        this.peticionApi = peticionApi;
    }

    public double convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        if (monedaOrigen.equals(monedaDestino)) {
            return cantidad;
        }

        Double tasaOrigen = peticionApi.getConversiones().get(monedaOrigen);
        Double tasaDestino = peticionApi.getConversiones().get(monedaDestino);

        if (tasaOrigen == null || tasaDestino == null) {
            throw new IllegalArgumentException("Moneda no soportada");
        }

        double tasaConversion = tasaDestino / tasaOrigen;
        return cantidad * tasaConversion;
    }

}
