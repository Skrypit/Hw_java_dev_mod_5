package models;

import lombok.Data;

@Data
public class Pet {
    int id;
    String name;
    Category category;
    String photoUrl;
    Tag tag;
    PetStatus petStatus;

    public enum PetStatus {
        available, pending, sold
    }
}
