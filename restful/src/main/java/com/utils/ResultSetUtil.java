package com.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class ResultSetUtil<T> {

    public List<T> dumpResultSet(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<T>();
        ResultSetMetaData metaData = rs.getMetaData();
        int colCount = metaData.getColumnCount();

        Field[] fields = clazz.getDeclaredFields();

        while (rs.next()) {
            T newInstance = clazz.newInstance();

            for (int i = 1; i <= colCount; i++) {
                try {
                    Object value = rs.getObject(i);
                    for (int j = 0; j < fields.length; j++) {
                        Field f = fields[j];
                        if (f.getName().equalsIgnoreCase(metaData.getColumnName(i).replaceAll("_", ""))) {
                            BeanUtils.copyProperties(newInstance, f.getName(), (Class<?>) value);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            list.add(newInstance);
        }

        return list;
    }
}
