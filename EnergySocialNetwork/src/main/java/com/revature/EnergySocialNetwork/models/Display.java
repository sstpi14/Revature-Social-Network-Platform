package com.revature.EnergySocialNetwork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "displays")

public class Display {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer displayId;
    @ElementCollection
    List<String> img = new ArrayList<String>();
    @ManyToOne
    private Profile profile;
    @ManyToMany
    @JoinTable(
            name = "Likes",
            joinColumns = @JoinColumn(name = "display_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<Profile> likers =new ArrayList<>();
    @Column
    private String description;

    /**
     *
     * @param displayId Primary key for display incrementing with every new instance
     * @param numOfLikes variable representing a number of likes on a post
     * @param description variable representing any text added to a picture or normal post
     */
    public Display(Integer displayId, Integer numOfLikes, String description) {
        this.displayId = displayId;
        this.description = description;

    }

    public Display(Profile profile, String description){
        this.profile = profile;
        this.description = description;
    }


}
