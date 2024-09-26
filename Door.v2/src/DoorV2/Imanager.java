package DoorV2;



public interface Imanager {
	public void addDoor(Door d); 
	public void deleteDoor(int id);
	public Door findDoor(int id);
	public void printDoor();
}
