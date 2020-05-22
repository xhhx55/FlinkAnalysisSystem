package com.jw.app.sink;

import com.jw.entity.FlowMinuteInfo;
import com.jw.utils.ClickHouseUtil;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.HashMap;
import java.util.Map;

public class FlowMinuteSink implements SinkFunction<FlowMinuteInfo> {

    @Override
    public void invoke(FlowMinuteInfo in, Context context) throws Exception {
        String timeInfo = in.getTimeInfo();
        String deviceType = in.getDeviceType();
        Long times = in.getTimes();
        Long newUserNum = in.getNewUserNum();

        Long hourActiveNums = in.getHourActiveNums();
        Long dayActiveNums = in.getDayActiveNums();
        Long weekActiveNums = in.getWeekActiveNums();
        Long monthActiveNums = in.getMonthActiveNums();


        Map<String, String> map = new HashMap<>();
        map.put("timeInfo", timeInfo);
        map.put("deviceType", deviceType);
        map.put("times", String.valueOf(times));
        map.put("newUserNum", String.valueOf(newUserNum));

        map.put("hourActiveNums", hourActiveNums + "");
        map.put("dayActiveNums", dayActiveNums + "");
        map.put("weekActiveNums", weekActiveNums + "");
        map.put("monthActiveNums", monthActiveNums + "");

        ClickHouseUtil.insert("FlowMinuteInfo", map);
    }
}