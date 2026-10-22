package io.rekri.lab5.service;

import io.rekri.lab5.DTO.NewsRequest;
import io.rekri.lab5.DTO.NewsResponse;
import io.rekri.lab5.entities.News;
import io.rekri.lab5.repository.NewsRepository;
import io.rekri.lab5.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<NewsResponse> getAllNews() {
        return newsRepository.findAllByOrderByCreatedAtDescIdDesc().stream()
                .map(news -> NewsResponse.builder()
                        .id(news.getId())
                        .head(news.getHead())
                        .content(news.getContent())
                        .author(news.getAuthor().getName())
                        .createdAt(news.getCreatedAt())
                        .build())
                .toList();
    }

    public void createNews(final NewsRequest request, final String authorName) {
        final var news = new News();
        news.setHead(request.getHead());
        news.setContent(request.getContent());
        news.setAuthor(usersRepository.findByName(authorName).orElseThrow());
        newsRepository.save(news);
    }
}
