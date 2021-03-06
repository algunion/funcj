package org.typemeta.funcj.codec;

import org.typemeta.funcj.codec.utils.OperationNotImplementedException;

/**
 * Interface for classes that encapsulates the logic for encoding a value of type {@code T}
 * into a value of type {@code E} and vice versa.
 * @param <T>       the raw type to be encoded/decoded
 * @param <E>       the encoded type
 */
public interface Codec<T, E> {

    /**
     * Codec for null values.
     * @param <E>       the encoded type
     */
    interface NullCodec<E> extends Codec<Object, E> {
        /**
         * Check whether an encoded value represents a null value.
         * @param enc       encoded value
         * @return          true if encoded value represents a null value
         * @throws Exception if the operation fails
         */
        boolean isNull(E enc) throws Exception;
    }

    /**
     * Codec for {@link java.lang.Boolean} and {@code boolean} values.
     * @param <E>       the encoded type
     */
    abstract class BooleanCodec<E> implements Codec<Boolean, E> {

        @Override
        public E encode(Boolean val, E enc) throws Exception {
            return encodePrim(val, enc) ;
        }

        @Override
        public Boolean decode(Class<Boolean> dynType, E enc) throws Exception {
            return decodePrim(enc);
        }

        @Override
        public Boolean decode(E enc) throws Exception {
            return decodePrim(enc);
        }

        public E encodePrim(boolean val, E enc) throws Exception {
            return encodePrim(val);
        }

        public E encodePrim(boolean val) throws Exception {
            throw new OperationNotImplementedException();
        }

        public abstract boolean decodePrim(E enc) throws Exception;
    }

    /**
     * Codec for {@link java.lang.Byte} and {@code byte} values.
     * @param <E>       the encoded type
     */
    abstract class ByteCodec<E> implements Codec<Byte, E> {

        @Override
        public E encode(Byte val, E enc) throws Exception {
            return encodePrim(val, enc) ;
        }

        @Override
        public Byte decode(Class<Byte> dynType, E enc) throws Exception {
            return decodePrim(enc);
        }

        @Override
        public Byte decode(E enc) throws Exception {
            return decodePrim(enc);
        }

        public E encodePrim(byte val, E enc) throws Exception {
            return encodePrim(val);
        }

        public E encodePrim(byte val) throws Exception {
            throw new OperationNotImplementedException();
        }

        public abstract byte decodePrim(E enc) throws Exception;
    }

    /**
     * Codec for {@link java.lang.Character} and {@code char} values.
     * @param <E>       the encoded type
     */
    abstract class CharCodec<E> implements Codec<Character, E> {

        @Override
        public E encode(Character val, E enc) throws Exception {
            return encodePrim(val, enc) ;
        }

        @Override
        public Character decode(Class<Character> dynType, E enc) throws Exception {
            return decodePrim(enc);
        }

        @Override
        public Character decode(E enc) throws Exception {
            return decodePrim(enc);
        }

        public E encodePrim(char val, E enc) throws Exception {
            return encodePrim(val);
        }

        public E encodePrim(char val) throws Exception {
            throw new OperationNotImplementedException();
        }

        public abstract char decodePrim(E enc) throws Exception;
    }

    /**
     * Codec for {@link java.lang.Short} and {@code short} values.
     * @param <E>       the encoded type
     */
    abstract class ShortCodec<E> implements Codec<Short, E> {

        @Override
        public E encode(Short val, E enc) throws Exception {
            return encodePrim(val, enc) ;
        }

        @Override
        public Short decode(Class<Short> dynType, E enc) throws Exception {
            return decodePrim(enc);
        }

        @Override
        public Short decode(E enc) throws Exception {
            return decodePrim(enc);
        }

        public E encodePrim(short val, E enc) throws Exception {
            return encodePrim(val);
        }

        public E encodePrim(short val) throws Exception {
            throw new OperationNotImplementedException();
        }

        public abstract short decodePrim(E enc) throws Exception;
    }

