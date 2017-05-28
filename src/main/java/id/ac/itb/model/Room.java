package id.ac.itb.model;

public class Room {
	private int id;
	private String title;
	private String description;
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private int id_floor;
	private String title_floor;
	private String description_floor;
	private int id_building;
	private String title_building;
	private String description_building;
	private String latitude;
	private String longitude;
	private Position position;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public double getY1() {
		return y1;
	}
	public void setY1(double y1) {
		this.y1 = y1;
	}
	public double getY2() {
		return y2;
	}
	public void setY2(double y2) {
		this.y2 = y2;
	}
	public int getId_floor() {
		return id_floor;
	}
	public void setId_floor(int id_floor) {
		this.id_floor = id_floor;
	}
	public String getTitle_floor() {
		return title_floor;
	}
	public void setTitle_floor(String title_floor) {
		this.title_floor = title_floor;
	}
	public String getDescription_floor() {
		return description_floor;
	}
	public void setDescription_floor(String description_floor) {
		this.description_floor = description_floor;
	}
	public int getId_building() {
		return id_building;
	}
	public void setId_building(int id_building) {
		this.id_building = id_building;
	}
	public String getTitle_building() {
		return title_building;
	}
	public void setTitle_building(String title_building) {
		this.title_building = title_building;
	}
	public String getDescription_building() {
		return description_building;
	}
	public void setDescription_building(String description_building) {
		this.description_building = description_building;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
}
