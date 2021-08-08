import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Photo {

	private String id;
	private String name;
	private String added_time;
	private String category;
	private String file_name;
	private String created_time;
	
	public Photo(String imageFileName) {
		
		String current_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:mm:ss:SSS"));
		
		this.id = "IMG" + current_time;
		this.added_time = current_time;
		this.file_name = imageFileName;
	}
	
	public Photo(String[] fileInformation) {
		
		this.id = fileInformation[0];
		this.name = fileInformation[1];
		this.added_time = fileInformation[2];
		this.category = fileInformation[3];
		this.file_name = fileInformation[4];
		this.created_time = fileInformation[5];
	}
	
	public String getId() {
		return this.id;
	   }
	   
	public void setId(String id) {
		this.id = new String(id);
	   }
	
	public String getName() {
		return this.name;
	   }
	   
	public void setName(String name) {
		this.name = new String(name);
	   }
	   
	public String getAdded_time() {
		return this.added_time;
	   }
	   
	public void setAdded_time(String added_time) {
		this.added_time = new String(added_time);
	   }
	   
	public String getCategory() {
		return this.category;
	   }
	   
	public void setCategory(String category) {
		this.category = new String(category);
	   }
	   
	public String getFile_name() {
		return this.file_name;
	   }
	   
	public void setFile_name(String file_name) {
		this.file_name = new String(file_name);
	   }
	   
	public String getCreated_time() {
		return this.created_time;
	   }
	   
	public void setCreated_time(String created_time) {
		this.created_time = new String(created_time);
	   }
	
	public String getInfoString() {
		return (this.id+";"+this.name+";"+this.added_time+";"+this.category+";"+this.file_name+";"+this.created_time);
	}
	
	public void print() {
		System.out.println(this.getInfoString());
	}
	
}

