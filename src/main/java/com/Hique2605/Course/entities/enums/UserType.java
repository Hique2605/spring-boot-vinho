package com.Hique2605.Course.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
        USER,
        ADMIN,
        REPRESENTANTE;

        @JsonCreator
        public static UserType fromString(String key) {
                return key == null ? null : UserType.valueOf(key.toUpperCase());
        }
}