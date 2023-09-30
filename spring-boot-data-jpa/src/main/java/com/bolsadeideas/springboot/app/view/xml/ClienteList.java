package com.bolsadeideas.springboot.app.view.xml;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="clientes")
public class ClienteList {

	@XmlElement(name = "cliente")
	public List<Cliente> Clientes;

	public ClienteList(List<Cliente> clientes){

	}

	public ClienteList() {
	}

	public List<Cliente> getClientes() {
		return Clientes;
	}
}