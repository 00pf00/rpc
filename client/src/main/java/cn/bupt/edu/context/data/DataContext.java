package cn.bupt.edu.context.data;

import cn.bupt.edu.context.ClientContext;

public class DataContext implements ClientContext {
    private static DataContext ctx;
    @Override
    public void ResetModule(String service) {

    }
    public static DataContext getInstance(){
        return ctx;
    }

}
