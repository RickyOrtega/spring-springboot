package com.bolsadeideas.springboot.app.view.xlsx;
import com.bolsadeideas.springboot.app.models.entity.Factura;


import com.bolsadeideas.springboot.app.models.entity.ItemFactura;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.Locale;
import java.util.Map;

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Factura factura = (Factura) model.get("factura");
		Sheet sheet = workbook.createSheet(messageSource.getMessage("text.factura.ver.datos.factura", null, request.getLocale()));

		Locale locale = localeResolver.resolveLocale(request);

		//Cambiamos el nombre del archivo con la estructura: nombreClienteapellidoCliente_idFactura
		response.setHeader("Content-Disposition", "attachment; filename=\""
				.concat(factura.getCliente().getNombre())
				.concat(factura.getCliente().getApellido())
				.concat("_")
				.concat(String.valueOf(factura.getId()))
				.concat(".xlsx\""));

		MessageSourceAccessor mensajes = getMessageSourceAccessor();

		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);

		cell.setCellValue(messageSource.getMessage("text.factura.ver.datos.cliente", null, locale));
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getNombre().concat(" ").concat(factura.getCliente().getApellido()));

		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getEmail());

		sheet.createRow(4).createCell(0).setCellValue(mensajes.getMessage("text.factura.ver.datos.factura", locale));
		sheet.createRow(5).createCell(0).setCellValue(mensajes.getMessage("text.cliente.factura.folio", locale).concat(": ").concat(factura.getId().toString()));
		sheet.createRow(6).createCell(0).setCellValue(mensajes.getMessage("text.cliente.factura.descripcion", locale).concat(": ").concat(factura.getDescripcion()));
		sheet.createRow(7).createCell(0).setCellValue(mensajes.getMessage("text.cliente.factura.fecha", locale).concat(": ").concat(factura.getCreatedAt().toString()));

		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue(mensajes.getMessage("text.factura.form.item.nombre", locale));
		header.createCell(1).setCellValue(mensajes.getMessage("text.factura.form.item.precio", locale));
		header.createCell(2).setCellValue(mensajes.getMessage("text.factura.form.item.cantidad", locale));
		header.createCell(3).setCellValue(mensajes.getMessage("text.factura.form.item.total", locale));

		int rownum = 10;

		for(ItemFactura item: factura.getItems()){
			Row fila = sheet.createRow(rownum++);
			fila.createCell(0).setCellValue(item.getProducto().getNombre());
			fila.createCell(1).setCellValue(item.getProducto().getPrecio());
			fila.createCell(2).setCellValue(item.getCantidad());
			fila.createCell(3).setCellValue(item.calcularImporte());
		}

		Row filatotal = sheet.createRow(rownum);
		filatotal.createCell(2).setCellValue(mensajes.getMessage("text.factura.form.total", locale));
		filatotal.createCell(3).setCellValue(factura.getTotal());
	}
}