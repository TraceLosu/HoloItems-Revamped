# HoloItems
A plugin that adds some custom items to the server, themed with hololive talents!

## Contributing
This plugin uses HoloItemsAPI to register and listen to custom items. To learn more about the API, click [here.](https://github.com/StrangeOne101/HoloItemsAPI)

### Adding an item
To create a new custom item, create a class that extends the `CustomItem` class. The internal name of the item should have no spaces. The internal ID of the item will be used as the custom model data. Anything else you can add/set (enchantments, cooldowns, etc.) can be found in the `CustomItem` class. Make sure to register the item at its constructor.
```java
    private final static String name = "tideRider";
    private final static Material material = Material.TRIDENT;
    private final static String displayName = ChatColor.BLUE + "Tide Rider";
    private final static List<String> lore = List.of(
            "Allows you to riptide anywhere you want!"
    );
    private final static long cooldown = 5000;


    public TideRider() {
        super(name, material, displayName, lore);
        this.setMaxDurability(32);
        this.register();
    }
```

Once the class is created, add an instance of the class to the Set in the Idol's constructor, like so:
```java
    public GawrGura() {
        super(name, base64);
        getItemSet().add(new TideRider()); //Adding the instance of the item class to the idol's item set.
    }
```

If you want to have an item that consists of a large runnable task, you should create a class that extends `ItemAbility`. More info about it [here](https://github.com/StrangeOne101/HoloItemsAPI/blob/master/src/main/java/com/strangeone101/holoitemsapi/ItemAbility.java). Put the class in the same package as the item class.