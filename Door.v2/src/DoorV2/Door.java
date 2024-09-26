package DoorV2;
import java.util.ArrayList;
import java.util.Scanner;

public class Door  {
	private int id;
	private double height;
	private double width;
	private String color;
	private String material;
	private String handle;
	public String[] colorpack ={"red","yellow","green","blue","black","white"};
	public String[] materialpack = {"wood","steel"};
	public Door(int id,double height,double width,String color,String material,String handle ) {
		
		this.id=id;
		this.height=height;
		this.width=width;
		this.color=color;
		this.material=material;
		this.handle=handle;
	}
	public Door() {}
	public int getId() {
		return this.id;
	}
	public double getHeight() {
		return this.height;
	}
	public double getWidth() {
		return this.width;
	}
	public String getColor() {
		return this.color;
	}
	public String getHandle()
	{
		return this.handle;
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setHeight(double h) {
		this.height=h;
	}
	public void setWidth(double w) {
		this.width=w;
	}
	public void setColor(String c) {
		this.color=c;
	}
	public void setHandle(String ha) {
		this.handle=ha;
	}
	public String Name() {
		return "Door";
	}
	public void Showinfo() {
		System.out.print("ID: "+getId()+" ;Height:"+getHeight()+" ;Width:"+getWidth()+" ;Color:"+getColor()+" ;Handle:"+getHandle()+" Material:"+getMaterial());
	}
	public String getMaterial() {
		return this.material;
	}
	public void input() throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		while(!valid) {
			try {
				System.out.print("ID: ");
				id = sc.nextInt();
				System.out.print("Height: ");
				
				height = sc.nextDouble();
				
				
				System.out.print("Width: ");
				width = sc.nextDouble();
				sc.nextLine();
				System.out.print("Color: ");
				color = sc.nextLine();
				int x;
				System.out.print("Handle: 1.Smart/2.Keyed ");
				
				x =sc.nextInt();
				if(x==1) {
					handle="Smart";
				}
				else if(x==2) {
					handle="Keyed";
				}
				else if(x!=1||x!=2)  throw new Exception("Error:Please choose correct handle");
				
				
				sc.nextLine();
				System.out.print("Material: ");
				material =sc.nextLine();
				
				if(height<=0) throw new Exception("Error:Height is less than 0");
				
				if(width<=0) throw new Exception("Error:Width is less than 0");
				
				boolean colorcheck = false;
				for(String s: colorpack) {
					if(color.equals(s)) {
						colorcheck=true;
					}
				}
				if(colorcheck==false) {
					throw new Exception("Error:Color not found");
				}
				boolean materialcheck = false;
				for(String s: materialpack) {
					if(material.equals(s)) {
						materialcheck=true;
					}
				}
				if(materialcheck==false) {
					throw new Exception("Error:Material not found");
				}
				else valid=true;
		}
		
		
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		
		}
	
	
	}
	
}

