package br.com.brenohff.maxipago.MaxiPago.entity;

import java.io.Serializable;

public class DistanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String initialCity;
	private String finalCity;
	private String unit;
	private double distance;

	public String getInitialCity() {
		return initialCity;
	}

	public void setInitialCity(String initialCity) {
		this.initialCity = initialCity;
	}

	public String getFinalCity() {
		return finalCity;
	}

	public void setFinalCity(String finalCity) {
		this.finalCity = finalCity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
