import java.util.Scanner;

import com.hexaware.dao.AssetManagementServiceImpl;
import com.hexaware.entity.Asset;

public class AssetManagementApp {

    public static void assetInput(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Asset Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Asset Type: ");
        String type = scanner.nextLine();

        System.out.print("Enter Serial Number: ");
        String serialNumber = scanner.nextLine();

        System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
        String purchaseDate = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Status: ");
        String status = scanner.nextLine();

        System.out.print("Enter Owner ID: ");
        int ownerId = Integer.parseInt(scanner.nextLine());

        Asset asset = new Asset(name, type, serialNumber, purchaseDate, location, status, ownerId);

        AssetManagementServiceImpl assetInput = new AssetManagementServiceImpl();
        boolean assetAdded = assetInput.addAsset(asset);

        if(assetAdded) System.out.println("Asset addedd successfully ");

        scanner.close();
    }

    public static void assetUpdateInput(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Asset ID: ");
        int assetId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Asset Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Asset Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Serial Number: ");
        String serialNumber = sc.nextLine();

        System.out.print("Enter Purchase Date (yyyy-mm-dd): ");
        String purchaseDate = sc.nextLine();

        System.out.print("Enter Location: ");
        String location = sc.nextLine();

        System.out.print("Enter Status: ");
        String status = sc.nextLine();

        System.out.print("Enter Owner ID: ");
        int ownerId = Integer.parseInt(sc.nextLine());

        // Create Asset object
        Asset asset = new Asset(assetId, name, type, serialNumber, purchaseDate, location, status, ownerId);
        AssetManagementServiceImpl assetUpdate = new AssetManagementServiceImpl();
        boolean updateStatus = assetUpdate.updateAsset(asset);

        if(updateStatus){
            System.out.println("\n Asset updated Successfully \n");
        }else{
            System.out.println("\n Failed to update \n");
        }
    }

    public static void deleteAssetInput(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the asset id to be deleted : ");

        int assetId = sc.nextInt();

        AssetManagementServiceImpl assetDelete = new AssetManagementServiceImpl();
        boolean deletionStatus = assetDelete.deleteAsset(assetId);

        if(deletionStatus){
            System.out.println("Asset deleted successfully");
        }else{
            System.out.println("Asset cannot be deleted due to some reasons");
        }

    }

    public static void allocatAssetInput(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter asset Id : ");
        int assetId = sc.nextInt();
        
        System.out.print("Enter employee Id : ");
        int employeeId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Allocation Date (yyyy-mm-dd): ");
        String allocationDate = sc.nextLine();

        AssetManagementServiceImpl assetAllocate = new AssetManagementServiceImpl();
        boolean allocated = assetAllocate.allocateAsset(assetId, employeeId, allocationDate);

        if(allocated){
            System.out.println("Allocated Successfully");
        }else{
            System.out.println("Failed to allocate the resource");
        }
    }

    public static void deAllocateInput(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the asset id : ");
        int assetId = sc.nextInt();

        System.out.print("Enter the employee id : ");
        int employeeId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter the return date (yyyy-mm-dd) : ");
        String returnDate = sc.nextLine();

        AssetManagementServiceImpl deAllocation = new AssetManagementServiceImpl();
        boolean result = deAllocation.deallocateAsset(assetId, employeeId, returnDate);

        if(result){
            System.out.println("Deallocated asset successfully");
        }else{
            System.out.println("Unable to deallocate assets");
        }
    }

    public static void performMaintenanceInput(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the asset ID: ");
        int assetId = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter the maintenance date (yyyy-mm-dd): ");
        String maintenanceDate = sc.nextLine();

        System.out.print("Enter the description of maintenance: ");
        String description = sc.nextLine();

        System.out.print("Enter the cost of maintenance: ");
        double cost = sc.nextDouble();

        AssetManagementServiceImpl maintenanceService = new AssetManagementServiceImpl();
        boolean result = maintenanceService.performMaintenance(assetId, maintenanceDate, description, cost);

        if (result) {
            System.out.println("Maintenance record added successfully.");
        } else {
            System.out.println("Failed to add maintenance record.");
        }

    }

    public static void reserveAssetInput(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the asset ID: ");
        int assetId = sc.nextInt();

        System.out.print("Enter the employee ID: ");
        int employeeId = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter the reservation date (yyyy-mm-dd): ");
        String reservationDate = sc.nextLine();

        System.out.print("Enter the start date (yyyy-mm-dd): ");
        String startDate = sc.nextLine();

        System.out.print("Enter the end date (yyyy-mm-dd): ");
        String endDate = sc.nextLine();

        AssetManagementServiceImpl reservationService = new AssetManagementServiceImpl();
        boolean result = reservationService.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate);

        if (result) {
            System.out.println("Asset reserved successfully.");
        } else {
            System.out.println("Failed to reserve the asset.");
        }

    }

    public static void withdrawReservationInput(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the reservation ID to withdraw: ");
        int reservationId = sc.nextInt();

        AssetManagementServiceImpl withdrawService = new AssetManagementServiceImpl();
        boolean result = withdrawService.withdrawReservation(reservationId);

        if (result) {
            System.out.println("Reservation withdrawn successfully.");
        } else {
            System.out.println("Failed to withdraw the reservation.");
        }

    }
    public static void main(String[] args) {
    
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=== Asset Management System ===");

        while(!exit){
            System.out.println("[1] Add Asset"
            + "\n" +
            "[2] Update Asset"
            + "\n" +
            "[3] Delete Asset"
            + "\n" +
            "[4] Allocate Asset"
            + "\n" +
            "[5] Deallocate Asset"
            + "\n" + 
            "[6] Perform Maintenance"
            + "\n" +
            "[7] Reserve Asset"
            + "\n" +
            "[8] Withdraw asset" + "\n" +
            "[9] Exit");

            System.out.print("Enter your choice : ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    assetInput();
                    break;

                case 2:
                    assetUpdateInput();
                    break;

                case 3:
                    deleteAssetInput();
                    break;

                case 4:
                    allocatAssetInput();
                    break;

                case 5:
                    deAllocateInput();
                    break;

                case 6:
                    performMaintenanceInput();
                    break;

                case 7:
                    reserveAssetInput();
                    break;
            
                case 8:
                    withdrawReservationInput();
                    break;

                case 9:
                    exit = true;
                    break;
                    
                default:
                    exit = true;
                    break;
            }
        }
    }
}
