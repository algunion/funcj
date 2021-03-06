package org.typemeta.funcj.json.model;

import org.junit.Test;
import org.typemeta.funcj.control.Try;
import org.typemeta.funcj.data.*;
import org.typemeta.funcj.json.JsonParser;
import org.typemeta.funcj.parser.*;
import org.typemeta.funcj.tuples.Tuple2;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static org.junit.Assert.*;

public class JsonParserTest {
    private static final String test0 = "[null]";

    private static final String test1 = "[true]";

    private static final String test2 = "[12]";

    private static final String test3 = "[]";

    private static final String test4 = "[null,true,0,1.2,\"test\"]";

    private static final String test5 = "{}";

    private static final String test6 = "{\"key\":[1.1,\"value\",true,null]}";

    private static final String test7 = "[\"A\\uabcdB\"]";

    private static final String test8 = "[\"\\u0123\"]";

    private static final String test9 = "[\"\\ucafe\\ubabe\\uab98\\ufcde\\ubcda\\uef4a\\b\\f\\n\\r\\t\"]";

    private static final String test10 = "{\"ids\":[1.2,\"test\",null,true]}";

    private static final String[] tests = {
        test0, test1, test2, test3, test4, test5, test6, test7, test8, test9, test10
    };

    @Test
    public void testRoundTrip() {
        Arrays.stream(tests).forEach(JsonParserTest::roundTrip);
    }

    private static void roundTrip(String json) {
        //System.out.println(" Parsing: " + json);
        final JsValue node = JsonParser.parse(json).getOrThrow();

        final String json2 = node.toString();
        //System.out.println(json2);

        assertEquals("Round-tripped JSON", json, json2);
    }

    private static final String json =
        FileUtils.openResource("/example.json")
            .map(FileUtils::read)
            .get();

    @Test
    public void testSuccessParse() throws IOException {
        final Result<Chr, JsValue> result =
            JsonParser.parser.parse(
                Input.of(FileUtils.openResource("/example.json").get()));
        final JsValue node = result.getOrThrow();
        final String json2 = node.toString(100);

        //System.out.println(node.toString());
        assertEquals("Round-tripped JSON", json, json2);
    }

    @Test
    public void testJsonSuite() {
        FileUtils.openDir("json")
                .get()
                .forEach(t2 -> t2.map2(FileUtils::read)
                        .applyFrom(FileUtils::roundTripJson));
    }
}

abstract class FileUtils {

    static Try<BufferedReader> openResource(String name) {
        return Try.of(() -> {
            final InputStream is =
                    Optional.ofNullable(FileUtils.class.getResourceAsStream(name))
                            .orElseThrow(() -> new RuntimeException("File '" + name + "' not found"));
            return new BufferedReader(new InputStreamReader(is));
        });
    }

    static Try<Stream<Tuple2<String, BufferedReader>>> openDir(String name) {
        final String dir = "/" + name + "/";

        return Try.sequence(
                openResource(dir)
                        .get()
                        .lines()
                        .map(file -> openResource(dir + file)
                                .map(br -> Tuple2.of(file, br))));
    }

    static String read(BufferedReader rdr) {
        return rdr.lines().collect(Collectors.joining("\n"));
    }

    static Unit roundTripJson(String name, String json) {
        final Result<Chr, JsValue> result = JsonParser.parser.parse(Input.of(json));
        //System.out.println(name + " : " + result.isSuccess());
        if (!result.isSuccess())
            //System.out.println(result);

        if (name.startsWith("fail")) {
            assertFalse("Parse expected to fail: " + name, result.isSuccess());
        } else {
            assertTrue("Parse expected to succeed: " + name, result.isSuccess());

            final JsValue node = result.getOrThrow();
            final String json2 = node.toString(100);

            final JsValue node2 = JsonParser.parser.parse(Input.of(json)).getOrThrow();

            //System.out.println(node.toString());
            assertEquals("Round-tripped JSON: " + name, node, node2);
        }

        return Unit.UNIT;
    }
}
