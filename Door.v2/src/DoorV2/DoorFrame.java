package DoorV2;

import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DoorFrame extends JFrame {
	
	String[] typearr = {"Folding Door","Rolling Door"};
	String[] materialarr = {"wood","steel"};
	String[] handlearr = {"smart","keyed"};
	public DoorFrame(String title,List<Door> doorarr) {
		super(title);
		setSize(600,700);
		JPanel mainpanel = new JPanel();
		
		
		//p1
		JPanel p1 = new JPanel(new GridLayout(7,2,10,20));
		JLabel idc= new JLabel("ID:");
		JTextField idt = new JTextField(5);
		JLabel typel= new JLabel("Type");
		JComboBox typec = new JComboBox(typearr);
		JLabel heightl= new JLabel("Height: ");
		JLabel widthl= new JLabel("Width: ");
		JTextField heightt = new JTextField(5);
		JTextField widtht = new JTextField(5);	
		JLabel materiall = new JLabel("Material: ");
		JComboBox material = new JComboBox(materialarr);
		JLabel handlel = new JLabel("Handle:");
		JComboBox handlec = new JComboBox(handlearr);
		JLabel numberl = new JLabel("Number of door:");
		JTextField number = new JTextField(5);
				
		
		p1.add(typel);
		p1.add(typec);
		p1.add(idc);
		p1.add(idt);
		p1.add(heightl);
		p1.add(heightt);
		p1.add(widthl);
		p1.add(widtht);
		p1.add(materiall);
		p1.add(material);
		p1.add(handlel);
		p1.add(handlec);
		p1.add(numberl);
		p1.add(number);
		
		
		
		
		//p2
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		JButton addbtn = new JButton("Add");
		JButton delbtn = new JButton("Delete");
		JTextField findid = new JTextField(5);
		JButton searchbtn = new JButton("Search");
		
		addbtn.setActionCommand("add");
		delbtn.setActionCommand("del");
		searchbtn.setActionCommand("search");
		p2.add(addbtn);
		p2.add(delbtn);
		p2.add(findid);
		p2.add(searchbtn);
		
		
		
		//p4
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		JButton savebtn = new JButton("Save to file");
		JButton loadbtn = new JButton("Load file");
		p4.add(savebtn);
		p4.add(loadbtn);
		
		
		
		
		
		//p3
		String[] field = {"ID","Type","Height","Width","Material","Handle","Number of door/coil"};
		Object[][] data = new Object[doorarr.size()][];
		DefaultTableModel tmodel = new DefaultTableModel(field,0);
		JTable table = new JTable(tmodel);
		updatetable(tmodel,doorarr);
		JScrollPane p3 = new JScrollPane(table);
		
		mainpanel.add(p1);
		mainpanel.add(p2);
		mainpanel.add(p4);
		mainpanel.add(p3);
		mainpanel.setBorder(new EmptyBorder(20,20,20,20));
		mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.Y_AXIS));
		
		this.add(mainpanel);
		this.setVisible(true);
		
		
		//xu ly su kien
		
		typec.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
               
                if(typec.getSelectedItem()=="Folding Door") {
                	 numberl.setText("Number of door:");
                }
                else numberl.setText("Number of coil:");
               
            }
        });
		
		//them
		addbtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					validate(idt);
					validate(heightt);
					validate(widtht);
					validate(number);
					
					boolean idExist = false;
		            for (Door d : doorarr) {
		                if (d.getId() == Integer.parseInt(idt.getText())) {
		                    idExist = true;
		                    break;
		                }
		            }

		            if (idExist) {
		                JOptionPane.showMessageDialog(p3, "ID already exists! Please enter another ID number");
		                return; 
		            }
					if(typec.getSelectedItem().toString()=="Folding Door")  {
		            	FoldingDoor fd = new FoldingDoor(Integer.parseInt(idt.getText()),typec.getSelectedItem().toString(),Integer.parseInt(heightt.getText()),Integer.parseInt(widtht.getText()),material.getSelectedItem().toString(),handlec.getSelectedItem().toString(),Integer.parseInt(number.getText()));
		            	doorarr.add(fd);
		            	updatetable(tmodel,doorarr);
		            	
		            }
		            else {
		            	RollingDoor rd = new RollingDoor(Integer.parseInt(idt.getText()),typec.getSelectedItem().toString(),Integer.parseInt(heightt.getText()),Integer.parseInt(widtht.getText()),material.getSelectedItem().toString(),handlec.getSelectedItem().toString(),Integer.parseInt(number.getText()));
		            	doorarr.add(rd);
		            	updatetable(tmodel,doorarr);
		            }
	                
					           
	            }
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(p3, "Please Enter a Number!");
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(p3, "Please Enter >0 number!");
				}
				
			}
	            
		});
		
		
		//xoa
		delbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int selected = table.getSelectedRow();
					
					int delid = (int) tmodel.getValueAt(selected,0);
					tmodel.removeRow(selected);
					
					
					
					for(Door d: doorarr) {
						if(d.getId()==delid) {
							doorarr.remove(d);
						}
					}
					updatetable(tmodel,doorarr);
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(mainpanel,"Please choose door to delete in table");
				}
				
			}
			
		});
		
		
		//tim kiem
		searchbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					boolean found = false;
					int fid = Integer.parseInt(findid.getText());
					for (Door d : doorarr) {
						if(d.getId()==fid) {
							found=true;
							if(d.getType()=="Folding Door") {
								FoldingDoor fd = (FoldingDoor) d;
								JOptionPane.showMessageDialog(mainpanel,"ID: "+fd.getId()+" ;Type:"+fd.getType()+" ;Height:"+fd.getHeight()+" ;Width:"+fd.getWidth()+" ;Handle:"+fd.getHandle()+" ;"+" Material:"+fd.getMaterial()+" ;Number of door: "+fd.getNOD());
							}
							else {
								RollingDoor rd = (RollingDoor) d;
								JOptionPane.showMessageDialog(mainpanel,"ID: "+rd.getId()+" ;Type:"+rd.getType()+" ;Height:"+rd.getHeight()+" ;Width:"+rd.getWidth()+" ;Handle:"+rd.getHandle()+" ;"+" Material:"+rd.getMaterial()+" ;Number of coil: "+rd.getNOC());
							}
						
							
						}
					}
					if(found==false) {
						JOptionPane.showMessageDialog(mainpanel,"Door not found!");
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(mainpanel,"Please enter ID of door you need to find!");
				}
				
				
			}
			
		});
		
		
		//luu
		savebtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filepath = file.getAbsolutePath();
                    if(filepath.endsWith(".txt")) {
                    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
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
                    	} catch (Exception ex) {
							ex.printStackTrace();
						}
                    }
                    else if(filepath.endsWith(".dat")){
                    	try(FileOutputStream fos = new FileOutputStream(filepath)){
                			ObjectOutputStream oos = new ObjectOutputStream(fos);
                			for(Door d: doorarr) {
                				oos.writeObject(d);
                				
                			}
                		}
                		catch(Exception ex) {
                			ex.printStackTrace();
                		}
                    }
                    else {
                    	JOptionPane.showMessageDialog(mainpanel,"File not support");
                    }
				
                }
			}
			
		});
		
		
		
		//load
		loadbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filepath = file.getAbsolutePath();
                    if(filepath.endsWith(".txt")) {
                    	doorarr.clear();
                		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))){
                			
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
                		catch (Exception ex) {
                			
                			ex.printStackTrace();
                		}
                    }
                    else if(filepath.endsWith(".dat")){
                    	try(FileInputStream fis = new FileInputStream(filepath)){
                			ObjectInputStream ois = new ObjectInputStream(fis);
                			boolean check=true;
                			while(check) {
                				try {
                					Door d = (Door) ois.readObject();
                					doorarr.add(d);
                				}
                				catch (EOFException ex) {
                					check=false;
                				}
                			}
                		}
                		catch(Exception ex) {
                			ex.printStackTrace();
                		}
                	}
                    else {
                    	JOptionPane.showMessageDialog(mainpanel,"File not support");
                    }
                }
                updatetable(tmodel,doorarr);
			}
			
		});
		
	}
	
	
		
	//ham update lai bang
	public void updatetable(DefaultTableModel tmodel,List<Door> doorarr) {
		tmodel.setRowCount(0);
		for(Door door : doorarr) {
			Object[] rowData = new Object[7];
			rowData[0] = door.getId();
            rowData[1] = door.getType();
            rowData[2] = door.getHeight();
            rowData[3] = door.getWidth();
            rowData[4] = door.getMaterial();
            rowData[5] = door.getHandle();
            if(door.getType()=="Folding Door") {
            	rowData[6] = ((FoldingDoor) door).getNOD();
            }
            else
            	rowData[6] = ((RollingDoor) door).getNOC();
            tmodel.addRow(rowData);
		}
	}
	
	public void validate(JTextField tf) throws Exception {
		if(Integer.parseInt(tf.getText())<=0) throw new Exception("Please Enter >0 number");
	}


	
}
