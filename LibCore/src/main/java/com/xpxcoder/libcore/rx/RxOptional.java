package com.xpxcoder.libcore.rx;

/**
 * @author XPSoft
 */
public class RxOptional<M> {

    private final M optional;

    public RxOptional(M optional) {
        this.optional = optional;
    }

    public boolean isEmpty() {
        return this.optional == null;
    }

    public M get() {
        return optional;
    }
}