from cgitb import text
from fileinput import filename
import re
import os
import glob

for directory in glob.glob("/home/spacenerden/Code/Projects/HoloItems/src/main/java/xyz/holocons/mc/holoitemsrevamp/collection/*/"):
    gen = re.search("/home/spacenerden/Code/Projects/HoloItems/src/main/java/xyz/holocons/mc/holoitemsrevamp/collection\/(.*?)\/", directory).group(1)
    print(gen)
    idols = ""
    for file in glob.glob(f"{directory}/*.java"):
        java = file.replace(f"/home/spacenerden/Code/Projects/HoloItems/src/main/java/xyz/holocons/mc/holoitemsrevamp/collection/{gen}/", "")
        print(java.replace(".java", "()"))
        idols + f'getIdolSet().add(new {java.replace(".java", "()")});\n'
    print(idols)

    
    with open(f'output/{gen}Collection.java', "x") as file:
        data = f"""package xyz.holocons.mc.holoitemsrevamp.collection.{gen};

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class {gen}Collection extends IdolCollection {{

    private static final String name = "{gen}";

    public {gen}Collection() {{
        super(name);
        {idols}
    }}

    @Override
    public @NotNull Material getMaterial() {{
        return Material.PAPER;
    }}

    @Override
    public @NotNull Component getDisplayName() {{
        return Component.text("REPLACE")
                .color(TextColor.color(0x1D83FF));
    }}

    @Override
    public List<Component> getLore() {{
        return null;
    }}
}}

            """
        file.write(data)
