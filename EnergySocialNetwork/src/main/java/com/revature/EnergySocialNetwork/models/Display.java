package com.revature.EnergySocialNetwork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "displays")
public class Display {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer displayId;
    @Column
    private Blob img;
    @Column(nullable = false)
    private Integer profileId;
    @Column
    private Boolean liked; // do we need this?
    @Column
    private Integer numOfLikes;
    @Column (nullable = false)
    private String description;

    public Display(Integer displayId, Integer numOfLikes, String description) {
        this.displayId = displayId;
        this.numOfLikes = numOfLikes;
        this.description = description;
    }
}
