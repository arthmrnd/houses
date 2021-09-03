package org.potter.houses.harrypotterapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class House {

    private String id;
    private String name;
    private String animal;
    private String founder;
    private List<Value> values;

}
