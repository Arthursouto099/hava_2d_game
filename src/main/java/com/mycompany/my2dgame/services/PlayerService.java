/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame.services;
import com.mycompany.my2dgame.entity.PlayerInfo;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class PlayerService {
   
    
    public static PlayerInfo getPlayerWeb(int id) {
    try {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3307/character/" + id))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status HTTP: " + response.statusCode());
        System.out.println("Resposta do servidor: " + response.body());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();

            // Lê o JSON inteiro como um objeto genérico
            JsonNode root = mapper.readTree(response.body());

            // Extrai apenas o campo "data"
            JsonNode dataNode = root.get("data");

            if (dataNode != null && !dataNode.isNull()) {
                // Converte o campo "data" para PlayerInfo
                return mapper.treeToValue(dataNode, PlayerInfo.class);
            }
        } else {
            System.err.println("❌ Erro na requisição. Status: " + response.statusCode());
        }

    } catch (Exception e) {
        System.err.println("❌ Erro ao buscar jogador:");
        e.printStackTrace();
    }

    return null;
}

}
