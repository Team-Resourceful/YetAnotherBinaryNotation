package com.teamresourceful.yabn.elements;

import com.teamresourceful.yabn.utils.ByteArrayUtils;
import org.jetbrains.annotations.Nullable;

public sealed interface YabnElement permits YabnObject, YabnPrimitive, YabnArray {

    byte EOD = 0x00; // end of data

    byte[] toData();

    default byte[] toFullData() {
        byte[] data = new byte[] {getType().id};
        return ByteArrayUtils.add(data, toData());
    }

    YabnType getType();

    default boolean isNull() {
        return YabnType.NULL.equals(getType());
    }

    @Nullable
    default YabnElement getOrNull() {
        return isNull() ? null : this;
    }
}
