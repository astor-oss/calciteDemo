package com.free.calcite.model;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.util.Map;

public class MemorySchemaFactory implements SchemaFactory {
    @Override
    public Schema create(SchemaPlus schemaPlus, String s, Map<String, Object> map) {
        System.out.println("Input database Name is:" + s + ", input Args:");
        /*
        for (Map.Entry<String, Object> entry: map.entrySet()) {
            System.out.println("Args:" + entry.getKey() + ":" + (String)entry.getValue());
        }
        */
        System.out.println("param1 : " + map.get("param1"));
		System.out.println("param2 : " + map.get("param2"));
		System.out.println("Get database " + s);

        return new MemorySchema(s);
    }
}
