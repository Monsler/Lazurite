package com.kingmang.lazurite.libraries.io;

import com.kingmang.lazurite.core.Arguments;
import com.kingmang.lazurite.core.Function;
import com.kingmang.lazurite.exceptions.LzrException;
import com.kingmang.lazurite.runtime.values.LzrNumber;
import com.kingmang.lazurite.runtime.values.LzrValue;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LzrBufferedInputStream {

    private static BufferedInputStream bufferedInputStream;

    static class newBufferedInputStream implements Function {

        @Override
        public LzrValue execute(LzrValue... args) {
            Arguments.check(1, 1);
            bufferedInputStream = new BufferedInputStream((InputStream) args[0]);
            return LzrNumber.ZERO;
        }
    }

    static class read implements Function {

        @Override
        public LzrValue execute(LzrValue... args) {
            Arguments.check(1, 1);
            try {
                bufferedInputStream.read();
            } catch (IOException e) {
                throw new LzrException("RuntimeException: ", e.getMessage());
            }
            return LzrNumber.ZERO;
        }
    }
}
