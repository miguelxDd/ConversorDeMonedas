import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PeticionApiServicio {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/a1b0b164ec36ccb188d07428/latest/";

    public PeticionApiRespuesta fetchRates(String solicitud) throws IOException {
        try {
            URL url = new URL(API_URL + solicitud);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Gson gson = new Gson();
                return gson.fromJson(response.toString(), PeticionApiRespuesta.class);
            } else {
                throw new IOException("Error en la Respuesta de la API: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Error al realizar la petición a la API: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            throw e;
        }
    }
}
