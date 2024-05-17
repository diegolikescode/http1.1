package com.http1dot1.app.core.response;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * SimpleParser
 */
public class SimpleParser {

    /*
     * TODO: PARSER SUPPORT FOR
     * - [x] String
     * - [x] Number
     * - [x] Boolean
     * - [x] Nested JSON
     * - [x] Array
     */
    public static <T> String serializeToJson(T object) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        json.append("{");
        int length = 0;
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            if(!f.canAccess(object)) {
                f.setAccessible(true);
            }
            String name = f.getName();
            Object fieldValue;

            try {
                fieldValue = f.get(object);
            } catch (Exception e) {
                fieldValue = null;
            }

            json.append("\"" +name+ "\"" +": ");

            if (fieldValue != null) {
                if (isComplexObject(fieldValue)) {
                    json.append(serializeToJson(fieldValue));
                } else if (fieldValue instanceof String) {
                    json.append("\"" + fieldValue + "\"");
                } else if (fieldValue instanceof Collection) {
                    json.append("[");
                    Collection<?> col = (Collection<?>) fieldValue;
                    int colIndex = 0;
                    for (Object i : col) {
                        if (colIndex > 0) {
                            json.append(",");
                        }

                        json.append(serializeToJson(i));
                        colIndex++;
                    }
                    json.append("]");
                } else {
                    json.append(fieldValue);
                }

            } else {
                json.append("null");
            }

            if (length < fields.length-1) {
                json.append(",");
            }
        }
        json.append("}");
        return json.toString();
    }

    private static boolean isComplexObject(Object obj) {
        return !(obj instanceof String) &&
            !(obj instanceof Number) &&
            !(obj instanceof Boolean) &&
            !(obj.getClass().isArray()) &&
            !(obj instanceof Collection<?>);
    }
}
