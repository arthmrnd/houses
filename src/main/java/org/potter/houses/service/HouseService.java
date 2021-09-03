package org.potter.houses.service;

import lombok.RequiredArgsConstructor;
import org.potter.houses.harrypotterapi.HouseApiRepository;
import org.potter.houses.harrypotterapi.House;
import org.potter.houses.harrypotterapi.SortingHatRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HouseService {

    private final SortingHatRepository hatRepository;
    private final HouseApiRepository apiRepository;

    public String getHouseId() {
        return hatRepository.getHouse().getId();
    }

    public House getHouseFromApi(String houseId){
        return apiRepository.house(houseId);
    }

}
