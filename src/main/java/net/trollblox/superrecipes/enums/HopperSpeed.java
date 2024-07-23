package net.trollblox.superrecipes.enums;

public enum HopperSpeed {
    VANILLA(true),
    MODDED(false);

    private final boolean value;

    HopperSpeed(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public static HopperSpeed getHopperSpeedFromValue(boolean value) {
        for (HopperSpeed speed : HopperSpeed.values()) {
            if (speed.getValue() == value) return speed;
        }
        return HopperSpeed.getHopperSpeedFromValue(false);
    }

    public static HopperSpeed getHopperSpeedFromValueInverse(boolean value) {
        for (HopperSpeed speed : HopperSpeed.values()) {
            if (speed.getValue() == !value) return speed;
        }
        return HopperSpeed.getHopperSpeedFromValue(false);
    }

}
