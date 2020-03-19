package quangdoan.biz;

import com.jayway.jsonpath.JsonPath;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Business actions via API
 */
public class Api {
    private static final String API_URL = "https://unsplash.com/napi";
    private static final String token = "7R7zMwc23X2Cn-bq2jNbhUIorHcyTZvMAf87dXZKSQs";

    private static HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();


    public static Image getImage(String id) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(API_URL + "/photos/" + id)).build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            return null;
        }
        return new Image().setId(id)
                .setCamModel(JsonPath.read(response.body(), "$.exif.model"))
                .setFocalLeng(JsonPath.read(response.body(), "$.exif.focal_length"));
    }

    public static Collection createCollection(String title) {
        Map<Object, Object> data = new HashMap<>();
        data.put("title", title + random());
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildBody(data))
                .uri(URI.create(API_URL + "/collections"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            return null;
        }
        return new Collection().setTitle(title)
                .setId(JsonPath.read(response.body(), "$.id").toString());
    }

    public static void deleteCollection(String id) {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(API_URL + "/collections/" + id))
                .header("Authorization", "Bearer " + token)
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
        }
    }

    public static void addImageToCollection(String colId, String imgId) {
        Map<Object, Object> data = new HashMap<>();
        data.put("collection_id", colId);
        data.put("photo_id", imgId);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildBody(data))
                .uri(URI.create(API_URL + "/collections/" + colId + "/add"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
        }
    }


    /*public static String authorize() {
        Map<Object, Object> data = new HashMap<>();
        data.put("client_id", "D-aXpVrK43mBCysdNSsgaylSMTxLE_1eJ7s3fav9eJg");
        data.put("client_secret", "BB65hTziOrnr3Y1UdDFHAccJLvREDZwB6jLYKMOK6-M");
        data.put("redirect_uri", "urn:ietf:wg:oauth:2.0:oob");
        data.put("code", "revrjcHUM8FWfI_OAW_bljeqD-UFlex8eYq17RSHEEs");
        data.put("grant_type", "authorization_code");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildBody(data))
                .uri(URI.create("https://unsplash.com/oauth/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            return null;
        }
        return JsonPath.read(response.body(), "$.access_token");
    }*/

    private static HttpRequest.BodyPublisher buildBody(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    private static String random() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }
}
