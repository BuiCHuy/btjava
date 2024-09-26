package DoorV2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception  {
		Scanner sc = new Scanner(System.in);
		DoorManager manager = new DoorManager();
		boolean exit=true;
		while(exit) {
			int choice;
			System.out.println("\n----------------------------------");
			System.out.println("Door Manager:");
			System.out.println("1.Add Door");
			System.out.println("2.Delete Door");
			System.out.println("3.Find Door");
			System.out.println("4.Print Door List");
			System.out.println("5.Exit");
			System.out.print("Selection: ");
			choice=sc.nextInt();
			switch(choice){
				case 1:
					int n;
					boolean valid = false;
					while(!valid) {
						System.out.print("Quantity: ");
						try {
							n=sc.nextInt();
							if(n<=0) throw new Exception("Quantity must bigger than 0");
							else valid=true;
							Door door = new Door();
							for(int i=0;i<n;i++) 
							{
								int doortypechoice;
								while(true) {
									System.out.print("Choose door type: 1.Folding door/ 2.Rolling Door: ");
									doortypechoice =sc.nextInt();
									switch (doortypechoice) {
										case 1:
											door = new FoldingDoor();
											door.input();
											break;
										case 2:
											door = new RollingDoor();
											door.input();
											break;
										default :
											System.out.println("Error:Invalid choice. Please choose 1 or 2");
											continue;
									}
									break;
								}
								manager.addDoor(door);
							}
							
						}
						catch(Exception e) {
							System.out.println(e.getMessage());	
						}
					}
					
					
						
					
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
					founddoor.Showinfo();
					break;
				case 4:
					System.out.println("Door List:");
					manager.printDoor();
					
					break;
				case 5:
					exit=false;
					break;
				default :
					System.out.println("Error:Invalid choice. Please choose correct number (1-5)");
			}
		}
	}
		

}
