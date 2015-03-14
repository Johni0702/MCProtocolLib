package org.spacehq.mc.protocol.data.game.attribute;

import org.spacehq.mc.protocol.data.game.values.entity.ModifierOperation;
import org.spacehq.mc.protocol.data.game.values.entity.ModifierType;

public class AttributeModifier {

	private ModifierType type;
	private double amount;
	private ModifierOperation operation;

	public AttributeModifier(ModifierType type, double amount, ModifierOperation operation) {
		this.type = type;
		this.amount = amount;
		this.operation = operation;
	}

	public ModifierType getType() {
		return this.type;
	}

	public double getAmount() {
		return this.amount;
	}

	public ModifierOperation getOperation() {
		return this.operation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AttributeModifier that = (AttributeModifier) o;

		if (Double.compare(that.amount, amount) != 0) return false;
		if (operation != that.operation) return false;
		if (type != that.type) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = type.hashCode();
		long temp = Double.doubleToLongBits(amount);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + operation.hashCode();
		return result;
	}

}
