package product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class ProductDAOImplementation implements ProductDAO {

	@Override
	public int add(Product prod) throws SQLException, ClassNotFoundException {
		String SQL = "INSERT INTO product (title, price, product_quantity, is_visible) VALUES (?,?,?,true)";

		int success = 0;

		try (DatabaseConnection con = new DatabaseConnection();
				PreparedStatement ps = con.getConnection().prepareStatement(SQL)) {

			ps.setString(1, prod.getTitle());
			ps.setDouble(2, prod.getPrice());
			ps.setInt(3, prod.getQuantity());

			success = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public int update(Product prod) throws SQLException {
		String SQL = "UPDATE product SET title = ?, price = ?, product_quantity = ? WHERE product_id = ?";

		int success = 0;

		try (DatabaseConnection con = new DatabaseConnection();
				PreparedStatement ps = con.getConnection().prepareStatement(SQL)) {

			ps.setString(1, prod.getTitle());
			ps.setDouble(2, prod.getPrice());
			ps.setInt(3, prod.getQuantity());
			ps.setInt(4, prod.getProductId());

			success = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public int delete(Product prod) throws SQLException {

		String SQL = "UPDATE product SET is_visible = FALSE WHERE product_id = ?";

		int success = 0;

		try (DatabaseConnection con = new DatabaseConnection();
				PreparedStatement ps = con.getConnection().prepareStatement(SQL)) {

			ps.setInt(1, prod.getProductId());

			success = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public List<Product> getAll() throws SQLException, ClassNotFoundException, Exception {

		String SQL = "SELECT * FROM product  WHERE is_visible = true";
		List<Product> products = new ArrayList<>();
		try (DatabaseConnection connection = new DatabaseConnection();
				PreparedStatement statement = connection.getConnection().prepareStatement(SQL)) {

//		statement.setInt(1, limit);
//		statement.setInt(2, offset);

			if (statement.execute()) {
				try (ResultSet resultSet = statement.executeQuery()) {

					while (resultSet.next()) {
						Product product = new Product();

						product.setPrice(resultSet.getDouble("price"));
						product.setProductId(resultSet.getInt("product_id"));
						product.setQuantity(resultSet.getInt("product_quantity"));
						product.setTitle(resultSet.getString("title"));
						product.setIsVisible(resultSet.getBoolean("is_visible"));

						products.add(product);
					}
				}
			}
		}

		return products;
	}

	@Override
	public List<Product> getAll(Product type) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getByName(String title) throws SQLException {
		String SQL = "SELECT * FROM product WHERE is_visible = true AND title = ?";

		Product product = new Product();

		try (DatabaseConnection connection = new DatabaseConnection();
				PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL)) {

			preparedStatement.setString(1, title);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {

					product.setPrice(resultSet.getDouble("price"));
					product.setProductId(resultSet.getInt("product_id"));
					product.setQuantity(resultSet.getInt("product_quantity"));
					product.setTitle(resultSet.getString("title"));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return product;
	}

	@Override
	public Product getById(String prodId) throws SQLException {

		String SQL = "SELECT * FROM product WHERE product_id = ?";

		Product product = new Product();

		try (DatabaseConnection connection = new DatabaseConnection();
				PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL)) {

			preparedStatement.setInt(1, Integer.valueOf(prodId));

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {

					product.setPrice(resultSet.getDouble("price"));
					product.setProductId(resultSet.getInt("product_id"));
					product.setQuantity(resultSet.getInt("product_quantity"));
					product.setTitle(resultSet.getString("title"));
					product.setIsVisible(resultSet.getBoolean("is_visible"));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return product;
	}

}
