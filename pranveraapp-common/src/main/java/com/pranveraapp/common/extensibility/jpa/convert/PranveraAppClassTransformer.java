package com.pranveraapp.common.extensibility.jpa.convert;

import javax.persistence.spi.ClassTransformer;
import java.util.Properties;

/**
 * Created by elion on 24/01/16.
 */
public interface PranveraAppClassTransformer extends ClassTransformer {

    public void compileJPAProperties(Properties prop,Object key) throws Exception;
}
