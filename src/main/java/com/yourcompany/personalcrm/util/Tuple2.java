package com.yourcompany.personalcrm.util;

import java.util.function.BiFunction;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Tuple2<A, B>
{
    public final A a;
    public final B b;

    public <T> T map(@NonNull final BiFunction<A, B, T> function)
    {
        return function.apply(a, b);
    }
}
