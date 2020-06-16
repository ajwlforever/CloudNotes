package com.ajwlforever.notes.utils;

import java.io.Serializable;
import java.util.List;

public class NoteResult<T>  implements Serializable {

    private int status;
    private  String msg;
    private  T  data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
