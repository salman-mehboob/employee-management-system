package com.econception.employemanagement.service;


import com.econception.employemanagement.domain.LeavesCategories;
import com.econception.employemanagement.dto.LeavesCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BillsStatsService {

        public Map<String, Map<String, Integer>> initialBillsMap(){
            Map<String, Map<String, Integer>> ret = new HashMap<>();

            LocalDateTime dt = LocalDateTime.now().minusYears(1);
            LocalDateTime ey = LocalDateTime.now();

            while(dt.isBefore(ey)){
                String monthName = dt.format(DateTimeFormatter.ofPattern("MMM"));

                Map<String, Integer> subMap = new HashMap<>();
                subMap.put("Approved", 0);
                subMap.put("Rejected", 0);
                subMap.put("Pending", 0);
                ret.put(monthName, subMap);
                dt = dt.plusMonths(1);
            }
            return ret;
        }

    public Map<String, Map<String, Integer>> initialLeaveRMap(List<LeavesCategories> leavesCategories){
        Map<String, Map<String, Integer>> ret = new HashMap<>();


        for(var a : leavesCategories){
            Map<String, Integer> subMap = new HashMap<>();
            subMap.put("Approved", 0);
            subMap.put("Rejected", 0);
            subMap.put("Pending", 0);
            ret.put(a.getLeaveType(), subMap);

        }
        log.info("test map" + ret);
        return ret;
    }

    public String createBillsLabels(){
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
       // log.info(ret);
        return ret;
    }


    public String createBillJson(Map<String, Map<String, Integer>> map){

        LocalDateTime dt = LocalDateTime.now().minusYears(1);
        LocalDateTime ey = LocalDateTime.now();

        String pending = "[";
        String approved = "[";
        String rejected = "[";
        List<String> months = new ArrayList<>();
        while(dt.isBefore(ey)) {
            String monthName = dt.format(DateTimeFormatter.ofPattern("MMM"));

            if(months.contains(monthName)) {
                dt = dt.plusMonths(1);
                continue;
            }
            months.add(monthName);
            Map<String, Integer> subMap    = map.get(monthName);

            approved += subMap.get("Approved") + ",";
            rejected += subMap.get("Rejected") + ",";
            pending += subMap.get("Pending") + ",";
            dt = dt.plusMonths(1);
        }

        approved += "]";
        rejected += "]";
        pending += "]";

        String ret = "[\n" +
                "{\n" +
                "name: 'Approved',\n" +
                "type: 'area',\n" +
                "data: " + approved + "\n" +
                "}," +
                "{\n" +
                "name: 'Rejected',\n" +
                "type: 'area',\n" +
                "data: " + rejected + "\n" +
                "}," +
                "{\n" +
                "name: 'Pending',\n" +
                "type: 'area',\n" +
                "data: " + pending + "\n" +
                "}," +
                "]";
        return ret;
        }

}
