package com.kingmang.lazurite.runtime.values;


import com.kingmang.lazurite.core.Types;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class LzrNumber implements LzrValue {

    private static final int CACHE_MIN = -128;
    private static final int CACHE_MAX = 127;

    public static final LzrNumber MINUS_ONE;
    public static final LzrNumber ZERO;
    public static final LzrNumber ONE;

    private static final LzrNumber[] NUMBER_CACHE;

    static {
        final int length = CACHE_MAX - CACHE_MIN + 1;
        NUMBER_CACHE = new LzrNumber[length];
        int value = CACHE_MIN;
        for (int i = 0; i < length; i++) {
            NUMBER_CACHE[i] = new LzrNumber(value++);
        }

        final int zeroIndex = -CACHE_MIN;
        MINUS_ONE = NUMBER_CACHE[zeroIndex - 1];
        ZERO = NUMBER_CACHE[zeroIndex];
        ONE = NUMBER_CACHE[zeroIndex + 1];
    }


    public static LzrNumber fromBoolean(boolean b) {
        return b ? ONE : ZERO;
    }

    public static LzrNumber of(int value) {
        if (CACHE_MIN <= value && value <= CACHE_MAX) {
            return NUMBER_CACHE[-CACHE_MIN + value];
        }
        return new LzrNumber(value);
    }

    public static LzrNumber of(Number value) {
        return new LzrNumber(value);
    }

    private final Number value;
    
    @Override
    public int type() {
        return Types.NUMBER;
    }
    
    @Override
    public Number raw() {
        return value;
    }

    public boolean asBoolean() {
        return value.intValue() != 0;
    }
    
    public byte asByte() {
        return value.byteValue();
    }
    
    public short asShort() {
        return value.shortValue();
    }

    @Override
    public int asInt() {
        return value.intValue();
    }

    public long asLong() {
        return value.longValue();
    }

    public float asFloat() {
        return value.floatValue();
    }

    public double asDouble() {
        return value.doubleValue();
    }
    
    @Override
    public double asNumber() {
        return value.doubleValue();
    }

    @Override
    public String asString() {
        return value.toString();
    }

    @Override
    public int[] asArray() {
        return new int[0];
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + value.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass())
            return false;
        final Number other = ((LzrNumber) obj).value;
        if (value instanceof Double || other instanceof Double) {
            return Double.compare(value.doubleValue(), other.doubleValue()) == 0;
        }
        if (value instanceof Float || other instanceof Float) {
            return Float.compare(value.floatValue(), other.floatValue()) == 0;
        }
        if (value instanceof Long || other instanceof Long) {
            return Long.compare(value.longValue(), other.longValue()) == 0;
        }
        return Integer.compare(value.intValue(), other.intValue()) == 0;
    }
    
    @Override
    public int compareTo(LzrValue o) {
        if (o.type() == Types.NUMBER) {
            final Number other = ((LzrNumber) o).value;
            if (value instanceof Double || other instanceof Double) {
                return Double.compare(value.doubleValue(), other.doubleValue());
            }
            if (value instanceof Float || other instanceof Float) {
                return Float.compare(value.floatValue(), other.floatValue());
            }
            if (value instanceof Long || other instanceof Long) {
                return Long.compare(value.longValue(), other.longValue());
            }
            return Integer.compare(value.intValue(), other.intValue());
        }
        return asString().compareTo(o.asString());
    }

    @Override
    public String toString() {
        return asString();
    }
}
