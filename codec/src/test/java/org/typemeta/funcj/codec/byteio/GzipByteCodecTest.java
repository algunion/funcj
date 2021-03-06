package org.typemeta.funcj.codec.byteio;

import org.junit.Assert;
import org.typemeta.funcj.codec.*;
import org.typemeta.funcj.util.Exceptions;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.*;
import java.io.*;
import java.util.zip.*;

public class GzipByteCodecTest extends TestBase {
    final static ByteCodecCore codec = Codecs.byteCodec();

    public static final DocumentBuilder docBuilder;

    static {
        docBuilder = Exceptions.wrap(
                () -> DocumentBuilderFactory.newInstance().newDocumentBuilder()
        );
    }
    static {
        codec.registerTypeConstructor(TestTypes.NoEmptyCtor.class, () -> TestTypes.NoEmptyCtor.create(false));
        registerCustomCodec(codec);
    }

    @Override
    protected <T> void roundTrip(T val, Class<T> clazz) throws Exception {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final GZIPOutputStream gzos = new GZIPOutputStream(baos);
        final ByteIO byteOut = ByteIO.ofOutputStream(gzos);
        codec.encode(clazz, val, byteOut);
        gzos.close();

        final byte[] ba = baos.toByteArray();
        final ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        final GZIPInputStream gzis = new GZIPInputStream(bais);
        final ByteIO byteIn = ByteIO.ofInputStream(gzis);
        final T val2 = codec.decode(clazz, byteIn);

        if (printData || !val.equals(val2)) {
            System.out.println(DatatypeConverter.printHexBinary(ba));
        }

        Assert.assertEquals(val, val2);
    }
}
