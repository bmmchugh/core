package com.frdna.core;

public final class Assert {

    private Assert() { }

    /**
     * Throws an exception when <code>value</code> is <code>null</code>.
     *
     * @param label
     *     identifies <code>value</code>
     * @param value
     *     value tested for <code>null</code>
     * @throws NullPointerException
     *     if <code>value</code> is <code>null</code>
     */
    public static void notNull(final String label, final Object value) {
        if (value != null) {
            return;
        }

        throw new NullPointerException(label + " must not be null");
    }
}
