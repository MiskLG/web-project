package beans;

public class BuyerType {
	private String name;
	private Double discount;
	private int pointsNeeded;
		
	public BuyerType(String name, Double discount, int pointsNeeded) {
		super();
		this.name = name;
		this.discount = discount;
		this.pointsNeeded = pointsNeeded;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public int getPointsNeeded() {
		return pointsNeeded;
	}
	public void setPointsNeeded(int pointsNeeded) {
		this.pointsNeeded = pointsNeeded;
	}
	
}
