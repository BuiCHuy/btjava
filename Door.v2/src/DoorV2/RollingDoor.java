package DoorV2;

import java.util.Scanner;

public class RollingDoor extends Door{
	int numberofcoil;
	public RollingDoor(int id,double height,double width,String color,String material,String handle,int noc) {
		super(id,height,width,color,material,handle);
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
	public void input() throws Exception {
		Scanner sc = new Scanner(System.in);
		super.input();
		boolean valid = false;
		while(!valid) {
			try {
				System.out.print("Number of Coil: ");
				numberofcoil=sc.nextInt();
				if(numberofcoil<=0) throw new Exception("Error:Number of coil is less than 0");
				else valid = true;
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				
			}
		}
		
	}
}
