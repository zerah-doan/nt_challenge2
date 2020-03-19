package quangdoan.biz;

import com.jayway.jsonpath.JsonPath;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Image {
    private String id;
    private String camModel;
    private String focalLeng;

    public String getCamModel() {
        return camModel;
    }

    public String getId() {
        return id;
    }

    public Image setId(String id) {
        this.id = id;
        return this;
    }

    public Image setCamModel(String camModel) {
        this.camModel = camModel;
        return this;
    }

    public String getFocalLeng() {
        return focalLeng;
    }

    public String getFocalLeng(String unit) {
        return focalLeng + unit;
    }

    public Image setFocalLeng(String focalLeng) {
        this.focalLeng = focalLeng;
        return this;
    }
}
