package models;

import com.google.gson.annotations.SerializedName;
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
        @SerializedName("available")
        AVAILABLE,
        @SerializedName("pending")
        PENDING,
        @SerializedName("sold")
        SOLD
    }
}
