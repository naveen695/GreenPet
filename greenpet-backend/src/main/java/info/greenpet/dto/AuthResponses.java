package info.greenpet.dto;

public class AuthResponses {
    public static class AuthResponse {
        private String token;
        private String tokenType = "Bearer";

        public AuthResponse(String token) {
            this.token = token;
        }
        // getters/setters
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
        public String getTokenType() { return tokenType; }
        public void setTokenType(String tokenType) { this.tokenType = tokenType; }
    }
}
