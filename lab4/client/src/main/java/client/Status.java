package client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status {
    private Integer code;

    public Status() {
    }

    public Status(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "code=" + code;
    }
}