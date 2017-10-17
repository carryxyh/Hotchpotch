package com.iydsj.sw.common.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.iydsj.sw.common.dto.OrderDirection;
import com.iydsj.sw.common.dto.PageParam;

public class SqlBuilder {

    private StringBuilder sqlBuilder;

    private final static Logger LOGGER = Logger.getLogger(SqlBuilder.class);

    public SqlBuilder() {
        sqlBuilder = new StringBuilder();
    }

    public static SqlBuilder select(String table, String columns) {
        Assert.notNull(columns);
        Assert.notNull(table);
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(" select ")
                .append(columns)
                .append(" from ")
                .append(table)
                .append(" ");
        return sqlBuilder;
    }

    public static SqlBuilder select(String table, String columns, String where) {
        Assert.notNull(columns);
        Assert.notNull(table);
        SqlBuilder sqlBuilder = select(table, columns)
                .append(" where ")
                .append(where);
        return sqlBuilder;
    }

    public static SqlBuilder insert(String table, String columns, String values) {
        Assert.notNull(columns);
        Assert.notNull(table);
        Assert.notNull(values);
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(" insert into ")
                .append(table)
                .append(" ( ")
                .append(columns)
                .append(" ) ")
                .append(" values ")
                .append("( ")
                .append(values)
                .append(" ) ");
        return sqlBuilder;
    }

    public static SqlBuilder update(String table, String upsetColumns, String where) {
        Assert.notNull(table);
        Assert.notNull(upsetColumns);
        Assert.notNull(where);
        SqlBuilder sqlBuilder = update(table, upsetColumns)
                .append(" where ")
                .append(where);
        return sqlBuilder;
    }

    public static SqlBuilder update(String table, String upsetColumns) {
        Assert.notNull(table);
        Assert.notNull(upsetColumns);
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(" update ")
                .append(table)
                .append(" ")
                .append(" set ")
                .append(upsetColumns);
        return sqlBuilder;
    }

    public static SqlBuilder update(String table) {
        Assert.notNull(table);
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(" update ")
                .append(table)
                .append(" ");
        return sqlBuilder;
    }

    public static SqlBuilder delete(String table) {
        Assert.notNull(table);
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(" delete from ").append(table).append(" ");
        return sqlBuilder;
    }

    public SqlBuilder append(String sql) {
        Assert.notNull(sql);
        sqlBuilder.append(sql);
        return this;
    }

    public <T> SqlBuilder in(String column, List<T> objs) {
        Assert.notEmpty(objs);
        this.and().append(column);
        this.append(" in ( ");
        for (int index = 0; index < objs.size(); index++) {
            if (index != 0) {
                this.append(",");
            }
            sqlBuilder.append(objs.get(index));
        }
        this.append(" ) ");
        return this;
    }

    public <T> SqlBuilder notIn(String column, List<T> objs) {
        Assert.notEmpty(objs);
        this.and().append(column);
        this.append(" not in ( ");
        for (int index = 0; index < objs.size(); index++) {
            if (index != 0) {
                this.append(",");
            }
            sqlBuilder.append(objs.get(index));
        }
        this.append(" ) ");
        return this;
    }

    public SqlBuilder and(String sql) {
        this.append(" and ");
        this.append(sql);
        return this;
    }

    public SqlBuilder and() {
        this.append(" and ");
        return this;
    }

    public SqlBuilder where(String sql) {
        this.append(" where ");
        this.append(sql);
        return this;
    }

    public SqlBuilder where() {
        this.append(" where 1=1 ");
        return this;
    }

    public SqlBuilder asc(String... columns) {
        this.append(" order by ");
        for (int index = 0; index < columns.length; index++) {
            if (index != 0) {
                this.append(",");
            }
            this.append(columns[index]);
        }
        return this;
    }

    public SqlBuilder desc(String... columns) {
        this.append(" order by ");
        for (int index = 0; index < columns.length; index++) {
            if (index != 0) {
                this.append(",");
            }
            this.append(columns[index]);
            this.append(" desc ");
        }
        return this;
    }

