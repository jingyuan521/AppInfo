package com.example.app.entity.DTO;

public class AppinfoDto<T> {
    private String succsess;
    private String fail;
    private String message;
    private T data;

    public String getSuccsess() {
        return succsess;
    }

    public void setSuccsess(String succsess) {
        this.succsess = succsess;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
