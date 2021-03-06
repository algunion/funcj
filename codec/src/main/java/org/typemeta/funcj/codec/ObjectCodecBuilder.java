package org.typemeta.funcj.codec;

import org.typemeta.funcj.functions.*;

import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Provides a builder interface for building an object {@link Codec},
 * by specifying the means by which the fields that comprise the object
 * are extracted, and the mechanism used to construct an instance of the object.
 * @param <T> raw type to be encoded/decoded
 * @param <E> encoded type
 */
@SuppressWarnings("unchecked")
public class ObjectCodecBuilder<T, E> {
    public static class FieldCodec<T, E> {
        protected final FunctionsEx.F2<T, E, E> encoder;
        protected final FunctionsEx.F<E, Object> decoder;

        <FT> FieldCodec(FunctionsEx.F<T, FT> getter, Codec<FT, E> codec) {
            encoder = (val, enc) -> codec.encode(getter.apply(val), enc);
            decoder = enc -> codec.decode(enc);
        }

        public E encodeField(T val, E enc) throws Exception {
            return encoder.apply(val, enc);
        }

        public Object decodeField(E enc) throws Exception {
            return decoder.apply(enc);
        }
    }

    private final CodecCoreIntl<E> core;

    final Map<String, FieldCodec<T, E>> fields = new LinkedHashMap<>();

    public ObjectCodecBuilder(CodecCoreIntl<E> core) {
        this.core = core;
    }

    protected Codec<T, E> registration(Codec<T, E> codec) {
        return codec;
    }

    private <X> Codec<X, E> makeDynSafe(Codec<X, E> codec, Class<X> stcType) {
        if (Modifier.isFinal(stcType.getModifiers())) {
            return codec;
        } else {
            return core.dynamicCodec(codec, stcType);
        }
    }

    private <X> Codec<X, E> getNullSafeCodec(Class<X> stcType) {
        return core.makeNullSafeCodec(core.dynamicCodec(core.getNullUnsafeCodec(stcType), stcType));
    }

    private <X> Codec<X, E> getNullUnsafeCodec(Class<X> stcType) {
        return core.dynamicCodec(core.getNullUnsafeCodec(stcType), stcType);
    }

    <A> _1<A> field(String name, FunctionsEx.F<T, A> getter, Codec<A, E> codec) {
        fields.put(name, new FieldCodec<T, E>(getter, codec));
        return new _1<A>();
    }

    <A> _1<A> nullField(String name, FunctionsEx.F<T, A> getter, Class<A> clazz) {
        return field(name, getter, getNullSafeCodec(clazz));
    }

    <A> _1<A> field(String name, FunctionsEx.F<T, A> getter, Class<A> clazz) {
        return field(name, getter, getNullUnsafeCodec(clazz));
    }

    class _1<A> {
        public Codec<T, E> map(Functions.F<A, T> ctor) {
            return registration(
                    core.createObjectCodec(
                            fields,
                            arr -> ctor.apply((A)arr[0])));
        }

        <B> _2<B> field(String name, FunctionsEx.F<T, B> getter, Codec<B, E> codec) {
            fields.put(name, new FieldCodec<T, E>(getter, codec));
            return new _2<B>();
        }

        <B> _2<B> nullField(String name, FunctionsEx.F<T, B> getter, Class<B> clazz) {
            return field(name, getter, getNullSafeCodec(clazz));

        }

        <B> _2<B> field(String name, FunctionsEx.F<T, B> getter, Class<B> clazz) {
            return field(name, getter, getNullUnsafeCodec(clazz));
        }

        class _2<B> {
            public Codec<T, E> map(Functions.F2<A, B, T> ctor) {
                return registration(
                        core.createObjectCodec(
                                fields,
                                arr -> ctor.apply((A)arr[0], (B)arr[1])));
            }

            <C> _3<C> field(String name, FunctionsEx.F<T, C> getter, Codec<C, E> codec) {
                fields.put(name, new FieldCodec<T, E>(getter, codec));
                return new _3<C>();
            }

            <C> _3<C> nullField(String name, FunctionsEx.F<T, C> getter, Class<C> clazz) {
                return field(name, getter, getNullSafeCodec(clazz));
            }

            <C> _3<C> field(String name, FunctionsEx.F<T, C> getter, Class<C> clazz) {
                return field(name, getter, getNullUnsafeCodec(clazz));
            }

            class _3<C> {
                public Codec<T, E> map(Functions.F3<A, B, C, T> ctor) {
                    return registration(
                            core.createObjectCodec(
                                    fields,
                                    arr -> ctor.apply((A)arr[0], (B)arr[1], (C)arr[2])));
                }

                <D> _4<D> field(String name, FunctionsEx.F<T, D> getter, Codec<D, E> codec) {
                    fields.put(name, new FieldCodec<T, E>(getter, codec));
                    return new _4<D>();
                }

                <D> _4<D> nullField(String name, FunctionsEx.F<T, D> getter, Class<D> clazz) {
                    return field(name, getter, getNullSafeCodec(clazz));
                }

                <D> _4<D> field(String name, FunctionsEx.F<T, D> getter, Class<D> clazz) {
                    return field(name, getter, getNullUnsafeCodec(clazz));
                }

                class _4<D> {
                    public Codec<T, E> map(Functions.F4<A, B, C, D, T> ctor) {
                        return registration(
                                core.createObjectCodec(
                                        fields,
                                        arr -> ctor.apply((A)arr[0], (B)arr[1], (C)arr[2], (D)arr[3])));
                    }

                    <N> _N field(String name, FunctionsEx.F<T, N> getter, Codec<N, E> codec) {
                        fields.put(name, new FieldCodec<T, E>(getter, codec));
                        return new _N();
                    }

                    <N> _N nullField(String name, FunctionsEx.F<T, N> getter, Class<N> clazz) {
                        return field(name, getter, getNullSafeCodec(clazz));
                    }

                    <N> _N field(String name, FunctionsEx.F<T, N> getter, Class<N> clazz) {
                        return field(name, getter, getNullUnsafeCodec(clazz));
                    }

                    class _N {
                        public Codec<T, E> map(Functions.F<Object[], T> ctor) {
                            return registration(core.createObjectCodec(fields, ctor));
                        }

                        <N> _N field(String name, FunctionsEx.F<T, N> getter, Codec<N, E> codec) {
                            fields.put(name, new FieldCodec<T, E>(getter, codec));
                            return new _N();
                        }

                        <N> _N nullField(String name, FunctionsEx.F<T, N> getter, Class<N> clazz) {
                            return field(name, getter, getNullSafeCodec(clazz));
                        }

                        <N> _N field(String name, FunctionsEx.F<T, N> getter, Class<N> clazz) {
                            return field(name, getter, getNullUnsafeCodec(clazz));
                        }
                    }
                }
            }
        }
    }
}
