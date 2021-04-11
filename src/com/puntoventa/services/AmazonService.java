package com.puntoventa.services;

import javax.faces.bean.RequestScoped;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.puntoventa.utilities.Constants;

@RequestScoped
public class AmazonService {

	private final BasicAWSCredentials credentials = new BasicAWSCredentials(Constants.ACCESS_KEY, Constants.SECRET_KEY);

	@SuppressWarnings("deprecation")
	public void sendSMSMessage(String codigo, String nombre, int cveSucursal) {
		AmazonSNSClient client = new AmazonSNSClient(credentials);
		String sucursal = cveSucursal == 1 ? "BERAKA" : "";
		String message = "El producto " + nombre + " con código " + codigo + " en la sucursal " + sucursal
				+ " se ha quedado sin inventario.";
		String phoneNumber = "+525517241751";
		client.setRegion(Region.getRegion(Regions.US_EAST_1));
		client.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber));
	}

}
