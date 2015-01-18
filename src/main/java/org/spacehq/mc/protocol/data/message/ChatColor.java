package org.spacehq.mc.protocol.data.message;

public enum ChatColor {

    BLACK,
    DARK_BLUE,
    DARK_GREEN,
    DARK_AQUA,
    DARK_RED,
    DARK_PURPLE,
    GOLD,
    GRAY,
    DARK_GRAY,
    BLUE,
    GREEN,
    AQUA,
    RED,
    LIGHT_PURPLE,
    YELLOW,
    WHITE,
    RESET;

	static final ChatColor[] values = values();

	private final String str = name().toLowerCase();

	@Override
	public String toString() {
		return str;
	}

	public static ChatColor byName(String name) {
		name = name.toLowerCase();
		for(ChatColor color : values) {
			if(color.toString().equals(name)) {
				return color;
			}
		}

        return null;
    }

}
