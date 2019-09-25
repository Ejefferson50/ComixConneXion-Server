package com.server.project.comixconnexion.requestModels;

import com.server.project.comixconnexion.entities.User;



public class ComicRequest {


    private Long id;
    private String publisher;
    private String title;
    private Integer seriesNum;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(Integer seriesNum) {
        this.seriesNum = seriesNum;
    }


}
