/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.my2dgame.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class RingService {

    public static void postRingRegister(String name, String descricao, int quantidade) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String json = String.format(
                    "{\"name\": \"%s\", \"descricao\": \"%s\", \"quantidade\": %d}",
                    name, descricao, quantidade
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3307/anel/create"))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Resposta: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
   
}
