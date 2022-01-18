package com.WineOutBE.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DiarySettings")
public class DiarySettings {

    @Id
    @Column(name = "settingID")
    private int id;

    @Column(name = "rating")
    private boolean rating;

    @Column(name = "tannins")
    private boolean tannins;

    @Column(name = "acidity")
    private boolean acidity;

    @Column(name = "body")
    private boolean body;

    @Column(name = "comments")
    private boolean comments;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    public boolean isTannins() {
        return tannins;
    }

    public void setTannins(boolean tannins) {
        this.tannins = tannins;
    }

    public boolean isAcidity() {
        return acidity;
    }

    public void setAcidity(boolean acidity) {
        this.acidity = acidity;
    }

    public boolean isBody() {
        return body;
    }

    public void setBody(boolean body) {
        this.body = body;
    }

    public boolean isComments() {
        return comments;
    }

    public void setComments(boolean comments) {
        this.comments = comments;
    }
}
