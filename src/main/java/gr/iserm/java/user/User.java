package gr.iserm.java.user;

import gr.iserm.java.user.processing.UserEnrichment;
import gr.iserm.java.user.processing.UserValidity;

import java.util.Map;
import java.util.TreeMap;

public class User {

    private String uuid;
    private String name, email;
    private UserValidity validity;

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserValidity getValidity() {
        return validity;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> properties = new TreeMap<>();
        properties.put("uuid", uuid);
        properties.put("name", name);
        properties.put("email", email);
        properties.put("valid", validity.isValid());
        return properties;
    }

    public static class Builder {
        private User user;
        public static Builder create() {
            return new Builder();
        }

        private Builder() {
            user = new User();
        }

        public Builder setUuid(String uuid) {
            user.uuid = uuid;
            return this;
        }

        public Builder setName(String name) {
            user.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder setValidity(UserValidity userValidity) {
            user.validity = userValidity;
            return this;
        }

        public Builder setEnhancement(UserEnrichment userEnrichment) {
            user.uuid = userEnrichment.getUuid();
            return this;
        }

        public Builder copyUser(User _user) {
            user.uuid = _user.uuid;
            user.name = _user.name;
            user.email = _user.email;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
