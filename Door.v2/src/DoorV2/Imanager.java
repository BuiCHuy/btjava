package DoorV2;



public interface Imanager {
	public void addDoor(); 
	public void deleteDoor(int id);
	public Door findDoor(int id);
	public void printDoor();
	public void printFile();
	public void loadFile();
	public void saveFile();
	public void loadFiledat();
	public void saveFiledat();
}
