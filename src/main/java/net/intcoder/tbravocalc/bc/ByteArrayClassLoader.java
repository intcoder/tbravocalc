package net.intcoder.tbravocalc.bc;

import java.util.HashMap;
import java.util.Map;

public class ByteArrayClassLoader extends ClassLoader {
    private final Map<String, byte[]> extraClassDefs;
    private final Map<String, Class<?>> loadedClasses = new HashMap<>();

    public ByteArrayClassLoader(Map<String, byte[]> extraClassDefs) {
        this.extraClassDefs = new HashMap<String, byte[]>(extraClassDefs);
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        byte[] classBytes = this.extraClassDefs.remove(name);
        if (classBytes != null) {
            loadedClasses.put(name, defineClass(name, classBytes, 0, classBytes.length));
        }

        var class0 = loadedClasses.get(name);
        if (class0 != null) {
            return class0;
        } else {
            return super.findClass(name);
        }
    }

}