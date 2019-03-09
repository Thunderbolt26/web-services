package service;

import java.util.ArrayList;
import java.util.List;

import static service.QueryBuilder.Filter.EQUAL;

public class QueryBuilder {
    private String tableName;
    private List<String> fields = new ArrayList<>();
    private List<Filter> filters = new ArrayList<>();

    public QueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    public class Filter {
        final String field;
        final String value;
        static final String EQUAL = " = ";

        public Filter(String field, String value) {
            this.field = field;
            this.value = value;
        }
    }

    public QueryBuilder addParam(String param) {
        fields.add(param);
        return this;
    }

    public QueryBuilder addFilter(String field, String value) {
        filters.add(new Filter(field, "'" + value + "'"));
        return this;
    }

    public QueryBuilder addFilter(String field, Integer value) {
        filters.add(new Filter(field, String.valueOf(value)));
        return this;
    }

    public String query() {
        StringBuilder builder = new StringBuilder();
        builder.append("select ");
        if (fields.size() == 0) builder.append("* ");
        else {
            int i = 0;
            for (String field : fields) {
                if (i != 0) builder.append(", ");
                else {
                    i = 1;
                }
                builder.append(field).append(' ');
            }
        }
        builder.append("from ").append(tableName);
        int i = 0;
        for (Filter filter : filters) {
            if (i == 0) {
                builder.append(" where ");
                i = 1;
            } else builder.append(" and ");
            builder.append(filter.field).append(EQUAL).append(filter.value);
        }
        return builder.toString();
    }
}
