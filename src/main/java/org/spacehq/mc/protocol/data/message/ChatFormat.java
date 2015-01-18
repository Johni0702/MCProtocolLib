package org.spacehq.mc.protocol.data.message;

public enum ChatFormat {

    BOLD,
    UNDERLINED,
    STRIKETHROUGH,
    ITALIC,
    OBFUSCATED;

	static final ChatFormat[] values = values();

	private final String str = name().toLowerCase();

	@Override
	public String toString() {
		return str;
	}

	public static ChatFormat byName(String name) {
		name = name.toLowerCase();
		for(ChatFormat format : values) {
			if(format.toString().equals(name)) {
				return format;
			}
		}

        return null;
    }

}