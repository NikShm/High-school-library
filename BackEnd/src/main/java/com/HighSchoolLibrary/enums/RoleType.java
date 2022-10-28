package com.HighSchoolLibrary.enums;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project FreshBeauty
 * @class Role
 * @since 7/6/2022 - 19.47
 **/
public enum RoleType {
    NONE("NONE"),
    USER("USER"),
    ADMIN("ADMIN"),
    OPERATOR("OPERATOR");

    private final String name;

    private RoleType(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
