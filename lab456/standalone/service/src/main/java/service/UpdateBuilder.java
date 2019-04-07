package service;

import java.util.ArrayList;
import java.util.List;

import static service.UpdateBuilder.Pair.EQUAL;

public class UpdateBuilder {
    private String tableName;
    private String returnField;
    private List<Pair> setters = new ArrayList<>();
    private List<Pair> filters = new ArrayList<>();

    public UpdateBuilder(String tableName) {
        this.tableName = tableName;
        returnField = null;
    }

    public UpdateBuilder(String tableName, String returnField) {
        this.tableName = tableName;
        this.returnField = returnField;
    }


    public class Pair {
        final String field;
        final String value;
        static final String EQUAL = " = ";

        public Pair(String field, String value) {
            this.field = field;
            this.value = value;
        }
    }

    public UpdateBuilder addSetter(String field, String value) {
        setters.add(new Pair(field, "'" + value + "'"));
        return this;
    }

    public UpdateBuilder addSetter(String field, Integer value) {
        setters.add(new Pair(field, String.valueOf(value)));
        return this;
    }

    public UpdateBuilder addFilter(String field, String value) {
        filters.add(new Pair(field, "'" + value + "'"));
        return this;
    }

    public UpdateBuilder addFilter(String field, Integer value) {

        filters.add(new Pair(field, String.valueOf(value)));
        return this;
    }

    public String query() {
        StringBuilder builder = new StringBuilder();
        if (setters.size() == 0) return null;
        builder.append("update ").append(tableName).append(" set ");

        int i = 0;
        for (Pair setter : setters) {
            if (i != 0) builder.append(", ");
            else {
                i = 1;
            }
            builder.append(setter.field).append(EQUAL).append(setter.value);
        }
        i = 0;
        for (Pair filter : filters) {
            if (i == 0) {
                builder.append(" where ");
                i = 1;
            } else builder.append(" and ");
            builder.append(filter.field).append(EQUAL).append(filter.value);
        }
        if(returnField != null)
            builder.append(" RETURNING ").append(returnField);
        return builder.toString();
    }
}
