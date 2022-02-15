package com.WineOutBE.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasting_notes")
public class TastingNotes {

    @Id
    @Column(name = "notes_id")
    private Long notesID;

    @Column(name = "body")
    private int body;

    @Column(name = "tannins")
    private int tannins;

    @Column(name = "acidity")
    private int acidity;

    @Column(name = "sweetness")
    private int sweetness;

    @Column(name = "intensity")
    private int intensity;

    @Column(name = "finish")
    private int finish;

    public Long getNotesID() {
        return notesID;
    }

    public void setNotesID(Long notesID) {
        this.notesID = notesID;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getTannins() {
        return tannins;
    }

    public void setTannins(int tannins) {
        this.tannins = tannins;
    }

    public int getAcidity() {
        return acidity;
    }

    public void setAcidity(int acidity) {
        this.acidity = acidity;
    }

    public int getSweetness() {
        return sweetness;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }
}
