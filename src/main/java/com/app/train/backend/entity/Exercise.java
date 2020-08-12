package com.app.train.backend.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
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
    @ToString.Exclude
    private List<Train> trains;

}

