package com.example.asigment1.model;

public class MonHoc {
    private String code ;
    private  String name;
    private String teacher;

    public MonHoc(String code, String name, String teacher) {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
