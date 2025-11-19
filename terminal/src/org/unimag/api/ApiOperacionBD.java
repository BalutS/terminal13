package org.unimag.api;

import java.util.List;

public interface ApiOperacionBD<T, ID> {

    public int getSerial();

    public T inserInto(T objeto, String ruta);

    public List<T> selectFrom();
    
    //son 30 points
//    public List<T> selectFromWhereActivos();

    public int numRows();

    public Boolean deleteFrom(ID codigo);

    public T updateSet(ID codigo, T objeto, String ruta);

    public T getOne(ID codigo);
}
