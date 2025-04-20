package com.hexaware.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.hexaware.util.DBConnetion;
import com.mysql.cj.protocol.ExportControlled;
import com.hexaware.entity.Asset;

public class AssetManagementServiceImpl implements AssetManagementService  {

	static Connection connection;

	public AssetManagementServiceImpl(){
			try{
				connection = DBConnetion.getConnection();
			}catch(Exception e){
				System.out.println("Error in making connection " + e.getMessage());
			}
	}

	@Override
	public boolean addAsset(Asset asset) {

		boolean addAssetStatus = false;

		String query = "INSERT INTO assets(name,type,serial_number,purchase_date,location,status,owner_id) VALUES" + "\n" +
		"(?,?,?,?,?,?,?)";

		try{
			PreparedStatement pstm = connection.prepareStatement(query);

			pstm.setString(1, asset.getAssetName());
			pstm.setString(2, asset.getType());
			pstm.setString(3, asset.getSerialNumber());
			pstm.setString(4, asset.getPurchaseDate());
			pstm.setString(5, asset.getLocation());
			pstm.setString(6, asset.getStatus());
			pstm.setInt(7, asset.getOwnerId());

			int rowsAffected = pstm.executeUpdate();

			if(rowsAffected > 0){
				addAssetStatus = true;
			}

		}catch(Exception e){
			System.out.println("There was a problem in adding the asset " + e.getMessage());
		}

		return addAssetStatus;
	}

	@Override
	public boolean updateAsset(Asset asset) {

		boolean updated = false;

		String query = "UPDATE assets SET" + "\n" +
		"name = ?,type = ?,serial_number = ?,purchase_date = ?,location = ?,status = ?,owner_id = ?" + "\n" +
		"WHERE asset_id = ?";

		try{
			PreparedStatement pst = connection.prepareStatement(query);

			pst.setString(1, asset.getAssetName());
			pst.setString(2, asset.getType());
			pst.setString(3, asset.getSerialNumber());
			pst.setString(4, asset.getPurchaseDate());
			pst.setString(5, asset.getLocation());
			pst.setString(6, asset.getStatus());
			pst.setInt(7, asset.getOwnerId());
			pst.setInt(8, asset.getAssetId());

			int rowsUpdated = pst.executeUpdate();

			if(rowsUpdated > 0){
				updated = true;
			}

		}catch(Exception e){
			System.out.println("Error in updating the asset");
		}

		return updated;
	}

	@Override
	public boolean deleteAsset(int assetId) {

		boolean deleted = false;

		String query = "DELETE FROM assets WHERE assetId = ?";

		try{
			PreparedStatement pstm = connection.prepareStatement(query);

			pstm.setInt(1, assetId);

			int rowsDeleted = pstm.executeUpdate();

			if(rowsDeleted > 0){
				deleted = true;
			}
		}catch(Exception e){
			System.out.println("Error in deleting asset");
		}

		return deleted;
	}

	@Override
	public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {

		boolean allocateAsset = false;

		String query = "INSERT INTO asset_allocations(asset_id,employee_id,allocation_date,return_date)" + "\n" +
		"VALUES(?,?,?,?)";

		LocalDate localDate = LocalDate.parse(allocationDate);

		try{
			PreparedStatement pst = connection.prepareStatement(query);

			pst.setInt(1, assetId);
			pst.setInt(2, employeeId);
			pst.setString(3, allocationDate);
			pst.setDate(4, Date.valueOf(localDate.plusDays(10)));

			int rowsInserted = pst.executeUpdate();

			if(rowsInserted > 0){
				allocateAsset = true;
			}
		}catch(Exception e){
			System.out.println("Error in allocating resources");
		}

		return allocateAsset;
	}

	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {

		boolean deAllocated = false;

		String query = "DELETE FROM asset_allocation WHERE return_date = ?";

		try{
			PreparedStatement pst = connection.prepareStatement(query);

			pst.setString(1, returnDate);

			int removed = pst.executeUpdate();

			if(removed > 0){
				deAllocated = true;
			}
		}catch(Exception e){
			System.out.println("Error in deallocating asset");
		}

		return deAllocated;
	}

	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
		String query = "INSERT INTO maintenance_records (asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?)";

		try{ // assume you have a DBConnection class
			PreparedStatement pst = connection.prepareStatement(query);

			// Convert the String to java.sql.Date

			pst.setInt(1, assetId);
			pst.setString(2, maintenanceDate);
			pst.setString(3, description);
			pst.setDouble(4, cost);

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException | DateTimeParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
		String query = "INSERT INTO reservations (asset_id, employee_id, reservation_date, start_date, end_date, status) " +
		"VALUES (?, ?, ?, ?, ?, ?)";

		try{
		PreparedStatement pst = connection.prepareStatement(query);

		pst.setInt(1, assetId);
		pst.setInt(2, employeeId);
		pst.setString(3, reservationDate); // Format: "yyyy-MM-dd"
		pst.setString(4, startDate);       // Format: "yyyy-MM-dd"
		pst.setString(5, endDate);         // Format: "yyyy-MM-dd"
		pst.setString(6, "Reserved");      // Default status for new reservation

		int rows = pst.executeUpdate();
		return rows > 0;

		} catch (SQLException e) {
		e.printStackTrace();
		return false;
		}
	}

	@Override
	public boolean withdrawReservation(int reservationId) {
		String query = "DELETE FROM reservations WHERE reservation_id = ?";

		try {
			 PreparedStatement pst = connection.prepareStatement(query);
	
			pst.setInt(1, reservationId);
	
			int rows = pst.executeUpdate();
			return rows > 0;
	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}


