package com.example.giuaky1.controller;

import com.example.giuaky1.model.Event;
import com.example.giuaky1.repository.EventRepository;
import com.example.giuaky1.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Event API", description = "API quản lý sự kiện")
public class EventController {

    @Autowired
    private EventRepository eventRepository;


    @Autowired
    private EventService eventService;

    @Operation(summary = "Lấy danh sách tất cả sự kiện")
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Operation(summary = "Lấy thông tin sự kiện theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        Event event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }

    @Operation(summary = "Tạo mới một sự kiện")
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @Operation(summary = "Cập nhật sự kiện theo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event updatedEvent) {
        Event existingEvent = eventService.findById(id);

        // Cập nhật thông tin sự kiện
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());
        existingEvent.setOrganizer(updatedEvent.getOrganizer());
        existingEvent.setCategory(updatedEvent.getCategory());

        // Lưu sự kiện đã cập nhật
        Event savedEvent = eventService.update(existingEvent);

        // Trả về sự kiện đã được cập nhật
        return ResponseEntity.ok(savedEvent);
    }

    @Operation(summary = "Xóa sự kiện theo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
