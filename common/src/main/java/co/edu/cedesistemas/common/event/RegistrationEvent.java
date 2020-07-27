package co.edu.cedesistemas.common.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationEvent {
    private String userId;
    private String storeId;
    private Status status;

    @Override
    public String toString() {
        return toJSON();
    }

    public String toJSON() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception ex) {
            return "{}";
        }
    }

    public static RegistrationEvent fromJSON(final String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, RegistrationEvent.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public enum Status {
        CREATED,
        DELETED,
        FAILED
    }
}
