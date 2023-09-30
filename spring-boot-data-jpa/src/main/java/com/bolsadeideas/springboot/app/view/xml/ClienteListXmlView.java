package com.bolsadeideas.springboot.app.view.xml;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Map;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Component("listar.xml")
public class ClienteListXmlView extends MarshallingView {

	@Autowired
	public ClienteListXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
										   HttpServletRequest request,
										   HttpServletResponse response)
			throws Exception {

		model.remove("titulo");
		model.remove("page");

		//noinspection unchecked
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");

		model.remove("clientes");

		model.put("clienteList", new ClienteList(clientes.getContent()));

		super.renderMergedOutputModel(model, request, response);
	}
}