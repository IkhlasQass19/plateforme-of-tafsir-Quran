package com.example.demo.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="tafassir")
@Getter
@Setter
public class Tafssir {
    @Id
    private int NTafsir;
    @Column
    private String Nom;
    @Column
    private int An;
    @Column
    private String Nom_S;
    @Column
    private int Approuve;

    @Column
    private String Description;

}
