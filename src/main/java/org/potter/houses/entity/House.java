package org.potter.houses.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "house")
public class House {

    @Id
    @Column(name = "id_house")
    private String id;
    @Column(name = "house_name")
    private String houseName;
    private String animal;
    private String founder;
    private String value1;
    private String value3;
    private String value2;
    private String value4;

}
