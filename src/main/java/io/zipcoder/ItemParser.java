package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    public List<Item> parseItemList(String valueToParse) {
        List<Item> arr =new ArrayList<>();
        ArrayList<String> jerksonArr = new ArrayList<>();
//        String result;
        //valueToParse.replaceAll("[@|^|%]", ":");

        Matcher matcher = Pattern.compile("[@|^|%]")
                .matcher(valueToParse);
        while (matcher.find()) {
            matcher.replaceAll(":");
        }


            Matcher m = Pattern.compile("(.*?)##")
                    .matcher(valueToParse);
            while (m.find()) {
                jerksonArr.add(m.group());
            }


            for (String i : jerksonArr){
                try {
                    arr.add(parseSingleItem(i));
                } catch (ItemParseException e) {
                    e.printStackTrace();
                }
//
            }
            return  arr;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
                String name = "test";
                String price = "0.0";
                String type = "testing";
                String expiration = "stuff";

                List<String> itemDescipList = new ArrayList<>();



            Matcher m = Pattern.compile("(.*?);|(.*?)##")
                .matcher(singleItem);
            while (m.find()) {
                itemDescipList.add(m.group(1));
            }

            name = parseName(itemDescipList.get(0));
            price = parseprice(itemDescipList.get(1));
            type =parsetype(itemDescipList.get(2));
            expiration = parseExperation(itemDescipList.get(3));

            return new Item(name, Double.valueOf(price),type, expiration);

    }


    public String parseName(String nameString) {
        Matcher matcher = Pattern.compile(":(.*?)")
                .matcher(nameString);
        return matcher.group(1);
    }

    public String parseprice(String priceString) {
        Matcher matcher = Pattern.compile(":(.*?)")
                .matcher(priceString);
        return matcher.group(1);
    }

    public String parsetype(String typeString) {
        Matcher matcher = Pattern.compile(":(.*)")
                .matcher(typeString);
        return matcher.group(1);
    }

    public String parseExperation(String experationString) {
        Matcher matcher = Pattern.compile(":(.*)")
                .matcher(experationString);
        return matcher.group(1);
    }




}
