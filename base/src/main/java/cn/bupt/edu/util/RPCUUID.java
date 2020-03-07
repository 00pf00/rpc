package cn.bupt.edu.util;

import java.util.UUID;

public class RPCUUID {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
