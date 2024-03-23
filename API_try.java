import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import java.util.*;
import java.net.*;


public class API_test {


    public static void main(String[] args)
    {

        // Using REST API OAuth1.0 Credentials
        String apiKey = "99f449862f264a0fa49305ddef7ee141";
        String apiSecretKey = "697b55f1af33458eb93c963ed367bb9f";

        String foodCall = "chicken"; // the user tries to find  dishes with chicken

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = buildRequest(apiKey, apiSecretKey, foodCall);

        try {
            // Send the request and receive the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        }

        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static HttpRequest buildRequest(String apiKey, String apiSecretKey, String foodCall) {
        String url = "https://platform.fatsecret.com/rest/server.api";
        String method = "foods.search";
        String format = "json";

        String signature = OAuthSignature.generateSign(url, method, apiKey, apiSecretKey, format, foodCall);

        String requestUrl = url + "?method=" + method +
                "&format=" + format +
                "&food_query=" + foodCall +
                "&oauth_consumer_key=" + apiKey +
                "&oauth_signature_method=HMAC-SHA1" +
                "&oauth_timestamp=" + System.currentTimeMillis() / 1000 +
                "&oauth_nonce=" + System.currentTimeMillis() +
                "&oauth_version=1.0" +
                "&oauth_signature=" + signature;

        return HttpRequest.newBuilder().uri(URI.create(requestUrl)).GET().build();
    }
}

