package com.ocean.whale.model;

import com.ocean.whale.exception.WhaleException;
import com.ocean.whale.exception.WhaleServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class PostRecommendationList {
    List<String> recommendations; // list of postId

    public static PostRecommendationList fromMap(Map<String, Object> map) {
        // Extract the recommendations list from the map
        List<String> recommendations = new ArrayList<>();

        try {
            if (map.containsKey("recommendations") && map.get("recommendations") instanceof List) {
                recommendations = (List<String>) map.get("recommendations");
            }
        } catch (Exception e) {
            throw new WhaleServiceException(WhaleException.BAD_DATA_ERROR, "recommendations not a string list");
        }

        // Create and return a new instance of PostRecommendationList
        return new PostRecommendationList(recommendations);
    }
}
