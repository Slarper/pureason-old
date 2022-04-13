package slarper.pureason.spell;

public class Spells {
    public static final Spell SPELL;

    public static void load() {}

    static {
        SPELL = Spell.register(new Spell("key"));
    }
}
