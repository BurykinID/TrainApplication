package com.app.train.backend.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
    @ToString.Exclude
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "stress_id")
    @ToString.Exclude
    private LevelOfStress levelOfStress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User idUser;

    @NotNull
    private int set;

    @NotNull
    private int repeats;

    @NotNull
    private double weight;
    private int pulseStart;
    private int pulseFinish;
    private int pulseMax;
    private double timeRecreation;

    private LocalTime timeStart;
    private LocalTime timeFinish;
    private String powerReserve;

    @NotNull
    private LocalDate date;

}
