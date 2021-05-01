package locked;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMe {

	static String dir_selected;
	static int menu_option;
	static File folder_name;
	public LockedMe() {
		
		var message = "***************** Welcome to the Locked File System  ***************************\n"+
					  "***************** Developed by Ashok Thangellapally ****************************\n"+
					  "***************** Conatct Email : tashok.503@gmail.com *************************\n";
		System.out.println(message);
		
	}
	
	private final void ShowMainMenu(){
		
		var menu_message = "MAIN MENU : \n"+
							"1. For listing All files in directory \n"+
							"2. For Add,Create and Delete operations \n"+
							"3. For Exit from the program"+
							"\n Please select any one option : 1 or 2 or 3";
		
		System.out.println(menu_message);					
	}
	
	private static File selectDirectory(String dirname) {
		var folder_name = new File(dirname);
        if (!folder_name.exists())
        	folder_name.mkdirs();		
        return folder_name;
		
	}	

	public void ShowOperations(int menu_option) {
		Scanner scanner = new Scanner(System.in);
		if(menu_option==1) {
		    showFiles();
		    ShowMainMenu();			
		    menu_option = scanner.nextInt();
		    ShowOperations(menu_option);
		}else if(menu_option==2) {
			
			viewCrudMenu();
		}else if(menu_option==3) {
			
            System.out.println("Thank You");
            System.exit(0);
		}else {
			ShowMainMenu();	
		}		
		
	}
	
    void viewCrudMenu() {
    	var crud_message = "List of ther operations allowed \n"+
    					   "\t a for add file \n"+
    					   "\t b for delete file \n"+
    					   "\t c for Search file \n"+
    					   "\t d for GoTo MAIN MENU";
        System.out.println(crud_message);
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case 'a' : {
                    System.out.print("↳ Adding a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim().toLowerCase();
                    addFile(filename);
                    break;
                }
                case 'b' : {
                    System.out.print("↳ Deleting a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    deleteFile(filename);
                    break;
                }
                case 'c' : {
                    System.out.print("↳ Searching a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    searchFile(filename);
                    break;
                }
                case 'd' : {
                    System.out.println("Go Back to MAIN menu");
                    ShowMainMenu();
        		    menu_option = scanner.nextInt();
        		    ShowOperations(menu_option);
                }
                default : System.out.println("Please enter a, b, c or d");
            }
            viewCrudMenu();
        }
        catch (Exception e){
            System.out.println("Please enter a, b, c or d");
            viewCrudMenu();
        }
    }
	
    void addFile(String filename) throws IOException {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equalsIgnoreCase(file)) {
                System.out.println("File " + filename + " already exists at " + folder_name);
                return;
            }
        }
        filepath.createNewFile();
        System.out.println("File "+filename+" added to "+ folder_name);
    }
	
    void showFiles() {
        if (folder_name.list().length==0)
            System.out.println("The folder is empty\n");
        else {
            String[] list = folder_name.list();
            System.out.println("The files in "+ folder_name +" are :");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }
    
    void deleteFile(String filename) {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file) && filepath.delete()) {
                System.out.println("File " + filename + " deleted from " + folder_name);
                return;
            }
        }
        System.out.println("Delete Operation failed. FILE NOT FOUND");
    }
    
    void searchFile(String filename) {
    	
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("FOUND : File " + filename + " exists at " + folder_name);
                return;
            }
        }
        System.out.println("File NOT found (FNF)");
    }    
    
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		LockedMe lock = new LockedMe();
		Scanner scanner = new Scanner(System.in);
		
		// SELECT DIRECTORY TO PERFORM ACTIONS
		while(dir_selected==null || dir_selected=="") {
			System.out.println("PLEASE SELECT DIRECTORY LOCATION TO PERFORM ACTIONS \n ex: F:\\demo_files");
			dir_selected = scanner.nextLine().trim();
			folder_name = selectDirectory(dir_selected);
		}		
		
		
		System.out.println("Dir selected is "+dir_selected);
		System.out.println("---------------------------------");
		
		//SHOW MAIN MENU		
		while(menu_option<=0 || menu_option>=4) {
			lock.ShowMainMenu();
			menu_option = scanner.nextInt();
		}
		System.out.println("------------------------------------------");
		lock.ShowOperations(menu_option);
		
		//System.out.println(dir_selected);
		//dir_selected = selectDirectory()
		//lock.ShowMainMenu();
		
	}
	

	
	
	

}
