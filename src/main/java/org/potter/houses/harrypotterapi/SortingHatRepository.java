package org.potter.houses.harrypotterapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sortinghat", url = "https://api-harrypotter.herokuapp.com/sortinghat")
public interface SortingHatRepository {

    @GetMapping()
    HouseId getHouse();


}
