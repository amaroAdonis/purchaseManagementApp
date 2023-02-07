package parser;

import java.io.IOException;
import java.util.Collection;
import java.util.List;


public interface Reader <T> {

    void dataReader() throws IOException;
    void dataWriter(List<T> list) throws IOException;



}
