package DoorV2;

import java.util.Scanner;

public class FoldingDoor extends Door{
	int numberofdoor;
	public FoldingDoor(int id,double height,double width,String color,String material,String handle,int nod) {
		super(id,height,width,color,material,handle);
		this.numberofdoor=nod;
	}
	public FoldingDoor() {}
	public void setNOD(int n) {
		this.numberofdoor=n;
	}
	public int getNOD() {
		return this.numberofdoor;
	}
	public void Showinfo() {
		super.Showinfo();
		System.out.print(" ;Number of Door:"+getNOD()+"\n");
	}
	public void input() throws Exception {
		Scanner sc = new Scanner(System.in);
		super.input();
		boolean valid = false;
		while(!valid) {
			try {
				System.out.print("Number of Door: ");
				numberofdoor=sc.nextInt();
				if(numberofdoor<=0) throw new Exception("Error:Number of door is less than 0");
				else valid = true;
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				
			}
		}
		
		
		
	}
}
