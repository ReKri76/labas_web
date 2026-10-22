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

/**
 * Сервис для получения и публикации новостей.
 */
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UsersRepository usersRepository;

    /**
     * Возвращает новости для отображения на главной странице.
     *
     * @return список новостей, отсортированный от новых к старым
     */
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

    /**
     * Публикует новость от имени указанного пользователя.
     *
     * @param request заголовок и текст публикуемой новости
     * @param authorName имя пользователя, публикующего новость
     * @throws java.util.NoSuchElementException если пользователь не найден
     */
    public void createNews(final NewsRequest request, final String authorName) {
        final var news = new News();
        news.setHead(request.getHead());
        news.setContent(request.getContent());
        news.setAuthor(usersRepository.findByName(authorName).orElseThrow());
        newsRepository.save(news);
    }
}
