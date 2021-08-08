import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Album {

	private ArrayList<Photo> album = new ArrayList<Photo>();
	
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
	         album.clear();
	         return ;
		}
		
		catch(WrongFormatException e) {
	         System.out.println("Wrong Date Format ; Skip the input line : " + line);
	         album.clear();
	         return ;
	      }
	      
		catch(IdConflictException e) {
			System.out.println("ID Conflict (a photo with the same ID already exists); Skip the input line: " + line);
	        album.clear();
	        return ;
	        }
	}
	
	
	public int numPhotos() {
		return album.size();
	}
	
	public Photo getPhoto(int i) {
		return album.get(i);
	}
	
	public void addPhoto(Photo p) {
		album.add(p);
	}
	
	public void delete(Photo p) {
		album.remove(p);
	}
	
	public void parsingInfoPhoto(String data) throws NotFoundImageException, WrongFormatException, IdConflictException {
		
		String[] token = data.split("\\;");
		
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
			album.add(new Photo(token[0]));
		}
		
		else {
			album.add(new Photo(token));
		}
			
	}
	
	public void saveAlbum(String Album_object) {
		
		try {
			OutputStream out = new FileOutputStream(Album_object);
			
			for(int i=0; i<this.numPhotos(); i++) {
				
				String s = album.get(i).getInfoString();
				byte[] data = s.getBytes();
				out.write(data);
			}
			
			out.write('\n');
		}
		catch(Exception e) {
		
		}
	}
	
	public void printPhoto() {
		for(int i=0; i<this.numPhotos(); i++) {
			String s = album.get(i).getInfoString();
			System.out.println(s);
		}
	}
	
	public void sortbyDate() {
		Collections.sort(this.album, new Comparator<Photo>() {
			public int compare(Photo p1, Photo p2) {
				return p1.getAdded_time().compareTo(p2.getAdded_time());
			}
		});
		Collections.reverse(this.album);
	}
	
	public void sortbyCategory() {
		Collections.sort(this.album, new Comparator<Photo>() {
			public int compare(Photo p1, Photo p2) {
				
				if(p1.getCategory().equals(p2.getCategory())) {
					return p1.getAdded_time().compareTo(p2.getAdded_time());
				}
				
				else
					return p1.getCategory().compareTo(p2.getCategory());
		
			}
		});
	}

}

class NotFoundImageException extends Exception{
    
}

class WrongFormatException extends Exception{
   
}

class IdConflictException extends Exception{
   
}

		