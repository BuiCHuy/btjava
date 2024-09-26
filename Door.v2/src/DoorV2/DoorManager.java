package DoorV2;

import java.util.ArrayList;
import java.util.List;

public class DoorManager implements Imanager {
	public List<Door> doorarr = new ArrayList<>();
	public void addDoor(Door d) {
		doorarr.add(d);
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
		
}
