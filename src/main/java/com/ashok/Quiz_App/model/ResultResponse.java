package com.ashok.Quiz_App.model;

public class ResultResponse {
    private Integer id;
    private String response;

    public ResultResponse(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
