package org.typemeta.funcj.json.model;

import org.typemeta.funcj.functions.Functions;
import org.typemeta.funcj.json.algebra.JsonAlg;

/**
 * Models a JSON null value.
 */
public enum JSNull implements JSValue {
    NULL;

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public StringBuilder toString(StringBuilder sb) {
        return sb.append(toString());
    }

    @Override
    public <T> T match(
            Functions.F<JSNull, T> fNull,
            Functions.F<JSBool, T> fBool,
            Functions.F<JSNumber, T> fNum,
            Functions.F<JSString, T> fStr,
            Functions.F<JSArray, T> fArr,
            Functions.F<JSObject, T> fObj) {
        return fNull.apply(this);
    }

    @Override
    public <T> T apply(JsonAlg<T> alg) {
        return alg.nul();
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean isBool() {
        return false;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public boolean isString() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public JSNull asNull() {
        return this;
    }

    @Override
    public JSBool asBool() {
        throw Utils.boolTypeError(getClass());
    }

    @Override
    public JSNumber asNumber() {
        throw Utils.numberTypeError(getClass());
    }

    @Override
    public JSString asString() {
        throw Utils.stringTypeError(getClass());
    }

    @Override
    public JSArray asArray() {
        throw Utils.arrayTypeError(getClass());
    }

    @Override
    public JSObject asObject() {
        throw Utils.objectTypeError(getClass());
    }
}