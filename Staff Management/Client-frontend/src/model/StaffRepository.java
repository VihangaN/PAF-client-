package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

public class StaffRepository {

	Connection con = null;

	public StaffRepository() {

		String db = "jdbc:mysql://localhost:3306/healthcare?serverTimezone=UTC";
		String username = "root";
		String password = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, username, password);
		} catch (Exception e) {
			System.out.println("Connection " + e);
		}

	}

	public String getstaff() {
		String output = "";
		output = "<table class=\"table\">" + "<thead>" + "<tr>" + "<th> Id</th>" + "<th> Name</th>" + "<th>Age</th>"
				+ "<th>Gender</th>" + "<th>NiC</th>" + "<th>Address</th>" + "<th>Email</th>" + "<th>Type</th>"
				+ "<th>Update</th>" + "<th>Remove</th>" + "</tr>" + "</thead>";

		String sql = "SELECT * from staff";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Staff s = new Staff();
				String Id = rs.getString(1);
				String Name = rs.getString(2);
				String Age = String.valueOf(rs.getInt(3));
				String Gender = rs.getString(4);
				String Nic = rs.getString(5);
				String Address = rs.getString(6);
				String Email = rs.getString(7);
				boolean Type = rs.getBoolean(8);
				String staffType;

				if (Type) {
					staffType = "Medical staff";
				} else {
					staffType = "none medical staff";
				}
				output += "<tr><td><input id=\"hidstaffId\" value=\"" + Id + "\" name=\"hidstaffId\" type=\"hidden\"> "
						+ Id + " </td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + Age + "</td>";
				output += "<td>" + Gender + "</td>";
				output += "<td>" + Nic + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td> <input id=\"hidstafftype\" value=\"" + Type
						+ "\" name=\"hidstafftype\" type=\"hidden\">" + staffType + "</td>";

				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btnUpdate btn btn-secondary\"></td>"
						+ "<td><button name=\"btnRemove\" data-appid='" + Id + "'type=\"submit\" value=\"" + Id
						+ "\"class=\"btnRemove btn btn-danger\">Remove</button></td></tr>";

			}
		} catch (Exception e) {
			System.out.println("Get staff" + e);
		}

		return output;

	}

	public Staff getMember(int id) {

		String sql = "SELECT * from staff WHERE id=" + id;

		Staff s = new Staff();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {

				s.seteId(rs.getString(1));
				s.setName(rs.getString(2));
				s.setAge(rs.getInt(3));
				s.setGender(rs.getString(4));
				s.setNic(rs.getString(5));
				s.setAddress(rs.getString(6));
				s.setEmail(rs.getString(7));
				s.setType(rs.getBoolean(8));

			}
		} catch (Exception e) {
			System.out.println("Get member" + e);
		}
		return s;
	}

	public String createMember(Staff s1) {
		String output = "";
		JsonObject jsonObject = new JsonObject();
		String sql = "insert into staff (name,age,gender,nic,Address,email,type) values (?,?,?,?,?,?,?)";
		if (con == null) {
			return "Error while connecting to the database for readline";
		}

		try {
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, s1.getName());
			st.setInt(2, s1.getAge());
			st.setString(3, s1.getGender());
			st.setString(4, s1.getNic());
			st.setString(5, s1.getAddress());
			st.setString(6, s1.getEmail());
			st.setBoolean(7, s1.isType());

			st.executeUpdate();
			String newRead = getstaff();
			jsonObject.addProperty("status", "success");
			jsonObject.addProperty("data", newRead);
		} catch (SQLException e) {
			System.out.println("Creatememeber " + e);
			jsonObject.addProperty("status", "error");
			jsonObject.addProperty("data", e.getMessage());
		} catch (Exception e) {
			System.out.println("Creatememeber " + e);
			jsonObject.addProperty("status", "error");
			jsonObject.addProperty("data", e.getMessage());
		}

		return jsonObject.toString();
	}

	public String updateMember(Staff s1) {
		String sql = "update staff set name=?,age=?,gender=?,nic=?,Address=?,email=?,type=? WHERE id=?";
		String output = "";
		JsonObject jsonObject = new JsonObject();
		if (con == null) {
			return "Error while connecting to the database for readline";
		}
		try {
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, s1.getName());
			st.setInt(2, s1.getAge());
			st.setString(3, s1.getGender());
			st.setString(4, s1.getNic());
			st.setString(5, s1.getAddress());
			st.setString(6, s1.getEmail());
			st.setBoolean(7, s1.isType());
			st.setString(8, s1.geteId());

			st.executeUpdate();

			String newRead = getstaff();
			jsonObject.addProperty("status", "success");
			jsonObject.addProperty("data", newRead);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			jsonObject.addProperty("status", "error");
			jsonObject.addProperty("data", e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			jsonObject.addProperty("status", "error");
			jsonObject.addProperty("data", e.getMessage());
		}

		return jsonObject.toString();
	}

	public String deleteMember(int id) {
		String sql = "DELETE from staff WHERE id=?";
		String output = "";
		JsonObject jsonObject = new JsonObject();

		if (con == null) {
			return "Error while connecting to the database for readline";
		}
		try {

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();

			output = "Deleted successfully";

			jsonObject.addProperty("status", "success");
			jsonObject.addProperty("data", getstaff());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			jsonObject.addProperty("status", "error");
			jsonObject.addProperty("data", e.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			jsonObject.addProperty("status", "error");
			jsonObject.addProperty("data", e.getMessage());
		}

		return jsonObject.toString();
	}

}
