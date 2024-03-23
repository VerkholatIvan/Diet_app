import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OAuthSignature {

    public static String generateSign(
            String URL,
            String method,
            String key,
            String secret,
            String format,
            String foodCall
    )
    {
        try {
            String baseString = method + "&" + URLEncoder.encode(URL, StandardCharsets.UTF_8) + "&" +
                    URLEncoder.encode(
                            "format=" + format +
                                    "&food_query=" + URLEncoder.encode(foodCall, StandardCharsets.UTF_8) +
                                    "&oauth_consumer_key=" + key +
                                    "&oauth_nonce=" + System.currentTimeMillis() +
                                    "&oauth_signature_method=HMAC-SHA1" +
                                    "&oauth_timestamp=" + System.currentTimeMillis() / 1000 +
                                    "&oauth_version=1.0",
                            StandardCharsets.UTF_8
                    );

            String signInKey = URLEncoder.encode(secret, StandardCharsets.UTF_8) + "&";
            SecretKeySpec keySpec = new SecretKeySpec(signInKey.getBytes(), "HmacSHA1");

            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(keySpec);

            byte[] result = mac.doFinal(baseString.getBytes());

            return Base64.getEncoder().encodeToString(result);
        }


        catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
