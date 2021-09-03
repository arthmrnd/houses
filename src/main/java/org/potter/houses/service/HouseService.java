package org.potter.houses.service;

import lombok.RequiredArgsConstructor;
import org.potter.houses.entity.House;
import org.potter.houses.harrypotterapi.HouseApiRepository;
import org.potter.houses.harrypotterapi.HouseRequest;
import org.potter.houses.harrypotterapi.SortingHatRepository;
import org.potter.houses.repository.HouseRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HouseService {

    private final HouseRepository houseRepository;
    private final SortingHatRepository hatRepository;
    private final HouseApiRepository apiRepository;

    public House findById(String id) {
        var house = houseRepository.findById(id);
        return house.orElse(null);
    }

    public House getHouse() {
        return convertToHouse(getHouseFromApi());
    }

    public String getHouseId() {
        return hatRepository.getHouse().getId();
    }

    public HouseRequest getHouseFromApi(){
        return apiRepository.house(getHouseId());
    }

    public House convertToHouse(HouseRequest houseRequest) {
        var house = new House(
                houseRequest.getId(),
                houseRequest.getName(),
                houseRequest.getAnimal(),
                houseRequest.getFounder(),
                houseRequest.getValues().get(0).getName(),
                houseRequest.getValues().get(1).getName(),
                houseRequest.getValues().get(2).getName(),
                houseRequest.getValues().get(3).getName()
        );
        return saveHouse(house);
    }

    public House saveHouse(House house) {
        if (houseRepository.findById(house.getId()).isEmpty()) {
            houseRepository.save(house);
            System.out.println("saved");
        }
        else {
            System.out.println("passed");
        }
        return house;
    }
}
