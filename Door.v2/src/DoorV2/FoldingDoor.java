package DoorV2;

import java.util.Scanner;

public class FoldingDoor extends Door{
	int numberofdoor;
	public FoldingDoor(int id,String type,double height,double width,String material,String handle,int nod) {
		super(id,type,height,width,material,handle);
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
	public void setType() {
		this.setType("Folding Door");
	}
	public String getType() {
		return "Folding Door";
	}
	public void input() throws Exception {
		Scanner sc = new Scanner(System.in);
		super.input();
		boolean valid = false;
		
				System.out.print("Number of Door: ");
				numberofdoor=sc.nextInt();
				if(numberofdoor<=0) throw new Exception("Error:Number of door is less than 0");
				else valid = true;
			
		}
		
		
	
	
}
