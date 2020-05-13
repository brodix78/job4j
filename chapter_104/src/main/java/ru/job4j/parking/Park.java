package ru.job4j.parking;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Park implements Parking {

    LinkedHashMap<String, Car> stPlaces;
    LinkedHashMap<String, Car> cargoPlaces;

    public Park(LinkedHashMap<String, Car> stPlaces, LinkedHashMap<String, Car> cargoPlaces) {
        this.cargoPlaces = cargoPlaces;
        this.stPlaces = stPlaces;
    }

    @Override
    public String addCar(Car car) {
        String rsl = null;
        if (car.getPlaces() > 1) {
            for (String place : cargoPlaces.keySet()) {
                if (cargoPlaces.get(place) == null) {
                    cargoPlaces.replace(place, car);
                    rsl = place;
                }
            }
        }
        if (rsl == null) {
            LinkedList<String> collect = new LinkedList<>();
            int places = car.getPlaces();
            for (String place : cargoPlaces.keySet()) {
                if (stPlaces.get(place) == null) {
                    places--;
                    collect.add(place);
                } else {
                    places = car.getPlaces();
                    collect = new LinkedList<>();
                }
                if (places == 0) {
                    for (String pl : collect) {
                        cargoPlaces.replace(pl, car);
                    }
                    rsl = collect.size() == 1 ? collect.getFirst()
                            : String.format("%s - %s", collect.getFirst(), collect.getLast());
                }
            }
        }
        return rsl;
    }

    @Override
    public Car getCar(String number) {
        Car car = null;
        for (String place : stPlaces.keySet()) {
            if (car != null && !stPlaces.get(place).getNumber().equals(number)) {
                break;
            }
            if (stPlaces.get(place).getNumber().equals(number)) {
                car = stPlaces.get(place);
                stPlaces.replace(place, null);
            }
        }
        if (car != null) {
            for (String place : cargoPlaces.keySet()) {
                if (cargoPlaces.get(place).getNumber().equals(number)) {
                    car = cargoPlaces.get(place);
                    cargoPlaces.replace(place, null);
                    break;
                }
            }
        }
        return car;
    }
}