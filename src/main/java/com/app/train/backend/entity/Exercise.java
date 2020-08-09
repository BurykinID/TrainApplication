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
public class Exercise extends AbstractEntity {

    @NotNull
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "exercise", fetch = FetchType.EAGER)
    private List<Train> trains;

}

