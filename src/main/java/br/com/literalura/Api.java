package br.com.literalura;


import br.com.literalura.model.BookRecord;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class Api {

    private static final String URL_BASE = "http://gutendex.com/books";

    private ObjectMapper mapper = new ObjectMapper();

    public List<BookRecord> requestData(String author, String title) throws IOException, InterruptedException {

        URI uri = UriComponentsBuilder
                .fromUriString(URL_BASE)
                .queryParam("search", author+" "+title)
                .build().toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        HttpResponse<String> httpResponse = HttpClient
                .newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() >= 400) {
            System.out.println("Falha ao consumir a API. Verifique a requisição e sua conexão!");
            return List.of();
        }

        JsonNode jsonNode = mapper.readTree(httpResponse.body());
        JsonNode results = jsonNode.findValue("results");

        List<BookRecord> bookRecords = mapper.convertValue(results, new TypeReference<List<BookRecord>>() {
        });

        return bookRecords;
    }

}

