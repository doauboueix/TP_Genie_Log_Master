package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateBrie(Item item){
        item.sellIn --;
        if(item.quality <= 50) item.quality ++;
    }


    public void updateBackstage(Item item){
        item.sellIn --;
        if(item.quality <= 50){
            item.quality ++;
            if(item.sellIn <= 10){
                if(item.sellIn <= 5){
                    item.quality ++;
                }
                item.quality++;
            }
        }
    }

    public void updateDefault(Item item){
        item.sellIn --;
        if(item.sellIn <= 0){
            if(item.quality >= 2){
                item.quality -= 2;
            }
            else item.quality = 0;
        }
    }


    public void updateQuality(){

        for (Item item : items) {
            switch(item.name){
                case "Sulfuras, Hand of Ragnaros":
                    break;
                case "Aged Brie":
                    updateBrie(item);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    updateBackstage(item);
                    break;
                default:
                    updateDefault(item);
                    break;
            }
        }
    }
    /*
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            if (!items[i].name.equals("Aged Brie") && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        if(items[i].name.equals("Conjured")){
                            if (items[i].quality >= 2) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }*/
}