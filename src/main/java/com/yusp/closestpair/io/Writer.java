package com.yusp.closestpair.io;

import java.io.IOException;
import java.util.List;

public interface Writer {

    void write(String path, List<String> results) throws IOException;

}
