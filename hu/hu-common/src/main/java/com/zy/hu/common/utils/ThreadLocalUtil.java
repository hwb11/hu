package com.zy.intelligentdevice.common.utils;


import lombok.Getter;

import java.util.List;

public class ThreadLocalUtil {
    @Getter
    private static ThreadLocal<List<Integer>> formValueListLocal = new ThreadLocal<>();

    @Getter
    private static ThreadLocal<List<String>> scoreJsonVoLocal = new ThreadLocal<>();
}
