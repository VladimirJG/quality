package ru.danilov.quality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkTagDto {
    @JsonProperty("rel")
    private String rel;

    @JsonProperty("type")
    private String type;

    @JsonProperty("href")
    private String href;

    @JsonProperty("sizes")
    private String sizes;

    public LinkTagDto() {
    }

    public LinkTagDto(String rel, String type, String href, String sizes) {
        this.rel = rel;
        this.type = type;
        this.href = href;
        this.sizes = sizes;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }
}
