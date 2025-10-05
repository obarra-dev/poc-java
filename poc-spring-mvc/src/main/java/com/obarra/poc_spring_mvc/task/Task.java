package com.obarra.poc_spring_mvc.task;

import java.util.List;

public record Task(String title, List<String> owners) {

    public String getOwnersAsString() {
        return String.join(",", owners);
    }

}
