package com.riwi.H3.infrastructure.controller;

import com.riwi.H3.application.port.in.EventUseCase;
import com.riwi.H3.domain.model.Event;
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
@RequestMapping("/events")
@Tag(name = "Events", description = "Gestión de eventos: CRUD completo")
public class EventController {

    private final EventUseCase eventUseCase;

    public EventController(EventUseCase eventUseCase) {
        this.eventUseCase = eventUseCase;
    }

    // -----------------------------
    //          CREATE
    // -----------------------------
    @Operation(
            summary = "Crear un evento",
            description = "Crea un nuevo evento dentro del catálogo.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Evento creado correctamente",
                            content = @Content(schema = @Schema(implementation = Event.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventUseCase.create(event));
    }

    // -----------------------------
    //          GET BY ID
    // -----------------------------
    @Operation(
            summary = "Buscar evento por ID",
            description = "Retorna un evento usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evento encontrado",
                            content = @Content(schema = @Schema(implementation = Event.class))),
                    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        return ResponseEntity.ok(eventUseCase.findById(id));
    }

    // -----------------------------
    //          GET ALL
    // -----------------------------
    @Operation(
            summary = "Listar todos los eventos",
            description = "Obtiene la lista completa de eventos registrados."
    )
    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        return ResponseEntity.ok(eventUseCase.findAll());
    }

    // -----------------------------
    //           UPDATE
    // -----------------------------
    @Operation(
            summary = "Actualizar un evento",
            description = "Actualiza los datos de un evento existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evento actualizado correctamente",
                            content = @Content(schema = @Schema(implementation = Event.class))),
                    @ApiResponse(responseCode = "404", description = "Evento no encontrado"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventUseCase.update(id, event));
    }

    // -----------------------------
    //           DELETE
    // -----------------------------
    @Operation(
            summary = "Eliminar un evento",
            description = "Elimina un evento por su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Evento eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
