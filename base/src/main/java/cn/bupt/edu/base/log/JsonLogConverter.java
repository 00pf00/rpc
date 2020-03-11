package cn.bupt.edu.base.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.bupt.edu.base.util.LogInfo;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonLogConverter extends ClassicConverter {
    private JSONObject jobj = new JSONObject();
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {

        jobj.put(LogInfo.TIME, iLoggingEvent.getTimeStamp());
        jobj.put(LogInfo.THREAD, iLoggingEvent.getThreadName());
        //jobj.put(LogInfo.LOG_LEVEL, iLoggingEvent.getLevel().levelStr);
        //jobj.put(LogInfo.CLASS_NAME, iLoggingEvent.getLoggerName());
        try {
            JSONObject msg = JSONObject.parseObject(iLoggingEvent.getFormattedMessage());
            for (String key : msg.keySet()) {
                jobj.put(key, msg.get(key));
            }
            jobj.remove(LogInfo.MSG);
        } catch (JSONException e) {
            jobj.put(LogInfo.MSG, iLoggingEvent.getFormattedMessage());
        }
        return jobj.toJSONString();
    }
}
