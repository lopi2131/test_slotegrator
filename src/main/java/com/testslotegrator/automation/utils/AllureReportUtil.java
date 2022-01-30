package com.testslotegrator.automation.utils;

import io.qameta.allure.Attachment;

import java.nio.charset.StandardCharsets;

public class AllureReportUtil {

    private AllureReportUtil() {
    }

    @Attachment(value = "{descr}", type = "text/plain")
    public static byte[] attachJSONToAllure(String descr, String jsonObject) {
        return jsonObject.getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{descr}", type = "text/plain")
    public static String attachTextToAllure(String descr, String text) {
        return text;
    }


    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

}
