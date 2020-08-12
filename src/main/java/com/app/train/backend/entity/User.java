package com.app.train.backend.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Data
public class User extends AbstractEntity {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @OneToMany(mappedBy = "idUser", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Train> trains;

}
