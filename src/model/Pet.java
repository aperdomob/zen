package model;

import java.util.Date;

public class Pet {
	
	private String name;
	
	private Date birthDate; 
	
	private float [] maxHeightRangeCm;
	
	private float [] maxWidthRangeCm;
	
	private float [] maxWeightRangeCm;
	
	private float actualHeightCm;
	
	private float actualWidthCm;
	
	private float actualWeightCm;
	
	private boolean isQuadruped;
	
	private boolean isBiped;
	
	private int [] colors;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isQuadruped() {
		return isQuadruped;
	}

	public void setQuadruped(boolean isQuadruped) {
		this.isQuadruped = isQuadruped;
	}

	public boolean isBiped() {
		return isBiped;
	}

	public void setBiped(boolean isBiped) {
		this.isBiped = isBiped;
	}

	public int[] getColors() {
		return colors;
	}

	public void setColors(int[] colors) {
		this.colors = colors;
	}

	public float[] getMaxHeightRangeCm() {
		return maxHeightRangeCm;
	}

	public void setMaxHeightRangeCm(float[] maxHeightRangeCm) {
		this.maxHeightRangeCm = maxHeightRangeCm;
	}

	public float[] getMaxWidthRangeCm() {
		return maxWidthRangeCm;
	}

	public void setMaxWidthRangeCm(float[] maxWidthRangeCm) {
		this.maxWidthRangeCm = maxWidthRangeCm;
	}

	public float[] getMaxWeightRangeCm() {
		return maxWeightRangeCm;
	}

	public void setMaxWeightRangeCm(float[] maxWeightRangeCm) {
		this.maxWeightRangeCm = maxWeightRangeCm;
	}

	public float getActualHeightCm() {
		return actualHeightCm;
	}

	public void setActualHeightCm(float actualHeightCm) {
		this.actualHeightCm = actualHeightCm;
	}

	public float getActualWidthCm() {
		return actualWidthCm;
	}

	public void setActualWidthCm(float actualWidthCm) {
		this.actualWidthCm = actualWidthCm;
	}

	public float getActualWeightCm() {
		return actualWeightCm;
	}

	public void setActualWeightCm(float actualWeightCm) {
		this.actualWeightCm = actualWeightCm;
	}
	
	
}
