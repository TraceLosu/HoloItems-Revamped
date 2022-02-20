# HoloItems
A plugin that adds some custom items themed around hololive talents!

## Contributing
This plugin uses HoloItemsAPI to register and listen to custom items. To learn more about the API, click [here.](https://github.com/StrangeOne101/HoloItemsAPI)

### Adding an item
To create a new custom item, create a class that extends the `CustomItem` class. The internal name of the item should have no spaces. The internal ID of the item will be used as the custom model data. Anything else you can add/set (enchantments, cooldowns, etc.) can be found in the `CustomItem` class. Make sure to register the item at its constructor.
```java
    private final static String name = "tide_rider";
    private final static Material material = Material.TRIDENT;
    private final static String displayName = ChatColor.BLUE + "Tide Rider";
    private final static List<String> lore = List.of(
        "Allows you to riptide anywhere you want!"
        );

    private final NamespacedKey key;

    public TideRider() {
        super(name, material, displayName, lore);
        this.key = new NamespacedKey(HoloItemsAPI.getPlugin(), name);
        this.setMaxDurability(32);
        this.setStackable(false);
        this.register();
        }
```

When making custom items that are enchantable, make sure to create the custom enchantment as well, and apply said
custom enchantment on the `buildStack` method.

Once the class is created, add an instance of the class to the idol's constructor in the CollectionManager class,
like so:
```java
    var gura = new GawrGura(
        new TideRider()
    );
```

If you want to have an item that consists of a large runnable task, you should create a class that extends `ItemAbility`. More info about it [here](https://github.com/StrangeOne101/HoloItemsAPI/blob/master/src/main/java/com/strangeone101/holoitemsapi/ItemAbility.java). Put the class in the same package as the item class.

## Building and testing
This project is configured to be packaged by Gradle wrapper. The Gradle wrapper script is invoked by running `./gradlew.bat [task]` on Windows or `./gradlew [task]` on Linux!

The main tasks to use are `clean`, `build`, and `runServer`!
