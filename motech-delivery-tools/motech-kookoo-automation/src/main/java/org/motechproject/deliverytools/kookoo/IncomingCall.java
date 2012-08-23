package org.motechproject.deliverytools.kookoo;

import com.google.gson.reflect.TypeToken;
import org.motechproject.dao.MotechJsonReader;
import org.motechproject.server.kookoo.KookooCallServiceImpl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;

public class IncomingCall {
    private String apiKey;
    private String phoneNumber;
    private HashMap<String, String> customParams;

    public IncomingCall(String queryString) throws IOException {
        QueryParams queryParams = QueryParams.fromQueryString(queryString);
        apiKey = queryParams.getString(KookooCallServiceImpl.API_KEY_KEY);
        phoneNumber = queryParams.getString(KookooCallServiceImpl.PHONE_NUMBER_KEY);

        MotechJsonReader jsonReader = new MotechJsonReader();
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();

        String urlString = URLDecoder.decode(URLDecoder.decode(queryParams.getString(KookooCallServiceImpl.URL_KEY), "UTF-8"), "UTF-8");
        URL callbackURL = new URL(urlString);
        String customQuery = callbackURL.getQuery();
        QueryParams customQueryParams = QueryParams.fromQueryString(customQuery);
        customParams = (HashMap<String, String>) jsonReader.readFromString(customQueryParams.getString(KookooCallServiceImpl.CUSTOM_DATA_KEY), type);
    }

    public String apiKey() {
        return apiKey;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public String customData(String customDataKey) {
        return customParams.get(customDataKey);
    }

    public HashMap<String, String> customParams() {
        return customParams;
    }
}
