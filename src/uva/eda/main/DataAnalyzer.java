package uva.eda.main;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataAnalyzer {
    private final ArrayList<Persona> data;
    private final Results results;
    private String userName;
    private int showNames;

    public DataAnalyzer(ArrayList<Persona> data) {
        this.data = data;
        results = new Results();
    }

    public HashMap<String, Integer> sortByDate(String startDate, String stopDate) {
        int start = dateConverter(startDate);
        int stop = dateConverter(stopDate);
        results.setIntervalDays(stop - start);
        HashMap<String, Integer> interval = new HashMap<>();
        AtomicInteger numPeople = new AtomicInteger();
        AtomicInteger difNames = new AtomicInteger();
        data.forEach(e -> {
            if (e.getBirthDay() >= start && e.getBirthDay() <= stop) {
                numPeople.getAndIncrement();
                String name = e.getName();
                if (!interval.containsKey(name)) {
                    difNames.getAndIncrement();
                }
                interval.computeIfAbsent(name, k -> 0);
                interval.computeIfPresent(name, (k, val) -> val + 1);
            }
        });
        results.setIntervalPeople(numPeople.intValue());
        results.setDifferentNames(difNames.intValue());
        return interval;
    }

    public HashMap<Integer, String> getTopNames(HashMap<String, Integer> interval) {
        HashMap<Integer, String> topNames = new LinkedHashMap<>();
        interval.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(showNames-1)
                .forEach(e -> topNames.put(e.getValue(), e.getKey()));
        return topNames;
    }

    public int[] findUsername(HashMap<String, Integer> sortedNames) {
        AtomicInteger val = new AtomicInteger();
        sortedNames.forEach((key, value) -> {
            if (key.equals(userName)) {
                val.set(value);
            }
        });
        AtomicInteger index = new AtomicInteger();
        sortedNames.forEach((key, value) -> {
            if (value > val.intValue()) {
                index.getAndIncrement();
            }
        });
        int[] nums = new int[2];
        nums[0] = index.intValue();
        nums[1] = val.intValue();
        return nums;
    }

    public int dateConverter(String date) {
        String[] split = date.split("/");
        int days = Integer.parseInt(split[0]);
        int months = Integer.parseInt(split[1]);
        int years = Integer.parseInt(split[2]);
        return 367*years - (7*(years+5001+(months-9)/7))/4 + (275*months)/9 + days - 692561;
    }

    public Results getResults() {

        return results;
    }

    public void setShowNames(int showNames) {
        this.showNames = showNames;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
