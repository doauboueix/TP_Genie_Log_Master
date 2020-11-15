package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void show_Item_ToString() {
        String name = "random";
        int sellIn = 5;
        int quality = 10;
        Item item = new Item(name, sellIn, quality);
        String expected = name + ", " + sellIn + ", " + quality;
        assertEquals(expected, item.toString());
    }

    @Test
    // Qualité baisse de 1 pour un item basic si sellIn >= 0
    void reduce_Quality_Basic_Item(){
        Item[] items = new Item[] { new Item("random", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(9));
    }

    @Test
    // Qualité baisse 2 fois plus vite pour un item basic si sellIn < 0
    void reduce_Quality_By_Two_Basic_Item(){
        Item[] items = new Item[] { new Item("random", -10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(8));
    }

    @Test
    // Qualité ne peut pas être négative pour un item basic avec sellIn >= 0
    void no_Negative_Quality_Basic_Item(){
        Item[] items = new Item[] { new Item("random", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, greaterThanOrEqualTo(0));
    }

    @Test
    // Qualité ne peut pas être négative pour un item basic avec sellIn <= 0
    void no_Negative_Quality_Basic_Item2(){
        Item[] items = new Item[] { new Item("random", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, greaterThanOrEqualTo(0));
    }

    @Test
    // Qualité max pour l'item backstage est de 50
    void no_Quality_Above_50_Backstage(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, lessThanOrEqualTo(50));
    }

    @Test
    // Qualité de l'item brie augmente de 1 si sellIn >= 0
    void increase_Quality_Brie(){
        Item[] items = new Item[] { new Item("Aged Brie", 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, lessThanOrEqualTo(1));
    }

    @Test
    // Qualité de l'item brie augmente de 2 si sellIn < 0
    void increase_Quality_Brie2(){
        Item[] items = new Item[] { new Item("Aged Brie", -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, lessThanOrEqualTo(3));
    }

    @Test
    // Qualité max pour l'item brie est de 50
    void no_Quality_Above_50_Brie(){
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, lessThanOrEqualTo(50));
    }

    @Test
    // La qualité de Sulfuras est toujours égale à 80
    void never_Reduce_Quality_Sulfuras(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1000, 80) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(80));
    }

    @Test
    // Qualité de backstage +2 si sellIn compris entre 10 et 6
    void increase_Quality_By_2_If_SellIn_Between_10_And_6_Backstage(){
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 1),
        };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(3));
    }

    @Test
    // Qualité de backstage ne dépasse pas 50 si sellIn compris entre 10 et 6
    void increase_Quality_By_2_If_SellIn_Between_10_And_6_Backstage2(){
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
        };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(50));
    }

    @Test
    // Qualité de backstage +3 si sellIn compris entre 5 et 0
    void increase_Quality_By_3_If_SellIn_Lower_Than_6_Backstage(){
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 1),
        };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(4));
    }

    @Test
    // Qualité de backstage ne dépasse pas 50 si sellIn compris entre 5 et 0
    void increase_Quality_By_3_If_SellIn_Lower_Than_6_Backstage2(){
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48),
        };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(50));
    }

    @Test
    // Qualité de backstage <0 si sellIn < 0
    void reset_Quality_If_SellIn_Lowr_Or_Equal_0_Backstage(){
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
        };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(0));
    }

    @Test
    // sellIn d'item basic diminue de 1, sellIn >= 0
    void reduce_SellIn_Basic_Item(){
        Item[] items = new Item[] { new Item("random", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(9));
    }

    @Test
    // sellIn d'item basic diminue de 1, sellIn < 0
    void reduce_SellIn_Basic_Item2(){
        Item[] items = new Item[] { new Item("random", -10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(-11));
    }

    @Test
    // sellIn de Sulfuras ne diminue pas, sellIn >= 0
    void never_Reduce_SellIn_Sulfuras(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(10));
    }

    @Test
    // sellIn de Sulfuras ne diminue pas, sellIn < 0
    void never_Reduce_SellIn_Sulfuras2(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(-10));
    }

    @Test
    // sellIn d'item basic diminue de 1, sellIn >= 0
    void reduce_SellIn_Brie(){
        Item[] items = new Item[] { new Item("Aged Brie", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(9));
    }

    @Test
    // sellIn d'item basic diminue de 1, sellIn < 0
    void reduce_SellIn_Brie2(){
        Item[] items = new Item[] { new Item("Aged Brie", -10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(-11));
    }

    @Test
    // sellIn d'item basic diminue de 1, sellIn >= 0
    void reduce_SellIn_Backstage(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(9));
    }

    @Test
    // sellIn d'item basic diminue de 1, sellIn < 0
    void reduce_SellIn_Backstage2(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(-11));
    }

    @Test
    void reduce_SellIn_Conjured(){
        Item[] items = new Item[] { new Item("Conjured random", 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].sellIn, equalTo(0));
    }

    @Test
    void reduce_Quality_By_2_If_SellIn_Above_0_Conjured(){
        Item[] items = new Item[] { new Item("Conjured random", 1, 2) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(0));
    }

    @Test
    void never_Reduce_Quality_Under_0_If_SellIn_Above_0_Conjured(){
        Item[] items = new Item[] { new Item("Conjured random", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(0));
    }

    @Test
    void reduce_Quality_By_4_If_SellIn_Not_Above_0_Conjured(){
        Item[] items = new Item[] { new Item("Conjured random", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(0));
    }

    @Test
    void never_Reduce_Quality_Under_0_If_Sellin_Under_1_Conjured2(){
        Item[] items = new Item[] { new Item("Conjured random", 0, 3) };
        GildedRose app = new GildedRose(items);
        app.updateItems();
        assertThat(app.items[0].quality, equalTo(0));
    }
}
