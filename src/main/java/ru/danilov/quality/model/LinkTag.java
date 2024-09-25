package ru.danilov.quality.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;



public class LinkTag {
    private String rel;
    private String type;
    private String href;
    private String sizes;

    public LinkTag() {
    }

    public LinkTag(String rel, String type, String href, String sizes) {
        this.rel = rel;
        this.type = type;
        this.href = href;
        this.sizes = sizes;
    }

    public String getRel() {
        return rel;
    }

    public String getType() {
        return type;
    }

    public String getHref() {
        return href;
    }

    public String getSizes() {
        return sizes;
    }
}