    /**
     * Codec for {@link java.lang.Integer} and {@code int} values.
     * @param <E>       the encoded type
     */
    abstract class IntCodec<E> implements Codec<Integer, E> {

        @Override
        public E encode(Integer val, E enc) throws Exception {
            return encodePrim(val, enc) ;
        }

        @Override
        public Integer decode(Class<Integer> dynType, E enc) throws Exception {
            return decodePrim(enc);
        }

        @Override
        public Integer decode(E enc) throws Exception {
            return decodePrim(enc);
        }

        public E encodePrim(int val, E enc) throws Exception {
            return encodePrim(val);
        }

        public E encodePrim(int val) throws Exception {
            throw new OperationNotImplementedException();
        }

        public abstract int decodePrim(E enc) throws Exception;
    }

    /**
     * Codec for {@link java.lang.Long} and {@code long} values.
     * @param <E>       the encoded type
     */
    abstract class LongCodec<E> implements Codec<Long, E> {

        @Override
        public E encode(Long val, E enc) throws Exception {
            return encodePrim(val, enc) ;
        }

        @Override
        public Long decode(Class<Long> dynType, E enc) throws Exception {
            return decodePrim(enc);
        }

        @Override
        public Long decode(E enc) throws Exception {
            return decodePrim(enc);
        }

        public E encodePrim(long val, E enc) throws Exception {
            return encodePrim(val);
        }

        public E encodePrim(long val) throws Exception {
            throw new OperationNotImplementedException();
        }

        public abstract long decodePrim(E enc) throws Exception;
    }

    /**
     * Codec for {@link java.lang.Float} and {@code float} values.
     * @param <E>       the encoded type
     */
    abstract class FloatCodec<E> implements Codec<Float, E> {

        @Override
        public E encode(Float val, E enc) throws Exception {
            return encodePrim(val, enc) ;
        }

        @Override
        public Float decode(Class<Float> dynType, E enc) throws Exception {
            return decodePrim(enc);
        }

        @Override
        public Float decode(E enc) throws Exception {
            return decodePrim(enc);
        }

        public E encodePrim(float val, E enc) throws Exception {
            return encodePrim(val);
        }

        public E encodePrim(float val) throws Exception {
            throw new OperationNotImplementedException();
        }

        public abstract float decodePrim(E enc) throws Exception;
    }

    /**
     * Codec for {@link java.lang.Double} and {@code double} values.
     * @param <E>       the encoded type
     */
    abstract class DoubleCodec<E> implements Codec<Double, E> {

        @Override
        public E encode(Double val, E enc) throws Exception {
            return encodePrim(val, enc) ;
        }

        @Override
        public Double decode(Class<Double> dynType, E enc) throws Exception {
            return decodePrim(enc);
        }

        @Override
        public Double decode(E enc) throws Exception {
            return decodePrim(enc);
        }

        public E encodePrim(double val, E enc) throws Exception {
            return encodePrim(val);
        }

        public E encodePrim(double val) throws Exception {
            throw new OperationNotImplementedException();
        }

        public abstract double decodePrim(E enc) throws Exception;
    }

    /**
     * Encode a value of type {@code T} into nd encoded value of type {@code E}.
     * @param val       the unencoded value
     * @param enc       the encoded parent value
     * @return          the encoded value
     * @throws Exception  if the operation fails
     */
    E encode(T val, E enc) throws Exception;

    /**
     * Decode a value of type {@code E} back into a value of type {@code T}.
     * One of the two {@code decode} methods must be implemented by sub-classes.
     * @param dynType   the dynamic type to decode into.
     * @param enc       the encoded value
     * @return          the decoded value
     * @throws Exception  if the operation fails
     */
    default T decode(Class<T> dynType, E enc) throws Exception {
        return decode(enc);
    }

    /**
     * Decode an encoded value of type {@code E} back into a value of type {@code T}.
     * One of the two {@code decode} methods must be implemented by sub-classes.
     * @param enc       the encoded value
     * @return          the decoded value
     * @throws Exception  if the operation fails
     */
    default T decode(E enc) throws Exception {
        throw new OperationNotImplementedException();
    }
}
