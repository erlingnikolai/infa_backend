package no;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Service
public class AppProperties {
    private final Auth auth = new Auth();

    public static class Auth {
        private String tokenSecret = "926D96C90030DD58429D2751AC1BDBBC";
        private long tokenExpirationMsec = 864000000;

        public String getTokenSecret() {
            return tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

    }

    public Auth getAuth() {
        return auth;
    }

}
