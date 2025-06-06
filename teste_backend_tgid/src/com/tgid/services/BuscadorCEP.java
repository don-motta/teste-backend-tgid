package com.tgid.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tgid.exceptions.AddressNotFoundException;
import com.tgid.exceptions.InvalidCepException;
import com.tgid.models.Cep;
import com.tgid.models.ViaCEP;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscadorCEP {

    public Cep buscarCEP(String cep) throws IOException, InterruptedException {
        try {
            if (cep.length() != 8) {
                throw new InvalidCepException();
            }
            for (char c : cep.toCharArray()) {
                if (!Character.isDigit(c)) {
                    throw new InvalidCepException();
                }
            }
            String address = "https://viacep.com.br/ws/" + cep + "/json/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(address))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            if (jsonObject.has("erro")) {
                throw new AddressNotFoundException("Desculpe! Endereço não encontrado.");
            }
            ViaCEP searchResult = gson.fromJson(json, ViaCEP.class);
            Cep result = new Cep(searchResult);
            return result;
        } catch (StringIndexOutOfBoundsException | InvalidCepException e) {
            System.out.println("Número invalido! Digite novamente, lembrando que o número do CEP deve ter exatamente 8 digitos numéricos, e deverá ser digitado sem hifen.");
        } catch (AddressNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
