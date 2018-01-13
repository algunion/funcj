package org.typemeta.funcj.util;

import org.typemeta.funcj.functions.FunctionsGenEx.*;

import java.util.*;

/**
 * Fold operations for lambdas with can throw.
 */
public abstract class FoldsGenEx {
    /**
     * Left-fold a function over an {@link Iterable}.
     * @param f binary function to be applied for the fold
     * @param z starting value for the fold
     * @param iter Iterable to be folded over
     * @param <T> iterable element type
     * @param <R> result type of fold operation
     * @return the folded value
     */
    public static <T, R, X extends Exception> R foldLeft(F2<R, T, R, X> f, R z, Iterable<T> iter) throws X {
        R acc = z;
        for (T t : iter) {
            acc = f.apply(acc, t);
        }
        return acc;
    }

    /**
     * Left-fold a function over a non-empty {@link Iterable}.
     * @param f binary operator to be applied for the fold
     * @param iter Iterable to be folded over
     * @param <T> iterable element type
     * @return the folded value
     */
    public static <T, X extends Exception> T foldLeft1(Op2<T, X> f, Iterable<T> iter) throws X {
        T acc = null;
        for (T t : iter) {
            if (acc == null) {
                acc = t;
            } else {
                acc = f.apply(acc, t);
            }
        }

        if (acc == null) {
            throw new IllegalArgumentException("Supplied Iterable argument is empty");
        } else {
            return acc;
        }
    }

    /**
     *
     * Right-fold a function over an {@link List}}
     * @param f binary function to be applied for the fold
     * @param z starting value for the fold
     * @param l list to fold over
     * @param <T> list element type
     * @param <R> result type of fold operation
     * @return the folded value
     */
    public static <T, R, X extends Exception> R foldRight(F2<T, R, R, X> f, R z, List<T> l) throws X {
        R acc = z;
        for (int i = l.size() - 1; i >= 0; --i) {
            acc = f.apply(l.get(i), acc);
        }
        return acc;
    }

    /**
     * Right-fold a function over a non-empty {@link List}.
     * @param f binary operator to be applied for the fold
     * @param l {@code List} to fold over
     * @param <T> list element type
     * @return the folded value
     */
    public static <T, X extends Exception> T foldRight1(Op2<T, X> f, List<T> l) throws X {
        final int i0 = l.size() - 1;
        T acc = null;
        for (int i = i0; i >= 0; --i) {
            if (i == i0) {
                acc = l.get(i);
            } else {
                acc = f.apply(l.get(i), acc);
            }
        }
        return acc;
    }

    /**
     * Right-fold a function over an {@link Set}}
     * @param f binary function to be applied for the fold
     * @param z starting value for the fold
     * @param s set to fold over
     * @param <T> set element type
     * @param <R> result type of fold operation
     * @return the folded value
     */
    public static <T, R, X extends Exception> R foldRight(F2<T, R, R, X> f, R z, Set<T> s) throws X {
        return foldRight(f, z, new ArrayList<T>(s));
    }

    /**
     * Right-fold a function over a non-empty  {@link Set}}
     * @param f binary function to be applied for the fold
     * @param s set to fold over
     * @param <T> set element type
     * @return the folded value
     */
    public static <T, X extends Exception> T foldRight1(Op2<T, X> f, Set<T> s) throws X {
        return foldRight1(f, new ArrayList<T>(s));
    }
}