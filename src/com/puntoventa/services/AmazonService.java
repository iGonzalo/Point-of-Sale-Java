package com.puntoventa.services;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.puntoventa.utilities.Constants;
import com.puntoventa.utilities.Util;

@RequestScoped
public class AmazonService {

	private BasicAWSCredentials credentials;

	@PostConstruct
	public void init() {
		credentials = new BasicAWSCredentials(Util.getDecodedString(Constants.ACCESS_KEY),
				Util.getDecodedString(Constants.SECRET_KEY));
	}

	@SuppressWarnings("deprecation")
	public void sendSMSMessage(String codigo, String nombre, int claveSucursal) {
		AmazonSNSClient client = new AmazonSNSClient(credentials);
		String sucursal = getSucursal(claveSucursal);
		String message = "El producto " + nombre + " con código " + codigo + " en la sucursal " + sucursal
				+ " se ha quedado sin inventario.";
		String phoneNumber = "+525517241751";
		client.setRegion(Region.getRegion(Regions.US_EAST_1));
		client.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber));
	}

	public String getSucursal(int claveSucursal) {
		String nombreSucursal = "";
		switch (claveSucursal) {
		case 1:
			nombreSucursal = "BERAKA";
			break;
		default:
			break;
		}
		return nombreSucursal;
	}

}
