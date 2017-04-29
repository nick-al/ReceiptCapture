package com.applepie.receiptcapture;

public class Product {
    String name;
      String number;
      int image;
      boolean box;
      

      Product(String _describe, String _price, int _image, boolean _box) {
        name = _describe;
        number = _price;
        image = _image;
        box = _box;
      }
    }