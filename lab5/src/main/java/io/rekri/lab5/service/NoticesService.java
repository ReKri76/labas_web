package io.rekri.lab5.service;

import io.rekri.lab5.DTO.Notice;
import io.rekri.lab5.repository.NoticesRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticesService {

    final private NoticesRepository repository;

    public List<Notice> getAllNotices(){
        return repository.findAll().stream().map(notice -> {
            return new Notice(notice.getHead(), notice.getContent());
        }).toList();
    }
}
