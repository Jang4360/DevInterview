package dev.interview.server.writing.domain;

import dev.interview.server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

// 사용자가 작성한 글 Entity
@Entity
@Table(name = "writings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Writing {
    @Id
    @GeneratedValue
    @Column(name = "writings_id", columnDefinition = "uuid")
    private UUID id;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
