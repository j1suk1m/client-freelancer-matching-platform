package com.example.profileservice.tag.api;

import com.example.profileservice.tag.model.dto.response.TagResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController implements TagApiController {

    // 임시 Mock 데이터 생성 (Swagger 출력을 위해)
    private final TagResponse tagSpring = new TagResponse("tag-001", "Spring Boot");
    private final TagResponse tagJPA = new TagResponse("tag-002", "Java");
    private final List<TagResponse> mockTags = List.of(tagSpring, tagJPA);

    @Override
    public ResponseEntity<List<TagResponse>> getAllTags() {
        // TODO: 실제 로직 구현
        return ResponseEntity.ok(mockTags);
    }

    @Override
    public ResponseEntity<List<TagResponse>> getMyTags() {
        // TODO: 실제 로직 구현
        return ResponseEntity.ok(List.of(tagSpring));
    }

    @Override
    public ResponseEntity<Void> linkMemberTag(@PathVariable String tagCode) {
        // TODO: 실제 로직 구현
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> unlinkMemberTag(@PathVariable String tagCode) {
        // TODO: 실제 로직 구현
        return ResponseEntity.noContent().build();
    }
}
