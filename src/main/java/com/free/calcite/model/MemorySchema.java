package com.free.calcite.model;

import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.util.HashMap;
import java.util.Map;

public class MemorySchema extends AbstractSchema {
    private String dbName;

    public MemorySchema(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public Map<String, Table> getTableMap() {
        Map<String, Table> tableList = new HashMap<>();
        MemoryData.DataBase dataBase = MemoryData.gDBMap.get(this.dbName);
        if (dataBase == null) {
            return tableList;
        } else {
            for (MemoryData.Table table: dataBase.tableList) {
                tableList.put(table.tableName, new MemoryTable(table));
            }
            return tableList;
        }
    }

}
