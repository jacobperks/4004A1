package server.logic.model;

public class Title {
	String ISBN;
	String booktitle;
	
	public Title(String ISBN,String booktitle){
		this.ISBN=ISBN;
		this.booktitle=booktitle;
	}

	public String getISBN() {
		return ISBN;
	}
	
	public String getBooktitle() {
		return booktitle;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	
	public String toString(){
		return "["+this.ISBN+","+this.booktitle+"]";
	}

}