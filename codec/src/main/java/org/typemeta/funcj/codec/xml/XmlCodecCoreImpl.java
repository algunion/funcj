package org.typemeta.funcj.codec.xml;

import org.typemeta.funcj.codec.*;
import org.typemeta.funcj.util.FoldsGenEx;
import org.w3c.dom.*;

import java.lang.reflect.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class XmlCodecCoreImpl extends BaseCodecCore<Element> implements XmlCodecCore {

    public XmlCodecCoreImpl() {
    }

    public String entryElemName() {
        return "elem";
    }

    public String typeAttrName() {
        return "type";
    }

    public String keyAttrName() {
        return "key";
    }

    public String keyElemName() {
        return "key";
    }

    public String valueElemName() {
        return "value";
    }


    public String metaAttrName() {
        return "meta";
    }

    public Element addEntryElement(Element parent) {
        return XmlUtils.addElement(parent, entryElemName());
    }

    protected final Codec.NullCodec<Element> nullCodec = new Codec.NullCodec<Element>() {

        private static final String nullAttrVal = "null";

        @Override
        public boolean isNull(Element enc) {
            return XmlUtils.getAttrValue(enc, metaAttrName()).equals(nullAttrVal);
        }

        @Override
        public Element encode(Object val, Element enc) {
            return XmlUtils.setAttrValue(enc, metaAttrName(), nullAttrVal);
        }

        @Override
        public Object decode(Element enc) throws Exception {
            if (isNull(enc)) {
                return null;
            } else {
                throw new CodecException("Expected a null node but got " + enc.getNodeName());
            }
        }
    };

    @Override
    public Codec.NullCodec<Element> nullCodec() {
        return nullCodec;
    }

    protected final Codec.BooleanCodec<Element> booleanCodec = new Codec.BooleanCodec<Element>() {

        @Override
        public Element encodePrim(boolean val, Element enc) {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public boolean decodePrim(Element enc) throws CodecException {
            return Boolean.parseBoolean(XmlUtils.firstChildText(enc).getWholeText());
        }
    };

    @Override
    public Codec.BooleanCodec<Element> booleanCodec() {
        return booleanCodec;
    }

    protected final Codec<boolean[], Element> booleanArrayCodec = new Codec<boolean[], Element>() {

        @Override
        public Element encode(boolean[] vals, Element enc) throws Exception {
            for (boolean val : vals) {
                booleanCodec().encode(val, addEntryElement(enc));
            }

            return enc;
        }

        @Override
        public boolean[] decode(Element enc) throws Exception {
            final NodeList nodes = enc.getChildNodes();
            final int l = nodes.getLength();
            final boolean[] vals = new boolean[l];

            for (int i = 0; i < l; ++i) {
                final Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elem = (Element) node;
                    if (elem.getNodeName().equals(entryElemName())) {
                        vals[i] = booleanCodec().decode(elem);
                    }
                }
            }

            return vals;
        }
    };

    @Override
    public Codec<boolean[], Element> booleanArrayCodec() {
        return booleanArrayCodec;
    }

    protected final Codec.ByteCodec<Element> byteCodec = new Codec.ByteCodec<Element>() {

        @Override
        public Element encodePrim(byte val, Element enc) {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public byte decodePrim(Element enc) throws CodecException {
            return Byte.parseByte(XmlUtils.firstChildText(enc).getWholeText());
        }
    };

    @Override
    public Codec.ByteCodec<Element> byteCodec() {
        return byteCodec;
    }

    protected final Codec<byte[], Element> byteArrayCodec = new Codec<byte[], Element>() {

        @Override
        public Element encode(byte[] vals, Element enc) throws Exception {
            for (byte val : vals) {
                byteCodec().encode(val, addEntryElement(enc));
            }

            return enc;
        }

        @Override
        public byte[] decode(Element enc) throws Exception {
            final NodeList nodes = enc.getChildNodes();
            final int l = nodes.getLength();
            final byte[] vals = new byte[l];

            for (int i = 0; i < l; ++i) {
                final Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elem = (Element) node;
                    if (elem.getNodeName().equals(entryElemName())) {
                        vals[i] = byteCodec().decode(elem);
                    }
                }
            }

            return vals;
        }
    };

    @Override
    public Codec<byte[], Element> byteArrayCodec() {
        return byteArrayCodec;
    }

    protected final Codec.CharCodec<Element> charCodec = new Codec.CharCodec<Element>() {

        @Override
        public Element encodePrim(char val, Element enc) {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public char decodePrim(Element enc) throws CodecException {
            return XmlUtils.firstChildText(enc).getWholeText().charAt(0);
        }
    };

    @Override
    public Codec.CharCodec<Element> charCodec() {
        return charCodec;
    }

    protected final Codec<char[], Element> charArrayCodec = new Codec<char[], Element>() {

        @Override
        public Element encode(char[] vals, Element enc) throws Exception {
            for (char val : vals) {
                charCodec().encode(val, addEntryElement(enc));
            }

            return enc;
        }

        @Override
        public char[] decode(Element enc) throws Exception {
            final NodeList nodes = enc.getChildNodes();
            final int l = nodes.getLength();
            final char[] vals = new char[l];

            for (int i = 0; i < l; ++i) {
                final Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elem = (Element) node;
                    if (elem.getNodeName().equals(entryElemName())) {
                        vals[i] = charCodec().decode(elem);
                    }
                }
            }

            return vals;
        }
    };

    @Override
    public Codec<char[], Element> charArrayCodec() {
        return charArrayCodec;
    }

    protected final Codec.ShortCodec<Element> shortCodec = new Codec.ShortCodec<Element>() {

        @Override
        public Element encodePrim(short val, Element enc) {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public short decodePrim(Element enc) throws CodecException {
            return Short.parseShort(XmlUtils.firstChildText(enc).getWholeText());
        }
    };

    @Override
    public Codec.ShortCodec<Element> shortCodec() {
        return shortCodec;
    }

    protected final Codec<short[], Element> shortArrayCodec = new Codec<short[], Element>() {

        @Override
        public Element encode(short[] vals, Element enc) throws Exception {
            for (short val : vals) {
                shortCodec().encode(val, addEntryElement(enc));
            }

            return enc;
        }

        @Override
        public short[] decode(Element enc) throws Exception {
            final NodeList nodes = enc.getChildNodes();
            final int l = nodes.getLength();
            final short[] vals = new short[l];

            for (int i = 0; i < l; ++i) {
                final Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elem = (Element) node;
                    if (elem.getNodeName().equals(entryElemName())) {
                        vals[i] = shortCodec().decode(elem);
                    }
                }
            }

            return vals;
        }
    };

    @Override
    public Codec<short[], Element> shortArrayCodec() {
        return shortArrayCodec;
    }

    protected final Codec.IntCodec<Element> intCodec = new Codec.IntCodec<Element>() {

        @Override
        public Element encodePrim(int val, Element enc) {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public int decodePrim(Element enc) throws CodecException {
            return Integer.parseInt(XmlUtils.firstChildText(enc).getWholeText());
        }
    };

    @Override
    public Codec.IntCodec<Element> intCodec() {
        return intCodec;
    }

    protected final Codec<int[], Element> intArrayCodec = new Codec<int[], Element>() {

        @Override
        public Element encode(int[] vals, Element enc) throws Exception {
            for (int val : vals) {
                intCodec().encode(val, addEntryElement(enc));
            }

            return enc;
        }

        @Override
        public int[] decode(Element enc) throws Exception {
            final NodeList nodes = enc.getChildNodes();
            final int l = nodes.getLength();
            final int[] vals = new int[l];

            for (int i = 0; i < l; ++i) {
                final Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elem = (Element) node;
                    if (elem.getNodeName().equals(entryElemName())) {
                        vals[i] = intCodec().decode(elem);
                    }
                }
            }

            return vals;
        }
    };

    @Override
    public Codec<int[], Element> intArrayCodec() {
        return intArrayCodec;
    }

    protected final Codec.LongCodec<Element> longCodec = new Codec.LongCodec<Element>() {

        @Override
        public Element encodePrim(long val, Element enc) {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public long decodePrim(Element enc) throws CodecException {
            return Long.parseLong(XmlUtils.firstChildText(enc).getWholeText());
        }
    };

    @Override
    public Codec.LongCodec<Element> longCodec() {
        return longCodec;
    }

    protected final Codec<long[], Element> longArrayCodec = new Codec<long[], Element>() {

        @Override
        public Element encode(long[] vals, Element enc) throws Exception {
            for (long val : vals) {
                longCodec().encode(val, addEntryElement(enc));
            }

            return enc;
        }

        @Override
        public long[] decode(Element enc) throws Exception {
            final NodeList nodes = enc.getChildNodes();
            final int l = nodes.getLength();
            final long[] vals = new long[l];

            for (int i = 0; i < l; ++i) {
                final Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elem = (Element) node;
                    if (elem.getNodeName().equals(entryElemName())) {
                        vals[i] = longCodec().decode(elem);
                    }
                }
            }

            return vals;
        }
    };

    @Override
    public Codec<long[], Element> longArrayCodec() {
        return longArrayCodec;
    }

    protected final Codec.FloatCodec<Element> floatCodec = new Codec.FloatCodec<Element>() {

        @Override
        public Element encodePrim(float val, Element enc) {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public float decodePrim(Element enc) throws CodecException {
            return Float.parseFloat(XmlUtils.firstChildText(enc).getWholeText());
        }
    };

    @Override
    public Codec.FloatCodec<Element> floatCodec() {
        return floatCodec;
    }

    protected final Codec<float[], Element> floatArrayCodec = new Codec<float[], Element>() {

        @Override
        public Element encode(float[] vals, Element enc) throws Exception {
            for (float val : vals) {
                floatCodec().encode(val, addEntryElement(enc));
            }

            return enc;
        }

        @Override
        public float[] decode(Element enc) throws Exception {
            final NodeList nodes = enc.getChildNodes();
            final int l = nodes.getLength();
            final float[] vals = new float[l];

            for (int i = 0; i < l; ++i) {
                final Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elem = (Element) node;
                    if (elem.getNodeName().equals(entryElemName())) {
                        vals[i] = floatCodec().decode(elem);
                    }
                }
            }

            return vals;
        }
    };

    @Override
    public Codec<float[], Element> floatArrayCodec() {
        return floatArrayCodec;
    }

    protected final Codec.DoubleCodec<Element> doubleCodec = new Codec.DoubleCodec<Element>() {

        @Override
        public Element encodePrim(double val, Element enc) {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public double decodePrim(Element enc) throws CodecException {
            return Double.parseDouble(XmlUtils.firstChildText(enc).getWholeText());
        }
    };

    @Override
    public Codec.DoubleCodec<Element> doubleCodec() {
        return doubleCodec;
    }

    protected final Codec<double[], Element> doubleArrayCodec = new Codec<double[], Element>() {

        @Override
        public Element encode(double[] vals, Element enc) throws Exception {
            for (double val : vals) {
                doubleCodec().encode(val, addEntryElement(enc));
            }

            return enc;
        }

        @Override
        public double[] decode(Element enc) throws Exception {
            final NodeList nodes = enc.getChildNodes();
            final int l = nodes.getLength();
            final double[] vals = new double[l];

            for (int i = 0; i < l; ++i) {
                final Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elem = (Element) node;
                    if (elem.getNodeName().equals(entryElemName())) {
                        vals[i] = doubleCodec().decode(elem);
                    }
                }
            }

            return vals;
        }
    };

    @Override
    public Codec<double[], Element> doubleArrayCodec() {
        return doubleArrayCodec;
    }

    protected final Codec<String, Element> stringCodec = new Codec<String, Element>() {
        @Override
        public Element encode(String val, Element enc) throws Exception {
            return XmlUtils.addTextElement(enc, String.valueOf(val));
        }

        @Override
        public String decode(Element enc) throws Exception {
            return XmlUtils.firstChildText(enc).getWholeText();
        }
    };

    @Override
    public Codec<String, Element> stringCodec() {
        return stringCodec;
    }

    @Override
    public <EM extends Enum<EM>> Codec<EM, Element> enumCodec(Class<? super EM> enumType) {
        return new Codec<EM, Element>() {
            @Override
            public Element encode(EM val, Element enc) throws Exception {
                return XmlUtils.addTextElement(enc, val.name());
            }

            @Override
            public EM decode(Class<EM> dynType, Element enc) throws Exception {
                Class<EM> type = dynType != null ? dynType : (Class<EM>)enumType;
                return EM.valueOf(type, XmlUtils.firstChildText(enc).getWholeText());
            }
        };
    }

    @Override
    public <V> Codec<Map<String, V>, Element> mapCodec(Codec<V, Element> valueCodec) {
        return new XmlMapCodecs.StringMapCodec<V>(this, valueCodec);
    }

    @Override
    public <K, V> Codec<Map<K, V>, Element> mapCodec(Codec<K, Element> keyCodec, Codec<V, Element> valueCodec) {
        return new XmlMapCodecs.MapCodec<K, V>(this, keyCodec, valueCodec);
    }

    @Override
    public <T> Codec<Collection<T>, Element> collCodec(Class<T> elemType, Codec<T, Element> elemCodec) {
        return new Codec<Collection<T>, Element>() {
            @Override
            public Element encode(Collection<T> vals, Element enc) throws Exception {
                for (T val : vals) {
                    elemCodec.encode(val, addEntryElement(enc));
                }

                return enc;
            }

            @Override
            public Collection<T> decode(Class<Collection<T>> dynType, Element enc) throws Exception {
                final Class<T> dynElemType = (Class<T>)dynType.getComponentType();

                final NodeList nodes = enc.getChildNodes();
                final int l = nodes.getLength();

                final Collection<T> vals = getTypeConstructor(dynType).construct();
                if (vals instanceof ArrayList) {
                    ((ArrayList<T>) vals).ensureCapacity(l);
                }

                for (int i = 0; i < l; ++i) {
                    final Node node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        final Element elem = (Element) node;
                        if (elem.getNodeName().equals(entryElemName())) {
                            vals.add(elemCodec.decode(dynElemType, elem));
                        }
                    }
                }

                return vals;
            }
        };
    }

    @Override
    public <T> Codec<T[], Element> objectArrayCodec(Class<T> elemType, Codec<T, Element> elemCodec) {
        return new Codec<T[], Element>() {
            @Override
            public Element encode(T[] vals, Element enc) throws Exception {
                for (T val : vals) {
                    elemCodec.encode(val, addEntryElement(enc));
                }

                return enc;
            }

            @Override
            public T[] decode(Class<T[]> dynType, Element enc) throws Exception {
                final Class<T> dynElemType = (Class<T>)dynType.getComponentType();

                final NodeList nodes = enc.getChildNodes();
                final int l = nodes.getLength();

                final T[] vals = (T[]) Array.newInstance(elemType, l);

                for (int i = 0; i < l; ++i) {
                    final Node node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        final Element elem = (Element) node;
                        if (elem.getNodeName().equals(entryElemName())) {
                            vals[i] = elemCodec.decode(dynElemType, elem);
                        }
                    }
                }

                return vals;
            }
        };
    }

    @Override
    public <T> Codec<T, Element> dynamicCodec(Class<T> stcType) {
        return new Codec<T, Element>() {
            @Override
            public Element encode(T val, Element enc) throws Exception {
                final Class<? extends T> dynType = (Class<? extends T>)val.getClass();
                if (!dynType.equals(stcType)) {
                    XmlUtils.setAttrValue(enc, typeAttrName(), classToName(dynType));
                }
                return encode2(XmlCodecCoreImpl.this.getNullUnsafeCodec(dynType), val, enc);
            }

            protected <S extends T> Element encode2(Codec<S, Element> codec, T val, Element enc) throws Exception {
                return codec.encode((S)val, enc);
            }

            @Override
            public T decode(Element enc) throws Exception {
                final String typeName = XmlUtils.getAttrValue(enc, typeAttrName());
                final Class<T> dynType;
                if (typeName.isEmpty()) {
                    dynType = stcType;
                } else {
                    dynType = nameToClass(typeName);
                }

                final Codec<T, Element> codec = XmlCodecCoreImpl.this.getNullUnsafeCodec(dynType);
                return codec.decode(dynType, enc);
            }
        };
    }

    @Override
    public <T> Codec<T, Element> dynamicCodec(Codec<T, Element> codec, Class<T> stcType) {
        return new Codec<T, Element>() {
            @Override
            public Element encode(T val, Element enc) throws Exception {
                final Class<? extends T> dynType = (Class<? extends T>)val.getClass();
                if (!dynType.equals(stcType)) {
                    XmlUtils.setAttrValue(enc, typeAttrName(), classToName(dynType));
                }
                return codec.encode(val, enc);
            }

            @Override
            public T decode(Element enc) throws Exception {
                final String typeName = XmlUtils.getAttrValue(enc, typeAttrName());
                final Class<T> dynType;
                if (typeName.isEmpty()) {
                    dynType = stcType;
                } else {
                    dynType = nameToClass(typeName);
                }

                return codec.decode(dynType, enc);
            }
        };
    }

    @Override
    public <T, RA extends ObjectMeta.ResultAccumlator<T>> Codec<T, Element> createObjectCodec(ObjectMeta<T, Element, RA> objMeta) {
        return new Codec<T, Element>() {
            @Override
            public Element encode(T val, Element enc) throws Exception {
                for (ObjectMeta.Field<T, Element, RA> field : objMeta) {
                    field.encodeField(val, XmlUtils.addElement(enc, field.name()));
                }
                return enc;
            }

            @Override
            public T decode(Class<T> dynType, Element enc) throws Exception {
                return FoldsGenEx.foldLeft(
                        (acc, field) -> field.decodeField(acc, XmlUtils.firstChildElement(enc, field.name())),
                        objMeta.startDecode(dynType),
                        objMeta
                ).construct();
            }
        };
    }

    @Override
    public String getFieldName(Field field, int depth, Set<String> existingNames) {
        String name = field.getName();
        while (existingNames.contains(name)) {
            name = "_" + name;
        }
        return name;
    }
}
