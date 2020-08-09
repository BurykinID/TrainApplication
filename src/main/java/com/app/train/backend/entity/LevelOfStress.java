package com.app.train.backend.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class LevelOfStress extends AbstractEntity {

    @NotNull
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "levelOfStress", fetch = FetchType.EAGER)
    List<Train> trains = new LinkedList<>();

}
