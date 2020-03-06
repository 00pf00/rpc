package cn.bupt.edu.protocol;

public class ProtocolReqMsg {
    private String version;

    public ProtocolReqMsg() {
        this.version = version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public byte[] Encode() {
        return new byte[]{};
    }
}
