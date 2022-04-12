package slarper.pureason.spell;

import slarper.pureason.event.SpellUseOnEntityCallback;

public class Spells {
    public static final FireTouch FIRE_TOUCH = new FireTouch();

    public static void load() {}

    static {
        SpellUseOnEntityCallback.EVENT.register(FIRE_TOUCH);
    }
}
