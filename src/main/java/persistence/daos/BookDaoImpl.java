package persistence.daos;

import entitites.BookEntity;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookDaoImpl implements BookDao {
    private final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private final Connection connection = connectionManager.getConnection();

    @Override
    public BookEntity save(BookEntity entity) {
        final String SQL = "INSERT INTO books (title, price) VALUE (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDouble(2, entity.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Optional<BookEntity> findById(Integer id) {
        final String SQL = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer bookId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    Double price = resultSet.getDouble(3);
                    BookEntity bookEntity = new BookEntity(bookId, title, price);
                    return Optional.of(bookEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<BookEntity> findAll() {
        List<BookEntity> bookEntityList = new ArrayList<>();
        final String SQL = "SELECT * FROM books;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer bookId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    Double price = resultSet.getDouble(3);
                    BookEntity bookEntity = new BookEntity(bookId, title, price);
                    bookEntityList.add(bookEntity);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connectionManager.close();
        }
        return bookEntityList;
    }

    @Override
    public Optional<BookEntity> update(BookEntity entity, Integer id) {
        final String SQL = "UPDATE books SET title = ? , price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDouble(2, entity.getPrice());
            preparedStatement.setInt(3, id);
            int n = preparedStatement.executeUpdate();
            if (n > 0) {
                return Optional.of(entity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            connectionManager.close();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        final String SQL = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            connectionManager.close();
        }
    }

    @Override
    public List<BookEntity> findAllBy(Predicate<BookEntity> predicate) {
        List<BookEntity> bookEntityList = findAll();
        bookEntityList = bookEntityList.stream().filter(predicate).collect(Collectors.toList());
        return bookEntityList;
    }
}
