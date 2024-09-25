package ru.danilov.quality.model;

public class MetaTag {
    private final String name;
    private final String content;

    public MetaTag(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
