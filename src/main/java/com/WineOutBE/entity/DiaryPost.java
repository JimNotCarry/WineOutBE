package com.WineOutBE.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diary_post", schema = "dbo")
public class DiaryPost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diary_id")
    private Long id;

    @Column(name = "winename")
    private String winename;

    @Column(name = "vintage")
    private String vintage;

    @Column(name = "producer")
    private String producer;

    @Column(name = "percentage")
    private String percentage;

    @Column(name = "region")
    private String region;

    @Column(name = "district")
    private String district;

    @Column(name = "grape")
    private String grape;

    @Column(name = "occasion_date")
    private LocalDate occasionDate;

    @Column(name = "post_date")
    private ZonedDateTime postDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notes_id", referencedColumnName = "notes_id")
    private TastingNotes tastingNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWinename() {
        return winename;
    }

    public void setWinename(String winename) {
        this.winename = winename;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGrape() {
        return grape;
    }

    public void setGrape(String grape) {
        this.grape = grape;
    }

    public LocalDate getOccasionDate() {
        return occasionDate;
    }

    public void setOccasionDate(LocalDate occasionDate) {
        this.occasionDate = occasionDate;
    }

    public ZonedDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(ZonedDateTime postDate) {
        this.postDate = postDate;
    }

    public TastingNotes getTastingNotes() {
        return tastingNotes;
    }

    public void setTastingNotes(TastingNotes tastingNotes) {
        this.tastingNotes = tastingNotes;
    }
}
