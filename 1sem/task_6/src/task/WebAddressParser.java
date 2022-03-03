package task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebAddressParser {

    private URL address;

    public WebAddressParser(String address){
        try {
            this.address = new URL(address);
        } catch (MalformedURLException e) {
            this.address = null;
        }
    }

    public String getLogin(){
        if(address != null) {
            String[] authority = address.getAuthority().split("@");
            if(authority.length >= 2) {
                String[] userInfo = authority[0].split(":");
                if (userInfo.length >= 2) {
                    return userInfo[0];
                }
            }
        }
        return "";
    }

    public String getPassword(){
        if(address != null) {
            String[] authority = address.getAuthority().split("@");
            if(authority.length >= 2) {
                String[] userInfo = authority[0].split(":");
                if (userInfo.length >= 2) {
                    return userInfo[1];
                }
            }
        }
        return "";
    }

    public String getScheme(){
        if(address != null) {
            return address.getProtocol();
        }
        return "";
    }

    public boolean isValid(){
        if(address != null) {
            return true;
        }
        return false;
    }

    public String getHost(){
        if(address != null) {
            return address.getHost();
        }
        return "";
    }

    public String getPort(){
        if(address != null && address.getPort() != -1) {
            return Integer.toString(address.getPort());
        }
        return "";
    }

    public String getUrl(){
        if(address != null) {
            return address.getPath().replaceFirst("/", "");
        }
        return "";
    }

    public HashMap<String, String> getParameters(){
        if(address != null && address.getQuery() != null) {
            String[] pair = address.getQuery().split("&");
            HashMap<String, String> parameters = new HashMap<>();

            String b[];
            for (int i = 0; i < pair.length; i++) {
                b = pair[i].split("=");
                parameters.put(b[0], b[1]);
            }

            return parameters;
        }
        return new HashMap<>();
    }

    public String getFragment(){
        if(address != null && address.getRef() != null) {
            return address.getRef();
        }
        return "";
    }
}
