package DoorV2;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoorManager implements Imanager {
	public List<Door> doorarr = new ArrayList<>();
	public List<Door> doorfile = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	public void input(Door d) throws Exception {
		
		try {
			d.input();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			d.input();
		}
	}
	public void addDoor() {
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
								input( door);
								break;
							case 2:
								door = new RollingDoor();
								input( door);
								break;
							default :
								System.out.println("Error:Invalid choice. Please choose 1 or 2");
								continue;
						}
						doorarr.add(door);
						break;
						
					}
					
				}
				
			}
			catch(Exception e) {
				System.out.println(e.getMessage());	
			}
		}
		
	}
	
	public void deleteDoor(int id) {
		Door ddoor = findDoor(id);
		doorarr.remove(ddoor);	
	}
	
	public Door findDoor(int id) {
		for(Door d : doorarr) {
			if(d.getId()==id) {
				return d;
			}
		}
		return null;
			
	}
	public void printDoor() {
		for(Door d : doorarr) {
			d.Showinfo();
		}
	}
//	public void addDoorFile() {
//		
//		int doortypechoice;
//		
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\ddata.txt",true))){
//			
//			while(true) {
//				System.out.print("Choose door type: 1.Folding door/ 2.Rolling Door: ");
//				doortypechoice =sc.nextInt();
//				switch (doortypechoice) {
//					case 1:
//						FoldingDoor fd = new FoldingDoor();
//						input(fd);
//						writer.write(fd.getId()+","+fd.getType()+","+fd.getHeight()+","+fd.getWidth()+","+fd.getColor()+","+fd.getHandle()+","+fd.getMaterial()+","+fd.getNOD());
//						writer.newLine();
//						break;
//					case 2:
//						RollingDoor rd = new RollingDoor();
//						input(rd);
//						writer.write(rd.getId()+","+rd.getType()+","+rd.getHeight()+","+rd.getWidth()+","+rd.getColor()+","+rd.getHandle()+","+rd.getMaterial()+","+rd.getNOC());
//						writer.newLine();
//						break;
//					default :
//						System.out.println("Error:Invalid choice. Please choose 1 or 2");
//						continue;
//				}
//				break;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
	public void printFile() {
		 
		try (BufferedReader reader = new BufferedReader(new FileReader("F:\\ddata.txt"))){
			
			String line;
	        while ((line = reader.readLine()) != null) {
	            
	            String[] data = line.split(",");  
	            int id = Integer.parseInt(data[0]);
	            String type = data[1];
	            double height = Double.parseDouble(data[2]);
	            double width = Double.parseDouble(data[3]);
	            
	            String handle = data[4];
	            String material = data[5];
	            int number = Integer.parseInt(data[6]);
	            
	            switch(type) {
	            case "Folding Door":
	            	FoldingDoor fd = new FoldingDoor(id,type,height,width,handle,material,number);
	            	fd.Showinfo();
	            	break;
	            
	            case "Rolling Door":
	            	RollingDoor rd = new RollingDoor(id,type,height,width,handle,material,number);
	            	rd.Showinfo();
	            	break;
	            }
	        }
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
            
     }
	public void loadFile() {
		doorarr.clear();
		try (BufferedReader reader = new BufferedReader(new FileReader("F:\\ddata.txt"))){
			
			String line;
	        while ((line = reader.readLine()) != null) {
	            
	            String[] data = line.split(",");  
	            int id = Integer.parseInt(data[0]);
	            String type = data[1].trim();
	            double height = Double.parseDouble(data[2]);
	            double width = Double.parseDouble(data[3]);
	            
	            String handle = data[4];
	            String material = data[5];
	            int number = Integer.parseInt(data[6]);
	            
	            switch(type) {
	            case "Folding Door":
	            	FoldingDoor fd = new FoldingDoor(id,type,height,width,handle,material,number);
	            	doorarr.add(fd);
	            	break;
	            
	            case "Rolling Door":
	            	RollingDoor rd = new RollingDoor(id,type,height,width,handle,material,number);
	            	doorarr.add(rd);
	            	break;
	            }
	        }
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void saveFile() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\ddata.txt"))){
			for(Door d: doorarr) {
				switch(d.getType()) {
				case "Folding Door":
					FoldingDoor fd = (FoldingDoor) d;
					writer.write(fd.getId()+","+fd.getType()+","+fd.getHeight()+","+fd.getWidth()+","+fd.getHandle()+","+fd.getMaterial()+","+fd.getNOD());
					writer.newLine();
					break;
				case "Rolling Door":
					RollingDoor rd = (RollingDoor) d;
					writer.write(rd.getId()+","+rd.getType()+","+rd.getHeight()+","+rd.getWidth()+","+rd.getHandle()+","+rd.getMaterial()+","+rd.getNOC());
					writer.newLine();
					break;
				}
				
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void saveFiledat() {
		try(FileOutputStream fos = new FileOutputStream("F:\\ddata.dat")){
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for(Door d: doorarr) {
				oos.writeObject(d);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void loadFiledat() {
		try(FileInputStream fis = new FileInputStream("F:\\ddata.dat")){
			ObjectInputStream ois = new ObjectInputStream(fis);
			boolean check=true;
			while(check) {
				try {
					Door d = (Door) ois.readObject();
					doorarr.add(d);
				}
				catch (EOFException e) {
					check=false;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
