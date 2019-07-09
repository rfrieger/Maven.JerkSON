package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.*;

public class GroceryReporter {
    private final String originalFileText;
    private ItemParser itemParser = new ItemParser();
    private List<Item> itemsList;
    Map<Double, Integer> milkPrice = new HashMap<>();
    Map<Double, Integer> breadPrice = new HashMap<>();
    Map<Double, Integer> cookiePrice = new HashMap<>();
    Map<Double, Integer> applePrice = new HashMap<>();




    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }

    public void mapPrices() {
        itemsList = itemParser.parseItemList(originalFileText);

        for (Item item : itemsList) {
            switch (item.getName()) {
                case "milk": {
                    if (milkPrice.containsKey(item.getPrice())){
                        milkPrice.replace(item.getPrice(), milkPrice.get(item.getPrice()) +1);
                    } else {
                        milkPrice.put(item.getPrice(), 1);
                    }
                }
                case "bread": {
                    if (breadPrice.containsKey(item.getPrice())){
                        breadPrice.replace(item.getPrice(), breadPrice.get(item.getPrice()) +1);
                    } else {
                        breadPrice.put(item.getPrice(), 1);
                    }
                }
                case "cookie": {
                    if (cookiePrice.containsKey(item.getPrice())){
                        cookiePrice.replace(item.getPrice(), cookiePrice.get(item.getPrice()) +1);
                    } else {
                        cookiePrice.put(item.getPrice(), 1);
                    }
                }
                case "apple": {
                    if (applePrice.containsKey(item.getPrice())){
                        applePrice.replace(item.getPrice(), applePrice.get(item.getPrice()) +1);
                    } else {
                        applePrice.put(item.getPrice(), 1);
                    }

                }
            }
        }
    }

//    public String getPrices(Map map){
//        StringBuilder sb = new StringBuilder();
//        sb.append("name:" +   );
//        Set<Double> set = map.keySet();
//
//        for (Double key : set){
//
//        }
//
//     }

    @Override
    public String toString() {
        mapPrices();

        return null;
    }
}
