package com.applepie.receiptcapture;

import android.graphics.drawable.Drawable;

public class Receipts {
 
 String numberss = null;
 String name = null;
 Drawable imageitem = null;

 public String getNumberss() {
  return numberss;
 }

 public void setNumberss(String numberss) {
  this.numberss = numberss;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public Drawable getImageitem() {
  return imageitem;
 }

 public void setImageitem(Drawable imageitem) {
  this.imageitem = imageitem;
 }

 boolean selected = false;
 
 public Receipts(Drawable image, String numbers, String name, boolean selected) {
  super();
  this.imageitem=image;
  this.numberss = numbers;
  this.name = name;
  this.selected = selected;
 }
 


 public boolean isSelected() {
  return selected;
 }
 public void setSelected(boolean selected) {
  this.selected = selected;
 }
 
}