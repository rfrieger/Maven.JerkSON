package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    Pattern pattern;
    Matcher matcher;


    public List<Item> parseItemList(String valueToParse) {
        List<Item> arr =new ArrayList<>();
        ArrayList<String> jerksonArr = new ArrayList<>();

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
                String name = "";
                String price = "";
                String type = "";
                String expiration = "";

                List<String> itemDescipList = new ArrayList<>();

            String fixedString = fixInput(singleItem);

            Matcher m = Pattern.compile(":(.*?;)")
                .matcher(fixedString);
            while (m.find()) {
                itemDescipList.add(m.group(1));
            }


            Matcher m2 = Pattern.compile("expiration:(.*?##)")
                    .matcher(fixedString);
            while (m2.find()) {
                itemDescipList.add(m2.group(1));
            }

          try {
              name = parse(itemDescipList.get(0).toLowerCase());
              price = parse(itemDescipList.get(1));
              type = parse(itemDescipList.get(2).toLowerCase());
              expiration = parseExperation(itemDescipList.get(3));
          } catch (IndexOutOfBoundsException e) {
              throw new ItemParseException();
          }

            return new Item(name, Double.valueOf(price),type.toLowerCase(), expiration);

    }


    public String parse(String nameString) throws ItemParseException {
        Pattern pattern = Pattern.compile("(.*?);");
         Matcher matcher = pattern.matcher(nameString);
         String result;
        while (matcher.find()) {
            result = matcher.group(1);
            return result;
        }return null;
    }


    public String parseExperation(String experationString) {
        Pattern pattern = Pattern.compile("(\\d{1,2}/\\d{2}/\\d{4})##");
        Matcher matcher = pattern.matcher(experationString);
        while (matcher.find()) {
           String result = matcher.group(1);
            System.out.println(result);
            return result;
        }return null;
    }


    private String fixInput(String toFix){

        pattern = pattern.compile("@|\\^|\\*|%|!");
        matcher = pattern.matcher(toFix);
        String result = matcher.replaceAll(":");

        pattern = pattern.compile("Food(.*)expiration");
        matcher = pattern.matcher(result);
        String ultimateFix = matcher.replaceAll("Food;expiration");
        return ultimateFix;
    }




}
