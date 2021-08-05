public class Main {

	public static void main(String[] args) {
		
		Album album = new Album("ID-conflict.data");
		
		for(int i=0; i<album.numPhotos(); i++) {
			album.getPhoto(i).print();
		}	
	}
}
