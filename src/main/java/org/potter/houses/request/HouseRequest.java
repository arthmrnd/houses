package org.potter.houses.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseRequest {

    private String id;
    private String houseName;
    private String animal;
    private String founder;
    private List<Value> value;

}
