package com.free.calcite.model;

import org.apache.calcite.sql.type.SqlTypeName;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MemoryData {
    public static final Map<String, DataBase> gDBMap = new HashMap<String, DataBase>();
    public static final Map<String, SqlTypeName> gSQLTypeMap = new HashMap<>();
    public static final Map<String, Class> gClassTypeMap = new HashMap<>();

    public static class DataBase {
        public List<Table> tableList = new LinkedList<Table>();
    };

    public static class Table {
        public String tableName;
        public List<Column> columnList = new LinkedList<Column>();
        public List<List<String>> data = new LinkedList<>();
    }

    public static class Column {
        public String name;
        public String type;
    }

    static {

        initRowDataType();

        DataBase dataBase = new DataBase();

        Table studentTable = new Table();
        initStudentTable(studentTable);


        Table classTable = new Table();
        initClassTable(classTable);

        dataBase.tableList.add(studentTable);
        dataBase.tableList.add(classTable);

        gDBMap.put("school", dataBase);
    }

    public static void initRowDataType() {
        gSQLTypeMap.put("char", SqlTypeName.CHAR);
        gClassTypeMap.put("char", Character.class);

        gSQLTypeMap.put("varchar", SqlTypeName.ARRAY);
        gClassTypeMap.put("varchar", String.class);

        gSQLTypeMap.put("boolean", SqlTypeName.BOOLEAN);
        gClassTypeMap.put("boolean", Boolean.class);

        gSQLTypeMap.put("integer", SqlTypeName.INTEGER);
        gClassTypeMap.put("integer", Integer.class);

        gSQLTypeMap.put("date", SqlTypeName.DATE);
        gClassTypeMap.put("date", Date.class);

        gSQLTypeMap.put("decimal", SqlTypeName.DECIMAL);
        gClassTypeMap.put("decimal", BigDecimal.class);
    }

    public static void initStudentTable(Table student) {
        student.tableName = "Student";
        Column name = new Column();
        name.name = "name";
        name.type = "varchar";
        student.columnList.add(name);

        Column id = new Column();
        id.name = "id";
        id.type = "varchar";
        student.columnList.add(id);

        Column classId = new Column();
        classId.name = "classId";
        classId.type = "integer";
        student.columnList.add(classId);

        Column birth = new Column();
        birth.name = "birthday";
        birth.type = "date";
        student.columnList.add(birth);

        Column home = new Column();
        home.name = "home";
        home.type = "varchar";
        student.columnList.add(home);

        student.data.add(Arrays.asList("fengysh","A000001", "1", "1989-06-10", "anhui"));
        student.data.add(Arrays.asList("wyshz","A000002", "1", "1989-03-04", "henan"));
        student.data.add(Arrays.asList("hesk","A000003", "1", "1992-02-10", "anhui"));
        student.data.add(Arrays.asList("whst","A000004", "2", "1993-04-08", "hebei"));
        student.data.add(Arrays.asList("wush","B000005", "2", "1998-02-26", "beijing"));
        student.data.add(Arrays.asList("ehsn","C000006", "3", "1990-06-18", "sichuan"));
        student.data.add(Arrays.asList("wisyh","D000007", "3", "1991-03-06", "zhejiang"));
        student.data.add(Arrays.asList("helsj","D000008", "4", "1993-09-10", "jiangsu"));
    }

    public static void initClassTable(Table cl) {
        cl.tableName = "Class";
        Column name = new Column();
        name.name = "name";
        name.type = "varchar";
        cl.columnList.add(name);

        Column id = new Column();
        id.name = "id";
        id.type = "integer";
        cl.columnList.add(id);

        Column teacher = new Column();
        teacher.name = "teacher";
        teacher.type = "varchar";
        cl.columnList.add(teacher);

        cl.data.add(Arrays.asList("3-1", "1", "fengsu"));
        cl.data.add(Arrays.asList("3-2", "2", "sunshue"));
        cl.data.add(Arrays.asList("3-3", "3", "sunshdh"));
        cl.data.add(Arrays.asList("3-4", "4", "shwud"));
    }

}
