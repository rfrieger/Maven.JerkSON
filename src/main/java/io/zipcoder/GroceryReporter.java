package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import java.util.List;
import java.util.Map;

public class GroceryReporter {
    private final String originalFileText;
    private ItemParser itemParser = new ItemParser();
    private List<Item> itemsList;
    Map<String, List<Double>> priceMap;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }




    @Override
    public String toString() {
        itemsList = itemParser.parseItemList(originalFileText);

//        for (Item item : itemsList) {
//            if(priceMap.containsKey(item))
//
//        }

        return null;
    }
}
