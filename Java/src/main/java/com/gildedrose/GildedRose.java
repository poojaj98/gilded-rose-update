package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured Mana Cake";

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
            updateItemSellIn(item);
            handleExpiredItem(item);
        }
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            increaseQuality(item);
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            increaseBackstagePassQuality(item);
        } else if (item.name.equals(CONJURED)) {
            decreaseQuality(item, 2);
        } else if (!item.name.equals(SULFURAS)) {
            decreaseQuality(item, 1);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item, int amount) {
        if (item.quality > MIN_QUALITY) {
            item.quality -= amount;
            if (item.quality < MIN_QUALITY) {
                item.quality = MIN_QUALITY;
            }
        }
    }

    private void increaseBackstagePassQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
            if (item.sellIn <= 10) {
                increaseQuality(item);
            }
            if (item.sellIn <= 5) {
                increaseQuality(item);
            }
        }
    }

    private void updateItemSellIn(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn--;
        }
    }

    private void handleExpiredItem(Item item) {
        if (item.sellIn < 0) {
            if (item.name.equals(AGED_BRIE)) {
                increaseQuality(item);
            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                item.quality = MIN_QUALITY;
            } else if (item.name.equals(CONJURED)) {
                decreaseQuality(item, 2);
            } else if (!item.name.equals(SULFURAS)) {
                decreaseQuality(item, 1);
            }
        }
    }
}
