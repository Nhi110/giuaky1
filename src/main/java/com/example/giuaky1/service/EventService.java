package com.example.giuaky1.service;

import com.example.giuaky1.model.Event;
import com.example.giuaky1.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Lấy tất cả các sự kiện
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    // Tìm sự kiện theo ID
    public Event findById(int id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    // Lưu hoặc cập nhật sự kiện
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    // Cập nhật sự kiện hiện có
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    // Xóa sự kiện theo ID
    public void deleteById(int id) {
        eventRepository.deleteById(id);
    }
}
