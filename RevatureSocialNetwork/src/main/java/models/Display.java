package models;

import lombok.*;

import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Display {
    private Integer id;
    private Blob img;
    private Integer profileId;
    private Boolean liked;
    private Integer numberOfLikes;
    private String description;

    public Display(Integer profileId) {
        this.profileId = profileId;
    }
}
