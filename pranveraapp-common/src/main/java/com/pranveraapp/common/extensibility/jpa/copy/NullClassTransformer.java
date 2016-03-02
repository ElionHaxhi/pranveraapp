package com.pranveraapp.common.extensibility.jpa.copy;

import com.pranveraapp.common.extensibility.jpa.convert.PranveraAppClassTransformer;

import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Properties;

/**
 * Created by elion on 24/01/16.
 */
public class NullClassTransformer implements PranveraAppClassTransformer{
     @Override
    public void compileJPAProperties(Properties props, Object key) throws Exception {
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        return null;
    }

}
