package com.nextyu.jenkins;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<Long>();
        list.add(12L);
        list.add(13L);

//        list.stream().toArray(Integer[]::new);

        list.toArray(new Long[list.size()]);

        Long[] longs = ArrayUtil.toArray(list, Long.class);

        Integer[] integers = list.stream().map(Long::intValue).toArray(Integer[]::new);
        Console.log(integers);

    }

    @Test
    public void test11() {

        String key1 = "key1";
        String key2 = "key2";

        Map<String, List<String>> map = new HashMap<>();

        map.computeIfAbsent(key1, k -> new ArrayList<>()).add("A1");
        map.computeIfAbsent(key1, k -> new ArrayList<>()).add("B1");


        map.computeIfAbsent(key2, k -> new ArrayList<>()).add("A2");
        map.computeIfAbsent(key2, k -> new ArrayList<>()).add("B2");

        Console.log(map);

    }
}
