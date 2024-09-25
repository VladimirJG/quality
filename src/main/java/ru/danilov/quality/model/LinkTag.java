package ru.danilov.quality.model;

import jakarta.persistence.*;

@Entity
@Table(name = "link_tag")
public class LinkTag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rel")
    private String rel;
    @Column(name = "type")
    private String type;
    @Column(name = "href")
    private String href;
    @Column(name = "sizes")
    private String sizes;


    public LinkTag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setRel(String rel) {
        this.rel = rel;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }
}
