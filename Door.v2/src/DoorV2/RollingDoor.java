package DoorV2;

import java.util.Scanner;

public class RollingDoor extends Door{
	int numberofcoil;
	public RollingDoor(int id,String type,double height,double width,String material,String handle,int noc) {
		super(id,type,height,width,material,handle);
		this.numberofcoil=noc;
	}
	public RollingDoor() {
		
	}
	
	public void setNOC(int n) {
		this.numberofcoil=n;
	}
	public int getNOC() {
		return this.numberofcoil;
	}
	public void Showinfo() {
		super.Showinfo();
		System.out.print(" ;Number of Coil:"+getNOC()+"\n");
	}
	public void setType() {
		this.setType("Folding Door");
	}
	public String getType() {
		return "Rolling Door";
	}
	public void input() throws Exception {
		Scanner sc = new Scanner(System.in);
		super.input();
		boolean valid = false;
			System.out.print("Number of Coil: ");
			numberofcoil=sc.nextInt();
			if(numberofcoil<=0) throw new Exception("Error:Number of coil is less than 0");
			else valid = true;
				
		
		
	}
}
