package com.mongs.restaurant.repository;

import com.mongs.restaurant.domain.entity.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {
}
