package cn.bupt.edu.context.data;

import cn.bupt.edu.context.ClientContext;

public class DataContext implements ClientContext {
    private static DataContext ctx;

    public static DataContext getInstance() {
        return ctx;
    }

    @Override
    public void ResetModule(String service) {

    }

}
