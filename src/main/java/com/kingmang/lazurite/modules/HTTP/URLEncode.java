package com.kingmang.lazurite.modules.HTTP;

import com.kingmang.lazurite.lib.Arguments;
import com.kingmang.lazurite.lib.Function;
import com.kingmang.lazurite.runtime.StringValue;
import com.kingmang.lazurite.runtime.Value;
import java.io.IOException;
import java.net.URLEncoder;

public final class URLEncode implements Function {

    @Override
    public Value execute(Value... args) {
        Arguments.checkOrOr(1, 2, args.length);
        
        String charset = "UTF-8";
        if (args.length >= 2) {
            charset = args[1].asString();
        }
        
        try {
            final String result = URLEncoder.encode(args[0].asString(), charset);
            return new StringValue(result);
        } catch (IOException ex) {
            return args[0];
        }
    }
}