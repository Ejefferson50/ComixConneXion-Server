package com.server.project.comixconnexion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comic_books")
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "publishing_company")
    private String publisher;

    @NotNull
    @Column(name = "series_title")
    private String seriesTitle;

    @NotNull
    @Column(name = "series_number")
    private Integer issue;


    public Comic(){  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }


}