    public SqlBuilder orderBy(String orderStr) {
        this.append(" order by ");
        this.append(orderStr);
        return this;
    }

    public SqlBuilder deleted(boolean isDeleted) {
        this.and(" IsDeleted=");
        if (!isDeleted) {
            this.append("0");
        } else {
            this.append("1");
        }
        return this;
    }

    public SqlBuilder set(List<String> items) {
        this.append(" set ");
        for (int index = 0; index < items.size(); index++) {
            if (index == 0) {
                this.append(items.get(index));
            } else {
                this.append(",").append(items.get(index));
            }
        }
        this.append(" ");
        return this;
    }

    public SqlBuilder set(String item) {
        this.append(" set ");
        this.append(item).append(" ");
        return this;
    }

    public SqlBuilder like(String column, String keyWord) {
        if (StringUtils.isNotBlank(keyWord)) {
            this.append(" ").append(column).append(" like '%").append(keyWord).append("%'");
        }
        return this;
    }

    public SqlBuilder rightlike(String column, String keyWord) {
        this.append(column).append(" like '%").append(keyWord).append("'");
        return this;
    }

    public SqlBuilder leftlike(String column, String keyWord) {
        this.append(column).append(" like '").append(keyWord).append("%'");
        return this;
    }


    public SqlBuilder or() {
        this.append(" or ");
        return this;
    }

    public SqlBuilder or(String item) {
        this.append(" or ").append(item).append(" ");
        return this;
    }

    public SqlBuilder as(String table) {
        this.append(" as ").append(table);
        return this;
    }

    public SqlBuilder leftJoin(String table) {
        this.append(" left join ").append(table);
        return this;
    }

    public SqlBuilder rightJoin(String table) {
        this.append(" right join ").append(table);
        return this;
    }

    public SqlBuilder innerJoin(String table) {
        this.append(" inner join ").append(table);
        return this;
    }

    public SqlBuilder on(String sql) {
        this.append(" on ").append(sql);
        return this;
    }

    public SqlBuilder page(PageParam pageParam) {
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            if (pageParam.getOrderDirection() == OrderDirection.ASC) {
                this.asc(pageParam.getOrderBy());
            } else {
                this.desc(pageParam.getOrderBy());
            }
        }
        int startIndex = 0;
        if (pageParam.getPageIndex() != 0) {
            startIndex = (pageParam.getPageIndex() - 1) * pageParam.getPageSize();
        } else {
            if (pageParam.getStartIndex() != 0) {
                startIndex = pageParam.getStartIndex();
            }
        }
        this.append(" limit ").append(startIndex + "").append(",").append(pageParam.getPageSize() + "");
        return this;
    }

    public SqlBuilder limit(int count) {
        this.append(" limit ");
        this.sqlBuilder.append(count);
        return this;
    }

    public SqlBuilder limit(int startIndex, int count) {
        this.append(" limit ");
        this.sqlBuilder.append(startIndex);
        this.sqlBuilder.append(",");
        this.sqlBuilder.append(count);
        return this;
    }

    public SqlBuilder groupBy(String column) {
        this.append(" ").append("group by ").append(column);
        return this;
    }

    public SqlBuilder groupBy(String... columns) {
        this.append(" ").append("group by ");
        for (int index = 0; index < columns.length; index++) {
            if (index != 0) {
                this.append(",");
            }
            this.append(columns[index]);
        }
        return this;
    }

    public SqlBuilder unionAll(List<SqlBuilder> sqlBuilders) {
        for (int i = 0; i < sqlBuilders.size(); i++) {
            if (i < sqlBuilders.size() - 1) {
                this.append("\n (").append(sqlBuilders.get(i).toString()).append(") \n").append("union all");
            } else {
                this.append("\n (").append(sqlBuilders.get(i).toString()).append(") \n");
            }
        }
        return this;
    }

    @Override
    public String toString() {
        String sql = sqlBuilder.toString();
        LOGGER.debug("SQL:" + sql);
        return sql;
    }
}
