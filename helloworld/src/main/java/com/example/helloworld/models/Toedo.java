package com.example.helloworld.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
//for getter setter
@Data
//boilerplate automatically set these
//for constructor with all items
@AllArgsConstructor
//for constructor with no items
@NoArgsConstructor
public class Toedo {
    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @NotBlank
    String title;
//    @NotNull
//    @NotBlank
//    @Schema(name = "description",example="complete spring")
//    //to follow or enforce some conditions
////            @Size(min=5,max=15)
//            //some size
//    String description;
//    //for mail
//    @Email
//    String email;
    Boolean isCompleted;

}
