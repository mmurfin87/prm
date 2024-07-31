package com.yourcompany.personalcrm.fieldtypes.createinstance;

import lombok.Data;
import java.util.List;

@Data
public class CreateInstanceRequest {
    private String fieldTypeId;
    private List<FieldValue> fieldValues;

    @Data
    public static class FieldValue {
        String fieldId;
        String value;
    }
}