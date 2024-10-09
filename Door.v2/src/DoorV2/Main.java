package DoorV2;
import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.*;


public class Main {

	

	public static void main(String[] args) throws Exception  {
		
		Scanner sc = new Scanner(System.in);
		DoorManager manager = new DoorManager();
		DoorFrame frame = new DoorFrame("Door Manager",manager.doorarr);
		
		boolean exit=true;
		while(exit) {
			int choice;
			System.out.println("\n----------------------------------");
			System.out.println("Door Manager:");
			System.out.println("1.Add Door");
			System.out.println("2.Delete Door");
			System.out.println("3.Find Door");
			System.out.println("4.Print Door List");
			System.out.println("5.Work with ddata.txt file");
			System.out.println("6.Work with ddata.dat file");
			System.out.println("7.Exit");
			System.out.print("Selection: ");
			choice=sc.nextInt();
			switch(choice){
				case 1:
					manager.addDoor();
					break;
				case 2:
					System.out.print("Enter id of door you want to delete: ");
					int delid = sc.nextInt();
					manager.deleteDoor(delid);
					break;
				case 3:
					
					System.out.print("Enter id of door you want to find: ");
					int findid = sc.nextInt();
					Door founddoor = manager.findDoor(findid);
					if(founddoor!=null) {
						founddoor.Showinfo();
					}
					else System.out.println("Door not found");
					break;
				case 4:
					System.out.println("Door List:");
					if(manager.doorarr.size()>0) {
						manager.printDoor();
					}
					else System.out.println("There is 0 door in the list");
					
					
					break;
				case 5:
					boolean exit2=true;
				
					while(exit2) {
						
						System.out.println("1.Print door list from ddata.txt");
						System.out.println("2.Load data from ddata.txt");
						System.out.println("3.Save data to ddata.txt");
						System.out.println("4.Exit");
						System.out.print("Selection: ");
						int choice2 = sc.nextInt();
						switch(choice2) {
						
						case 1:
							manager.printFile();
							break;
						case 2:
							manager.loadFile();
							break;
						case 3:
							manager.saveFile();
							break;
						case 4:
							exit2=false;
							break;
						default:
							System.out.println("Invalid selection. Please choose between 1-4.");
							break;
							
						}
					}
					break;
				case 6:
					boolean exit3=true;
					while(exit3)
					{
						System.out.println("1.Load data from ddata.dat");
						System.out.println("2.Save data to ddata.dat");
						System.out.println("3.Exit");
						System.out.print("Selection: ");
						int choice3=sc.nextInt();
						
						switch(choice3) {
						case 1:
							manager.loadFiledat();
							break;
						case 2:
							manager.saveFiledat();
							break;
						case 3:
							exit3=false;
							break;
						default:
							System.out.println("Invalid selection. Please choose between 1-3.");
							break;
						}
					}
					break;
				case 7:
					exit=false;
					break;
				default :
					System.out.println("Error:Invalid choice. Please choose correct number (1-5)");
			}
		}
	}
		

}
