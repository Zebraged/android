package com.example.android_project_weatherapp.Api;

public class UrlGenerator {
    //    handles url generation
    private String[] searchType = {"weather?q=", "weather?id=", "weather?", "group?id="};
    private String base = "https://api.openweathermap.org/data/2.5/";
    private String key = "&appid=0396235ccaab78ce750e30162513707d";
    public String createLocUrl(double lat, double lon) {
        return createUrl("lat=" + lat + "&lon=" + lon, 2);
    }
    public String createBulkUrl(String ids) {
        return createUrl(ids, 3);
    }
    public String createUrl(String dest, int searchtype) {
        String destination = removeWhiteSpace(dest);
        return "" + base + searchType[searchtype] + destination + key;
    }
    public String intArrtoString(int[] ids) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            sb.append(ids[i] + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        String s = sb.toString();
        return s;
    }

    private String removeWhiteSpace(String s) {
        return s.replaceAll("\\s", "");
    }
}
