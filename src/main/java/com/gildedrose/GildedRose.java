package com.gildedrose;

class GildedRose {
    static final String AGED_BRIE = "Aged Brie";
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    static final String CONJURED = "Conjured";

    static final int QUALITY_MAX_VALUE = 50;
    static final int QUALITY_MIN_VALUE = 0;
    static final int BACKSTAGE_SELLIN_CONDITION_1 = 10;
    static final int BACKSTAGE_SELLIN_CONDITION_2 = 5;
    static final int SELLIN_MIN_VALUE = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private void updateBrie(Item item){
        increaseQualityIfNotMax(item);
        if(item.sellIn <= SELLIN_MIN_VALUE)
            increaseQualityIfNotMax(item);
    }

    private void updateBackstage(Item item) {
        increaseQualityIfNotMax(item);
        if (item.sellIn <= BACKSTAGE_SELLIN_CONDITION_1) {
            increaseQualityIfNotMax(item);
            if (item.sellIn <= BACKSTAGE_SELLIN_CONDITION_2)
                increaseQualityIfNotMax(item);
        }
        if (item.sellIn <= SELLIN_MIN_VALUE)
            resetQuality(item);
    }

    private void updateDefault(Item item){
        decreaseQualityIfNotMin(item);
        if(isConjured(item))
            decreaseQualityIfNotMin(item);
        if(item.sellIn <= SELLIN_MIN_VALUE) {
            decreaseQualityIfNotMin(item);
            if(isConjured(item))
                decreaseQualityIfNotMin(item);
        }
    }

    public void updateItems(){
        for (Item item : items) {
            updateQuality(item);
            updateSellIn(item);
        }
    }

    private void updateQuality(Item item){
        switch(item.name){
            case SULFURAS:
                break;
            case AGED_BRIE:
                updateBrie(item);
                break;
            case BACKSTAGE_PASSES:
                updateBackstage(item);
                break;
            default:
                updateDefault(item);
                break;
        }
    }

    private void updateSellIn(Item item){
        if(item.name != SULFURAS)
            decreaseSellIn(item);
    }

    private void decreaseSellIn(Item item){
        item.sellIn--;
    }

    private void increaseQualityIfNotMax(Item item){
        if (item.quality < QUALITY_MAX_VALUE)
            item.quality++;
    }

    private void decreaseQualityIfNotMin(Item item){
        if (item.quality > QUALITY_MIN_VALUE)
            item.quality--;
    }

    private void resetQuality(Item item){
        item.quality = QUALITY_MIN_VALUE;
    }

    private boolean isConjured(Item item){
        return item.name.contains(CONJURED);
    }
}