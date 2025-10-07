package com.example.StudentScoreApi.util;

import java.util.*;
import java.util.stream.Collectors;

public final class StatisticsUtil {
    private StatisticsUtil() {}

    public static double mean(Collection<Integer> values) {
        return values.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    public static double median(Collection<Integer> values) {
        List<Integer> sorted = values.stream().sorted().collect(Collectors.toList());
        int n = sorted.size();
        if (n == 0) return 0;
        if (n % 2 == 1) return sorted.get(n/2);
        return (sorted.get(n/2 -1) + sorted.get(n/2)) / 2.0;
    }

    public static Integer mode(Collection<Integer> values) {
        if (values.isEmpty()) return null;
        Map<Integer, Integer> freq = new HashMap<>();
        for (Integer v : values) freq.put(v, freq.getOrDefault(v, 0) + 1);
        int max = Collections.max(freq.values());
        if (max == 1) return null;
        // return one mode (smallest value among ties)
        return freq.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .min(Integer::compareTo)
                .orElse(null);
    }
}
