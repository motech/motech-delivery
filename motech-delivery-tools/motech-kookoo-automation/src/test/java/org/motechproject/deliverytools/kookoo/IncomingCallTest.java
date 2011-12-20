package org.motechproject.deliverytools.kookoo;

import org.json.JSONObject;
import org.junit.Test;
import org.motechproject.ivr.kookoo.KookooCallServiceImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class IncomingCallTest {
    @Test
    public void shouldParseWebResponse() throws IOException {
        HashMap<String, String> customParams = new HashMap<String, String>();
        customParams.put(KookooCallServiceImpl.IS_OUTBOUND_CALL, "true");
        JSONObject json = new JSONObject(customParams);
        String callbackBaseURL = "http://fds";
        String callbackURL = String.format("%s?%s=%s", callbackBaseURL, KookooCallServiceImpl.CUSTOM_DATA_KEY, json.toString());

        IncomingCall incomingCall = new IncomingCall(String.format("%s=foo&%s=676&%s=%s",
                KookooCallServiceImpl.API_KEY_KEY, KookooCallServiceImpl.PHONE_NUMBER_KEY,
                KookooCallServiceImpl.URL_KEY, URLEncoder.encode(callbackURL, "UTF-8")));
        assertEquals("foo", incomingCall.apiKey());
        assertEquals("676", incomingCall.phoneNumber());
        assertEquals("true", incomingCall.customData(KookooCallServiceImpl.IS_OUTBOUND_CALL));
    }

    @Test
    public void doubleEncodedURL() throws IOException {
        String callbackURL = "http%253A%252F%252Flocalhost%253A8080%252Ftama%252Fivr%252Freply%253FdataMap%253D%257B%2522is_outbound_call%2522%253A%2522true%2522%257D&phone_no=01705589013";
        IncomingCall incomingCall = new IncomingCall(String.format("%s=foo&%s=676&%s=%s",
                KookooCallServiceImpl.API_KEY_KEY, KookooCallServiceImpl.PHONE_NUMBER_KEY,
                KookooCallServiceImpl.URL_KEY, callbackURL));
        assertEquals("true", incomingCall.customData(KookooCallServiceImpl.IS_OUTBOUND_CALL));
    }
}
