package com.econception.employemanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatsService {

    public Map<String, Map<String, Integer>> initialTasksMap(){
        Map<String, Map<String, Integer>> ret = new HashMap<>();

        LocalDateTime dt = LocalDateTime.now().minusYears(1);
        LocalDateTime ey = LocalDateTime.now();

        while(dt.isBefore(ey)){
            String monthName = dt.format(DateTimeFormatter.ofPattern("MMM"));

            Map<String, Integer> subMap = new HashMap<>();
            subMap.put("Pending", 0);
            subMap.put("Done", 0);
            subMap.put("Total", 0);
            ret.put(monthName, subMap);
            dt = dt.plusMonths(1);
        }
        return ret;
    }

    public String createTaskLabels(){
        LocalDateTime dt = LocalDateTime.now().minusYears(1);
        LocalDateTime ey = LocalDateTime.now();
        List<String> labels = new ArrayList<>();
        //String labels = "[";
        while(dt.isBefore(ey)){
            //labels += "'" + dt.format(DateTimeFormatter.ofPattern("MMM")) + "',";
            String monthName = dt.format(DateTimeFormatter.ofPattern("MMM"));
            if(!labels.contains(monthName)){
                labels.add(monthName);
            }
            dt = dt.plusMonths(1);
        }
        String ret = "[" + labels.stream()
               .map(s -> "'" + s + "'")
                .collect(Collectors.joining(",")) + "]";
        //labels += "]";
        log.info(ret);
        return ret;
    }

    public String createTaskJson(Map<String, Map<String, Integer>> map){

        LocalDateTime dt = LocalDateTime.now().minusYears(1);
        LocalDateTime ey = LocalDateTime.now();

        String assigned = "[";
        String pending = "[";
        String completed = "[";
        List<String> months = new ArrayList<>();
        while(dt.isBefore(ey)) {
            String               monthName = dt.format(DateTimeFormatter.ofPattern("MMM"));

            if(months.contains(monthName)) {
                dt = dt.plusMonths(1);
                continue;
            }
            months.add(monthName);
            Map<String, Integer> subMap    = map.get(monthName);

            assigned += subMap.get("Total") + ",";
            pending += subMap.get("Pending") + ",";
            completed += subMap.get("Done") + ",";
            dt = dt.plusMonths(1);
        }
        assigned += "]";
        pending += "]";
        completed += "]";

        String ret = "[\n" +
                "{\n" +
                "name: 'Assigned',\n" +
                "type: 'column',\n" +
                "data: " + assigned + "\n" +
                "}," +
                "{\n" +
                "name: 'Pending',\n" +
                "type: 'column',\n" +
                "data: " + pending + "\n" +
                "}," +
                "{\n" +
                "name: 'Complete',\n" +
                "type: 'column',\n" +
                "data: " + completed + "\n" +
                "}," +
                "]";
        return ret;

        /*
        series: [
        {
            name: 'Assigned',
            type: 'column',
            data: [440, 505, 414, 571, 227, 413, 201, 352, 652, 320, 257, 160]
        },
        {
            name: 'Pending',
            type: 'column',
            data: [200, 300, 259, 327, 129, 413, 201, 352, 652, 320, 129, 150]
        },
        {
            name: 'Complete',
            type: 'column',
            data: [190, 500, 159, 347, 159, 463, 271, 382, 692, 109, 117, 120]
        },
    ],
    stroke: {
        width: [0, 4, 4]
    },
    labels: ['01 Jan 2001', '02 Jan 2001', '03 Jan 2001', '04 Jan 2001', '05 Jan 2001', '06 Jan 2001', '07 Jan 2001', '08 Jan 2001', '09 Jan 2001', '10 Jan 2001', '11 Jan 2001', '12 Jan 2001'],



         */
    }
}
