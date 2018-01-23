package com.free.calcite.model;


import org.apache.calcite.linq4j.Enumerator;

import java.math.BigDecimal;
import java.util.List;

public class MemoryEnumerator<E> implements Enumerator<E> {
    private List<List<String>> data = null;
    private int currentIndex = -1;
    private RowConverter<E> rowConvert;
    private List<String> columnTypes;


    abstract static class RowConverter<E> {
        abstract E convertRow(List<String> rows, List<String> columnType);
    };

    static class ArrayRowConverter extends RowConverter<Object[]> {
        private int[] fields;

        public ArrayRowConverter(int[] fields) {
            this.fields = fields;
        }

        @Override
        Object[] convertRow(List<String> rows, List<String> columnType) {
            int index = 0;
            Object[] objects = new Object[fields.length];
            for (int field: fields) {
                objects[index++] = convertOptiqCellValue(rows.get(field), columnType.get(field));
            }
            return objects;
        }
    };

    public MemoryEnumerator(int[] fields, List<String> types, List<List<String>> data) {
        this.data = data;
        this.columnTypes = types;
        rowConvert = (RowConverter<E>) new ArrayRowConverter(fields);
    }

    @Override
    public void close() {

    }

    @Override
    public E current() {
        List<String> line = data.get(currentIndex);
        return rowConvert.convertRow(line, this.columnTypes);
    }

    @Override
    public void reset() {

    }

    @Override
    public boolean moveNext() {
        return ++ currentIndex < data.size();
    }

    public static Object convertOptiqCellValue(String strValue, String dataType) {
        if (strValue == null)
            return null;

        if ((strValue.equals("") || strValue.equals("\\N")) && !dataType.equals("string"))
            return null;

        if ("date".equals(dataType)) {
        	return DateFormat.stringToDate(strValue);
        } else if ("short".equals(dataType) || "smallint".equals(dataType)) {
            return Short.valueOf(strValue);
        } else if ("integer".equals(dataType)) {
            return Integer.valueOf(strValue);
        } else if ("long".equals(dataType) || "bigint".equals(dataType)) {
            return Long.valueOf(strValue);
        } else if ("double".equals(dataType)) {
            return Double.valueOf(strValue);
        } else if ("decimal".equals(dataType)) {
            return new BigDecimal(strValue);
        } else if ("timestamp".equals(dataType)) {
            return Long.valueOf(DateFormat.stringToMillis(strValue));
        } else if ("float".equals(dataType)) {
            return Float.valueOf(strValue);
        } else if ("boolean".equals(dataType)) {
            return Boolean.valueOf(strValue);
        } else {
            return strValue;
        }
    }
}
