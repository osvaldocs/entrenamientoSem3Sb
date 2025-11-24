package com.riwi.H3.infrastructure.controller;

import com.riwi.H3.application.port.in.VenueUseCase;
import com.riwi.H3.domain.model.Venue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
@Tag(name = "Venues", description = "Gestión de venues (lugares) para eventos")
public class VenueController {

    private final VenueUseCase venueUseCase;

    public VenueController(VenueUseCase venueUseCase) {
        this.venueUseCase = venueUseCase;
    }

    // -----------------------------
    //          CREATE
    // -----------------------------
    @Operation(
            summary = "Crear un venue",
            description = "Crea un nuevo venue en el catálogo de lugares.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Venue creado correctamente",
                            content = @Content(schema = @Schema(implementation = Venue.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PostMapping
    public ResponseEntity<Venue> create(@RequestBody Venue venue) {
        return ResponseEntity.status(HttpStatus.CREATED).body(venueUseCase.create(venue));
    }

    // -----------------------------
    //          GET BY ID
    // -----------------------------
    @Operation(
            summary = "Buscar venue por ID",
            description = "Retorna un venue según su identificador.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Venue encontrado",
                            content = @Content(schema = @Schema(implementation = Venue.class))),
                    @ApiResponse(responseCode = "404", description = "Venue no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Venue> findById(@PathVariable Long id) {
        return ResponseEntity.ok(venueUseCase.findById(id));
    }

    // -----------------------------
    //          GET ALL
    // -----------------------------
    @Operation(
            summary = "Listar todos los venues",
            description = "Obtiene la lista completa de venues disponibles."
    )
    @GetMapping
    public ResponseEntity<List<Venue>> findAll() {
        return ResponseEntity.ok(venueUseCase.findAll());
    }

    // -----------------------------
    //           UPDATE
    // -----------------------------
    @Operation(
            summary = "Actualizar un venue",
            description = "Actualiza los datos de un venue existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Venue actualizado correctamente",
                            content = @Content(schema = @Schema(implementation = Venue.class))),
                    @ApiResponse(responseCode = "404", description = "Venue no encontrado"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Venue> update(@PathVariable Long id, @RequestBody Venue venue) {
        return ResponseEntity.ok(venueUseCase.update(id, venue));
    }

    // -----------------------------
    //           DELETE
    // -----------------------------
    @Operation(
            summary = "Eliminar un venue",
            description = "Elimina un venue por su identificador.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Venue eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Venue no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        venueUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
