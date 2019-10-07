package com.server.project.comixconnexion.requestModels;

import com.server.project.comixconnexion.entities.User;



public class ComicRequest {


    private Long id;
    private String publisher;
    private String seriesTitle;
    private Integer issue;

    public ComicRequest(){}

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
