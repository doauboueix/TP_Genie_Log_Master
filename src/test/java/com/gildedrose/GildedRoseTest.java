package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name, is("foo"));
    }

    @Test
    void QualityDownTwoTimes(){
        Item[] items = new Item[] { new Item("random", -10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality, equalTo(8));
    }

    @Test
    void NegativeQuality(){
        Item[] items = new Item[] { new Item("random", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality, greaterThanOrEqualTo(0));
    }

    @Test
    void MaxQuality(){
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality, lessThanOrEqualTo(50));
    }

    @Test
    void Sulfuras(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality, equalTo(10));
        assertThat(app.items[0].sellIn, equalTo(10));
    }

    @Test
    void Backstage(){
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality, equalTo(12));
        assertThat(app.items[1].quality, equalTo(13));
        assertThat(app.items[2].quality, equalTo(0));
    }

    @Test
    void Conjured(){
        Item[] items = new Item[] {
                new Item("Item Conjured", 10, 10),
                new Item("Item Conjured", 0, 10),
                new Item("Item Conjured", 0, 3)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality, equalTo(8));
        assertThat(app.items[1].quality, equalTo(6));
        assertThat(app.items[2].quality, equalTo(0));
    }
}
