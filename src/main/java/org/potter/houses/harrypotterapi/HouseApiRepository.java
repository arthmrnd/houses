package org.potter.houses.harrypotterapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "house", url = "https://api-harrypotter.herokuapp.com/house")
public interface HouseApiRepository {

    @GetMapping("/{id}")
    House house(@PathVariable String id);
}
