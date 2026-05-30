package com.conciertos.backend.service.impl;

import com.conciertos.backend.dto.DashboardDTO;
import com.conciertos.backend.service.DashboardService;
import com.conciertos.backend.service.PdfService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final DashboardService dashboardService;

    @Override
    public ByteArrayInputStream generarReporteDashboard() {

        DashboardDTO dashboard = dashboardService.obtenerDashboard();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            Font titulo = new Font(Font.HELVETICA,18, Font.BOLD);
            Paragraph encabezado = new Paragraph("REPORTE EJECUTIVO TICKETCHAIN", titulo);

            encabezado.setAlignment(Element.ALIGN_CENTER);

            document.add(encabezado);

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Usuarios registrados: " + dashboard.getTotalUsuarios()));

            document.add(new Paragraph("Conciertos registrados: " + dashboard.getTotalConciertos()));

            document.add(new Paragraph("Entradas vendidas: " + dashboard.getTotalEntradasVendidas()));

            document.add(new Paragraph("Entradas disponibles: " + dashboard.getTotalEntradasDisponibles()));

            document.add(new Paragraph("Ingresos Totales: S/ " + dashboard.getIngresosTotales()));

            document.add(new Paragraph("Ganancia Empresa (30%): S/ " + dashboard.getGananciaEmpresa()));

            document.add(new Paragraph("Costos Operativos (70%): S/ " + dashboard.getCostosOperativos()));

            document.add(new Paragraph("Concierto Más Vendido: " + dashboard.getConciertoMasVendido()));

            document.add(new Paragraph("Concierto Más Rentable: " + dashboard.getConciertoMasRentable()));

            document.close();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error generando PDF"
            );
        }
        return new ByteArrayInputStream(
                out.toByteArray()
        );
    }
}