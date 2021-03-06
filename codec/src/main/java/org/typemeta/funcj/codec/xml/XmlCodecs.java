package org.typemeta.funcj.codec.xml;

import org.typemeta.funcj.codec.Codecs;
import org.w3c.dom.Element;

import java.util.Optional;

import static org.typemeta.funcj.util.Exceptions.*;

@SuppressWarnings("unchecked")
public class XmlCodecs {
    public static XmlCodecCoreImpl registerAll(XmlCodecCoreImpl core) {
        core.registerCodec(Optional.class, new OptionalCodec(core));
        return Codecs.registerAll(core);
    }

    public static class OptionalCodec<T> extends Codecs.CodecBase<Optional<T>, Element> {

        private static final String emptyAttrVal = "empty";
        private static final String presAttrVal = "present";

        private final String attrName;

        protected OptionalCodec(XmlCodecCoreImpl core) {
            super(core);
            attrName = core.metaAttrName();
        }

        @Override
        public Element encode(Optional<T> val, Element enc) throws Exception {
            return unwrap(() ->
                    val.map(
                            wrap(t -> {
                                    XmlUtils.setAttrValue(enc, attrName, presAttrVal);
                                    return core.dynamicCodec().encode(t, enc);
                            })::apply
                    ).orElseGet(() -> XmlUtils.setAttrValue(enc, attrName, emptyAttrVal))
            );
        }

        @Override
        public Optional<T> decode(Element enc) throws Exception {
            if (enc.getAttribute(attrName).equals(emptyAttrVal)) {
                return Optional.empty();
            } else {
                return Optional.of((T)core.dynamicCodec().decode(enc));
            }
        }
    }
}
