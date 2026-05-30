package com.conciertos.backend.controller;

import com.conciertos.backend.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReporteController {

    private final PdfService pdfService;

    @GetMapping("/dashboard/pdf")
    public ResponseEntity<InputStreamResource> generarPdf() {

        InputStreamResource archivo =
                new InputStreamResource(
                        pdfService.generarReporteDashboard()
                );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=dashboard.pdf"
                )
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(archivo);
    }
}