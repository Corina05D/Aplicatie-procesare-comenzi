package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append(" =?");
        return sb.toString();
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String v=null;
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            if(fieldName.contains("id")) v=fieldName;
        }
        String query = createSelectQuery(v);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ArrayList<String> getPrimaryKeys(Connection connection) {
        ResultSet rs;
        ArrayList<String> primaryKeys = new ArrayList<>();
        try {
            DatabaseMetaData meta = connection.getMetaData();
            rs = meta.getPrimaryKeys(null, null, type.getSimpleName());

            while (rs.next()) {
                String column_name = rs.getString("COLUMN_NAME");
                primaryKeys.add(column_name);
                System.out.println(column_name);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return primaryKeys;
    }

    private ArrayList<Field> getStatementFields(Connection connection) {
        ArrayList<Field> statementFields = new ArrayList<>();
        ArrayList<String> primaryKeys = getPrimaryKeys(connection);
        for (Field field : type.getDeclaredFields()) {
            boolean ok = true;
            for (String s : primaryKeys) {
                if (s.equals(field.getName())) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                statementFields.add(field);
            }
        }
        return statementFields;
    }

    private String createInsertQuery(Connection connection) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("INSERT ");
        sb1.append("INTO ");
        sb1.append(type.getSimpleName());
        sb1.append(" (");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(" VALUES (");

        String prefix = "";
        for (Field field : getStatementFields(connection)) {
            sb1.append(prefix);
            sb2.append(prefix);
            prefix = ",";
            sb1.append(field.getName());
            sb2.append("?");
        }
        sb1.append(")");
        sb2.append(")");
        sb1.append(sb2);
        return sb1.toString();
    }

    private PreparedStatement prepareUpdateStatement(Connection connection, String query, T t) throws SQLException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        for (Field field : getStatementFields(connection)) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
            Method method = propertyDescriptor.getReadMethod();
            statement.setObject(i++, method.invoke(t));
        }
        return statement;
    }

    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int insertedID = -1;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createInsertQuery(connection);
            statement = prepareUpdateStatement(connection, query, t);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return insertedID;
    }

    private String createUpdateQuery(Connection connection) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        String prefix = "";
        for (Field field : getStatementFields(connection)) {
            sb.append(prefix);
            prefix = ",";
            sb.append(field.getName()).append(" = ?");
        }
        sb.append(" WHERE ");
        String v=null;
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            if(fieldName.contains("id")) v=fieldName;
        }
        sb.append(v).append(" = ?");
        return sb.toString();
    }

    public void update(int id, T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createUpdateQuery(connection);
            statement = prepareUpdateStatement(connection, query, t);
            statement.setInt(getStatementFields(connection).size() + 1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: update " + e.getMessage());
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    private String createDeleteQuery(Connection connection) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        String v=null;
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            if(fieldName.contains("id")) v=fieldName;
        }
        sb.append(v).append(" = ?");
        return sb.toString();
    }

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createDeleteQuery(connection);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public List<String> createTableHeader() {
        List<String> tableHeader = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            tableHeader.add(field.getName());
        }
        return tableHeader;
    }

    public List<List<Object>> createTableData() {
        List<List<Object>> tableData = new ArrayList<>();
        for (T t : findAll()) {
            List<Object> tableRow = new ArrayList<>();
            for (Field field : type.getDeclaredFields()) {
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    tableRow.add(method.invoke(t));
                } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                    e.printStackTrace();
                }
            }
            tableData.add(tableRow);
        }
        return tableData;
    }
}
