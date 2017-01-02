package source.refacotring03;


public class Rental_Refacoting03 {
	
	private Movie_Refacorting03 movie;
	private int daysRented;
	
	public Movie_Refacorting03 getMovie() {
		return movie;
	}
	public void setMovie(Movie_Refacorting03 movie) {
		this.movie = movie;
	}
	public int getDaysRented() {
		return daysRented;
	}
	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}
	
	public Rental_Refacoting03(Movie_Refacorting03 movie, int daysRented) {
		super();
		this.movie = movie;
		this.daysRented = daysRented;
	}
	
	int getFrequentRenterPoints(){
		//调用Movie_Refacorting03#getFerquentRenterPoints方法
		return this.movie.getFerquentRenterPoints(daysRented);
	}
	
	double getCharge(){
		//调用Movie_Refacorting03#getCharge方法
		return this.movie.getCharge(daysRented);
	}
}
