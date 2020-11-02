package ua.edu.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.edu.task.models.Position;
import ua.edu.task.models.Shape;

import javax.naming.Name;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShapeDaoImpl implements CrudDao<Shape> {

    private final String SQL_FIND_BY_NAME = "SELECT * FROM shape INNER JOIN position ON " +
            " shape.shape_id = position.shape_id WHERE shape.name = ? ";

    private final String SQL_FIND_ALL = "SELECT * FROM shape ";

    private final String SQL_ADD_SHAPE = "INSERT INTO shape(name) VALUES ( ? )";
    private final String SQL_FIND_ID =" SELECT MAX(shape_id) FROM shape ";
    private StringBuilder SqlAddPos =new StringBuilder("INSERT INTO position(x,y,shape_id) VALUES ");
    private final String SQL_FIND_ID_FOR_DELETE = "SELECT shape.shape_id FROM shape WHERE shape.name = ? ";
    private final String SQL_DELETE_FROM_POSITION = "DELETE FROM position WHERE position.shape_id  =  ? ";
    private final String SQL_DELETE_FROM_SHAPE = "DELETE FROM shape WHERE shape.name = ? AND shape.shape_id = ? ";


    @Autowired
    private DataSource dataSource;

    @Override
    public Shape find(String sname) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, sname);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Position> positions = new ArrayList<>();
            String name = "";
            Integer id = null;
            while (resultSet.next()){
                Integer x = resultSet.getInt("x");
                Integer y = resultSet.getInt("y");
                positions.add(new Position(x,y));
                id = resultSet.getInt("shape_id");
                name = resultSet.getString("name");
            }
            return new Shape(id,name,positions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Shape model) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_ADD_SHAPE);
             PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_FIND_ID);

            preparedStatement1.setString(1, model.getName());
            preparedStatement1.execute();


            ResultSet resultSet2 = preparedStatement2.executeQuery();
            Integer id = null;
            while (resultSet2.next()) {
                id = resultSet2.getInt("max");
            }

            List<Position> positions = model.getPositions();
            for (int i = 0; i < positions.size(); i++) {
                SqlAddPos.append("( " + positions.get(i).getX() + ",");
                SqlAddPos.append(positions.get(i).getY() + ",");
                SqlAddPos.append(id + ")");
                if (i == positions.size() - 1) {
                    SqlAddPos.append(";");
                } else SqlAddPos.append(",");
            }
            PreparedStatement preparedStatement3 = connection.prepareStatement(SqlAddPos + "");
            preparedStatement3.executeUpdate();

            SqlAddPos = new StringBuilder("INSERT INTO position(x,y,shape_id) VALUES ");

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
        @Override
    public void update(Shape model) {

    }

//    private final String SQL_FIND_ID_FOR_DELETE = "SELECT shape.shape_id FROM shape WHERE shape.name = ? ";
//    private final String SQL_DELETE_FROM_POSITION = "DELETE FROM position WHERE position.shape_id  =  ? ";
//    private final String SQL_DELETE_FROM_SHAPE = "DELETE FROM shape WHERE shape.name = ? AND shape.shape_id = ? ";

    @Override
    public void delete(String shapeName) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_FIND_ID_FOR_DELETE);
            preparedStatement1.setString(1, shapeName);
            ResultSet resultSet = preparedStatement1.executeQuery();
            Integer id = null;
            while (resultSet.next()){
                id = resultSet.getInt("shape_id");
            }

            PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_DELETE_FROM_POSITION);
            preparedStatement2.setInt(1,id);
            preparedStatement2.execute();

            PreparedStatement preparedStatement3 = connection.prepareStatement(SQL_DELETE_FROM_SHAPE);

            preparedStatement3.setString(1,shapeName);
            preparedStatement3.setInt(2,id);
            preparedStatement3.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Shape> findAll() {
        try (Connection connection = dataSource.getConnection()){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Shape> list = new ArrayList<>();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                Shape shape = new Shape(name);
                list.add(shape);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
