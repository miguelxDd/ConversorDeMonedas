import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class PeticionApiRespuesta {
    private String resultado;
    private String base;
    @SerializedName("conversion_rates")
    private Map<String, Double> conversiones;

    public String getResultado() {
        return resultado;
    }

    public String getBase() {
        return base;
    }

    public Map<String, Double> getConversiones() {
        return conversiones;
    }
}
