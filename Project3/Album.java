import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Album {

	private Photo[] album = new Photo[100];
	private int numphoto = 0;
	
	public Album() {
		
	}
	
	public Album(String photoInfoFileName) {
		
		File file = new File(photoInfoFileName);
		
		Scanner input = null;
		String line = "";
		
		try {
			input = new Scanner(file);
			
			while(input.hasNext()) {
				line = input.nextLine();
				
				if(!line.isEmpty() && !line.startsWith("//")) {
					parsingInfoPhoto(line + " ");
					numphoto++;
				}
			}
			
			input.close();
		}
		
		catch(FileNotFoundException e) {
			System.out.println("Unknwon Album data File");
			return ;
		}
		
		catch(NotFoundImageException e) {
			System.out.println("No Image File  ; Skip the input line : " + line);
	         numphoto = 0;
	         return ;
		}
		
		catch(WrongFormatException e) {
	         System.out.println("Wrong Date Format ; Skip the input line : " + line);
	         numphoto = 0;
	         return ;
	      }
	      
		catch(IdConflictException e) {
			System.out.println("ID Conflict (a photo with the same ID already exists); Skip the input line: " + line);
	        numphoto = 0;
	        return ;
	        }
	}
	
	
	public int numPhotos() {
		return numphoto;
	}
	
	public Photo getPhoto(int i) {
		return album[i];
	}
	
	public void parsingInfoPhoto(String data) throws NotFoundImageException, WrongFormatException, IdConflictException {
		
		String[] token = data.split(";");
		
		int[] position = {4,7,10,13,16,19};
		char[] letter = {'-','-','_',':',':',':'};
		
		for(int i=0; i<token.length; i++) {
			token[i] = token[i].trim();
		}
		
		if(token[4].equals("")) throw new NotFoundImageException();
		
		for(int i=0; i<this.numPhotos(); i++) {
			if(this.getPhoto(i).getId().equals(token[0])) throw new IdConflictException();
		}
		
		for(int i=0; i<6; i++) {
			if(token[2].charAt(position[i]) != letter[i]) throw new WrongFormatException();
		}
		
		if(token.length == 1) {
			album[this.numPhotos()] = new Photo(token[0]);
		}
		
		else {
			album[this.numPhotos()] = new Photo(token);
		}
			
	}
	
	public void saveAlbum(String Album_object) {
		
		try {
			OutputStream out = new FileOutputStream(Album_object);
			
			for(int i=0; i<numphoto; i++) {
				
				String s = album[i].getInfoString();
				byte[] data = s.getBytes();
				out.write(data);
			}
			
			out.write('\n');
		}
		catch(Exception e) {
		
		}
	}

}

class NotFoundImageException extends Exception{
    
}

class WrongFormatException extends Exception{
   
}

class IdConflictException extends Exception{
   
}		